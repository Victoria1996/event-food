package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddCommentDto;
import by.bsu.eventfood.model.Client;

public interface CommentService {
    void addComment(AddCommentDto dto, Client client);

    void deleteComment(Long commentId, Client client);
}
