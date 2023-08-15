package chinthana.photographyweb.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "details")
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    @Id
    private String detailsId;
    private String email;
    private String password;
    private String introduction;
}
