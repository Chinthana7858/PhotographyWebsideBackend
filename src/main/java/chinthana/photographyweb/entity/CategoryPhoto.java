package chinthana.photographyweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//Used in portfolio - (category=portfolioId)
@Data
@Document(collection = "photos")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPhoto {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String category;
}
