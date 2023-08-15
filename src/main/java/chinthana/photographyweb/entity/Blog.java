package chinthana.photographyweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "blog")
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime publishDate;
    private String blogPhotoUrl;
    private boolean isPublished;
}
