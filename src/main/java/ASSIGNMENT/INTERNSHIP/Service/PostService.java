package ASSIGNMENT.INTERNSHIP.Service;

import ASSIGNMENT.INTERNSHIP.DTO.PostRequest;
import ASSIGNMENT.INTERNSHIP.ENUM.AuthorType;
import ASSIGNMENT.INTERNSHIP.Entity.Post;
import ASSIGNMENT.INTERNSHIP.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(PostRequest request) {

        Post post = new Post();
        post.setAuthorId(request.getAuthorId());
        post.setAuthorType(AuthorType.valueOf(request.getAuthorType()));
        post.setContent(request.getContent());
        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }
}
