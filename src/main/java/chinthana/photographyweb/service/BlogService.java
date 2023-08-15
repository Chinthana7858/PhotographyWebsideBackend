package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.Blog;
import chinthana.photographyweb.entity.Portfolio;
import chinthana.photographyweb.repository.BlogRepository;
import chinthana.photographyweb.repository.categoryPhotoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Blog createBlog(String title, String description, MultipartFile file, LocalDateTime publishDate, boolean isPublished) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String blogPhotoUrl = uploadResult.get("url").toString();
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setPublishDate(publishDate);
        blog.setBlogPhotoUrl(blogPhotoUrl);
        blog.setPublished(isPublished);
        return blogRepository.save(blog);
    }

    public List<Blog> getBlogsByIsPublished(boolean isPublished) {
        return blogRepository.findByIsPublished(isPublished);
    }

    public Optional<Blog> getBlogById(String id) {
        return blogRepository.findById(id);
    }

    public Blog updateBlogTitle(String id, String title) throws IOException {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setTitle(title);
            return blogRepository.save(blog);
        } else {
            // Blog with the given id not found
            return null;
        }
    }

    public Blog updateBlogDescription(String id, String description) throws IOException {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setDescription(description);
            return blogRepository.save(blog);
        } else {
            // Blog with the given id not found
            return null;
        }
    }

    public Blog updateBlogPhoto(String id, MultipartFile blogPhoto) throws IOException {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            Map uploadResult = cloudinary.uploader().upload(blogPhoto.getBytes(), ObjectUtils.emptyMap());
            String blogPhotoUrl = uploadResult.get("url").toString();
            blog.setBlogPhotoUrl(blogPhotoUrl);
            return blogRepository.save(blog);
        } else {
            // Blog with the given id not found
            return null;
        }
    }


    public Blog updateBlogPublishStatus(String blogId, boolean newIsPublished) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog with ID " + blogId + " not found."));

        existingBlog.setPublished(newIsPublished);
        return blogRepository.save(existingBlog);
    }

    public void deleteBlogById(String blogId){
        blogRepository.deleteById(blogId);
    }

    public List<Blog> getLastTwoPublishedBlogs() {
        Pageable pageable = PageRequest.of(0, 2);
        return blogRepository.findByIsPublishedTrueOrderByPublishDateDesc(pageable);
    }
}
