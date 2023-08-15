package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.ContactMessage;
import chinthana.photographyweb.entity.Portfolio;
import chinthana.photographyweb.repository.ContactMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactMessageService {
    @Autowired
    private final ContactMessageRepository contactMessageRepository;

    public ContactMessage saveMessage(ContactMessage contactMessage){
        return contactMessageRepository.save(contactMessage);
    }

    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }

    public void markMessageAsRead(String messageId) {
        ContactMessage message = contactMessageRepository.findById(messageId).orElse(null);
        if (message != null) {
            message.setRead(true);
            contactMessageRepository.save(message);
        }
    }
}
