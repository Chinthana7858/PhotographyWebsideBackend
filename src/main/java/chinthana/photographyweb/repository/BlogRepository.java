package chinthana.photographyweb.repository;

import chinthana.photographyweb.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findByIsPublished(boolean isPublished);
    List<Blog> findByIsPublishedTrueOrderByPublishDateDesc(Pageable pageable);
}
