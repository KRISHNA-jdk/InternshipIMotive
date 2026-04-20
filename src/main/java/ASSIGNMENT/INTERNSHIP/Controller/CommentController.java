package ASSIGNMENT.INTERNSHIP.Controller;

import ASSIGNMENT.INTERNSHIP.DTO.CommentRequest;
import ASSIGNMENT.INTERNSHIP.Entity.Comment;
import ASSIGNMENT.INTERNSHIP.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request) {

        return ResponseEntity.ok(commentService.addComment(postId, request));
    }
}
