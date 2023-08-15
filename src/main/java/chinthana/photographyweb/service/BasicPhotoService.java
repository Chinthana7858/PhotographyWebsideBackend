package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.BasicPhoto;
import chinthana.photographyweb.repository.BasicPhotoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class BasicPhotoService {
    @Autowired
    private BasicPhotoRepository photoRepository;

    @Autowired
    private Cloudinary cloudinary;

    public BasicPhoto savePhoto(String id, MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploadResult.get("url").toString();
        BasicPhoto photo = new BasicPhoto();
        photo.setId(id);
        photo.setName(file.getOriginalFilename());
        photo.setImageUrl(imageUrl);
        return photoRepository.save(photo);
    }

    public Iterable<BasicPhoto> getAllPhotos() {
        return photoRepository.findAll();
    }

    public BasicPhoto getPhotoById(String id) {
        return photoRepository.findById(id).orElse(null);
    }

}