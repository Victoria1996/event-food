package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddCommentDto;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.Comment;
import by.bsu.eventfood.repository.CommentRepository;
import by.bsu.eventfood.service.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(AddCommentDto dto, Client client) {
        Comment comment = commentMapper.map(dto);
        comment.setClient(client);

        commentRepository.save(comment);

        log.info("Comment with id {} from user {} was saved", comment.getId(), client.getEmail());
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, final Client client) {
        commentRepository.findById(commentId)
                .filter(comment -> comment.getClient().getId().equals(client.getId()))
                .map(this::deleteComment)
                .orElse("error: not exists or not belongs to user");
    }

    private String deleteComment(Comment comment) {
        Long id = comment.getId();

        commentRepository.delete(comment);

        log.info("Comment {} was deleted", id);

        removeRelatedToId(id);

        log.info("Comment {} child relatedToIds were deleted", id);

        return "comment was deleted success";
    }

    private void removeRelatedToId(final Long relatedToId) {
        commentRepository.findAllByRelatedToId(relatedToId)
                .stream()
                .forEach(comment -> {
                    comment.setRelatedToId(null);
                    commentRepository.save(comment);
                });
    }
}
