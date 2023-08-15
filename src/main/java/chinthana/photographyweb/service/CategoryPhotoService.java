package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.CategoryPhoto;
import chinthana.photographyweb.repository.categoryPhotoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CategoryPhotoService {
    @Autowired
    private categoryPhotoRepository photoRepository;

    @Autowired
    private Cloudinary cloudinary;

    public CategoryPhoto savePhoto(String category, MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploadResult.get("url").toString();
        CategoryPhoto photo = new CategoryPhoto();
        photo.setCategory(category);
        photo.setName(file.getOriginalFilename());
        photo.setImageUrl(imageUrl);
        return photoRepository.save(photo);
    }

    public List<CategoryPhoto> getPhotosByCategory(String category) {
        return photoRepository.findByCategory(category);
    }

    public void deleteCategoryPhotoById(String id) {
        photoRepository.deleteById(id);
    }
}
