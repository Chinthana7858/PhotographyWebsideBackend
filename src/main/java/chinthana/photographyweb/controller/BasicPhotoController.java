package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.BasicPhoto;
import chinthana.photographyweb.service.BasicPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/images")
@AllArgsConstructor
public class BasicPhotoController {

    private final BasicPhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("id") String id,@RequestParam("file") MultipartFile file) throws IOException {
        BasicPhoto savedPhoto = photoService.savePhoto(id,file);
        return ResponseEntity.ok(savedPhoto);
    }

    @GetMapping("/photos")
    public ResponseEntity<?> getAllPhotos() {
        Iterable<BasicPhoto> photos = photoService.getAllPhotos();
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPhotoById(@PathVariable String id) {
        BasicPhoto photo = photoService.getPhotoById(id);
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photo);
    }
}
