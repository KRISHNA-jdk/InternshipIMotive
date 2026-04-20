package ASSIGNMENT.INTERNSHIP.Service;

import ASSIGNMENT.INTERNSHIP.DTO.CommentRequest;
import ASSIGNMENT.INTERNSHIP.ENUM.AuthorType;
import ASSIGNMENT.INTERNSHIP.Entity.Comment;
import ASSIGNMENT.INTERNSHIP.Exception.BadRequestException;
import ASSIGNMENT.INTERNSHIP.Exception.ResourceNotFoundException;
import ASSIGNMENT.INTERNSHIP.Repository.CommentRepository;
import ASSIGNMENT.INTERNSHIP.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Comment addComment(Long postId, CommentRequest request) {


        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setAuthorId(request.getAuthorId());
        comment.setAuthorType(AuthorType.valueOf(request.getAuthorType()));
        comment.setContent(request.getContent());
        comment.setDepthLevel(request.getDepthLevel());

        if (request.getDepthLevel() > 20) {
            throw new BadRequestException("Max depth exceeded (20)");
        }

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found");
        }

        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }
}
