package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByRelatedToId(Long relatedToId);

    List<Comment> findAllByPlaceId(Long placeId);
}
