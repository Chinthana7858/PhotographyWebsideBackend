package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.BasicPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasicPhotoRepository extends MongoRepository<BasicPhoto, String> {
}
