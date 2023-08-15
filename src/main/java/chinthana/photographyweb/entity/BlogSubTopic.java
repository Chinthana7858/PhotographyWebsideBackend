package chinthana.photographyweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "blogSubTopic")
@AllArgsConstructor
@NoArgsConstructor
public class BlogSubTopic {
    @Id
    private String id;
    private int subTopicNo;
    private String blogId;
    private String subTitle;
    private String content;
    private String subTopicPhoto;
}
