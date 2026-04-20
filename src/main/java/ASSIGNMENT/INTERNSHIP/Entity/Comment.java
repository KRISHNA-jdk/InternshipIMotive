package ASSIGNMENT.INTERNSHIP.Entity;

import ASSIGNMENT.INTERNSHIP.ENUM.AuthorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    @NotNull
    private Long authorId;

    @Enumerated(EnumType.STRING)
    private AuthorType authorType;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int depthLevel;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
