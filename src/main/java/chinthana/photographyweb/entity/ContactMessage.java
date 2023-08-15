package chinthana.photographyweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessage {
    @Id
    private String id;
    private String senderName;
    private String senderEmail;
    private String subject;
    private String Message;
    private Date date;
    private boolean isRead;
}
