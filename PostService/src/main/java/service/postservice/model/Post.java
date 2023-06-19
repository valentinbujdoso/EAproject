package service.postservice.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank
    private String title;
    @Lob
    @NotBlank
    private String description;

    private String userId;

    public Post(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }
}
