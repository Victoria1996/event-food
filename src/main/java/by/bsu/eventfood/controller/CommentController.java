package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddCommentDto;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public void addComment(@RequestBody AddCommentDto dto,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        commentService.addComment(dto, userDetails.getClient());
    }

    @PostMapping("/remove/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        commentService.deleteComment(commentId, userDetails.getClient());
    }
}
