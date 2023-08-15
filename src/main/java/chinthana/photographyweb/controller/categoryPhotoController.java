package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.BasicPhoto;
import chinthana.photographyweb.entity.CategoryPhoto;
import chinthana.photographyweb.service.BasicPhotoService;
import chinthana.photographyweb.service.CategoryPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/images/category")
@AllArgsConstructor
public class categoryPhotoController {
    private final CategoryPhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("category") String category, @RequestParam("file") MultipartFile file) throws IOException {
        CategoryPhoto savedPhoto = photoService.savePhoto(category,file);
        return ResponseEntity.ok(savedPhoto);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<CategoryPhoto>> getPhotosByCategory(@PathVariable String category) {
        List<CategoryPhoto> photos = photoService.getPhotosByCategory(category);
        return ResponseEntity.ok(photos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryPhotoById(@PathVariable String id) {
        photoService.deleteCategoryPhotoById(id);
        return ResponseEntity.ok().build();
    }
}
