package ASSIGNMENT.INTERNSHIP.Controller;

import ASSIGNMENT.INTERNSHIP.DTO.PostRequest;
import ASSIGNMENT.INTERNSHIP.Entity.Post;
import ASSIGNMENT.INTERNSHIP.Service.PostService;
import ASSIGNMENT.INTERNSHIP.Service.ViralityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ViralityService viralityService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        viralityService.updateScore(postId, "LIKE");
        return ResponseEntity.ok("Post liked");
    }
}
