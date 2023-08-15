package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.Details;
import chinthana.photographyweb.entity.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends MongoRepository<Portfolio,String> {
    List<Portfolio> findTop2ByOrderByDateTimeDesc();
}
