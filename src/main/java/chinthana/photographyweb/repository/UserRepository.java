package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
