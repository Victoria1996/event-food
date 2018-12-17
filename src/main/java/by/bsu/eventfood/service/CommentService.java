package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddCommentDto;
import by.bsu.eventfood.controller.dto.CommentDto;
import by.bsu.eventfood.model.Client;

import java.util.List;

public interface CommentService {
    void addComment(AddCommentDto dto, Client client);

    void deleteComment(Long commentId, Client client);

    List<CommentDto> findPlaceComments(Long placeId);
}
