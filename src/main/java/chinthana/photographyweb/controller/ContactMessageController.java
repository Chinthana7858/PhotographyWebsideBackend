package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.ContactMessage;
import chinthana.photographyweb.service.ContactMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/message")
@AllArgsConstructor
public class ContactMessageController {
    private final ContactMessageService contactMessageService;

    @PostMapping
    public ResponseEntity<ContactMessage> saveMessage(@RequestBody ContactMessage contactMessage) {
        ContactMessage savedMsg = contactMessageService.saveMessage(contactMessage);
        return new ResponseEntity<>(savedMsg, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMeaages() {
        List<ContactMessage> messages = contactMessageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PutMapping("/{messageId}/mark-as-read")
    public ResponseEntity<String> markMessageAsRead(@PathVariable String messageId) {
        contactMessageService.markMessageAsRead(messageId);
        return ResponseEntity.ok("Message marked as read.");
    }
}
