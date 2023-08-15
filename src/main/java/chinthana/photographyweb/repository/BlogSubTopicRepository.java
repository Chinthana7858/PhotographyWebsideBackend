package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.BlogSubTopic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSubTopicRepository extends MongoRepository<BlogSubTopic,String> {
    List<BlogSubTopic> findByBlogId(String blogId);
}
