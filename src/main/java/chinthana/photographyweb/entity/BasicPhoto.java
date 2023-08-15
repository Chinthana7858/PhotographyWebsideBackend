package chinthana.photographyweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "photos")
@AllArgsConstructor
@NoArgsConstructor
public class BasicPhoto {
    @Id
    private String id;
    private String name;
    private String imageUrl;
}
