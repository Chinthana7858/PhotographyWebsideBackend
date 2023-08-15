package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.Details;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends MongoRepository<Details,String> {
}
