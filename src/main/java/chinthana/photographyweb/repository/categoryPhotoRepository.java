package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.CategoryPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface categoryPhotoRepository extends MongoRepository<CategoryPhoto, String> {
    List<CategoryPhoto> findByCategory(String category);
}
