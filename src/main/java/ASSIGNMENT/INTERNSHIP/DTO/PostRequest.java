package ASSIGNMENT.INTERNSHIP.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Long authorId;
    private String authorType;
    private String content;
}
