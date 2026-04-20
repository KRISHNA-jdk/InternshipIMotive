package ASSIGNMENT.INTERNSHIP.Controller;

import ASSIGNMENT.INTERNSHIP.DTO.PostRequest;
import ASSIGNMENT.INTERNSHIP.Entity.Post;
import ASSIGNMENT.INTERNSHIP.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        return ResponseEntity.ok("Post liked (logic in Phase 2)");
    }
}
