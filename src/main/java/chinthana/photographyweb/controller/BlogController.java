package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.Blog;
import chinthana.photographyweb.entity.Portfolio;
import chinthana.photographyweb.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/blog")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<?> createBlog( @RequestParam("title") String title,
                                         @RequestParam("description") String description,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam("publishDate") LocalDateTime publishDate,
                                         @RequestParam("isPublished") boolean isPublished) throws IOException {
        Blog savedBlog = blogService.createBlog(title,description,file,publishDate,isPublished);
        return ResponseEntity.ok(savedBlog);
    }


    @GetMapping("/unpublished")
    public ResponseEntity<List<Blog>> getUnPublishedBlogs() {
        List<Blog> publishedBlogs = blogService.getBlogsByIsPublished(false);
        return ResponseEntity.ok(publishedBlogs);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Blog>> getPublishedBlogs() {
        List<Blog> publishedBlogs = blogService.getBlogsByIsPublished(true);
        return ResponseEntity.ok(publishedBlogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable String id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/title/{id}")
    public ResponseEntity<Blog> updateBlogTitle(@PathVariable String id,
                                                @RequestParam String title) throws IOException {
        Blog updatedBlog = blogService.updateBlogTitle(id, title);
        if (updatedBlog != null) {
            return ResponseEntity.ok(updatedBlog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("description/{id}")
    public ResponseEntity<Blog> updateBlogDescription(@PathVariable String id,
                                                      @RequestParam String description) throws IOException {
        Blog updatedBlog = blogService.updateBlogDescription(id, description);
        if (updatedBlog != null) {
            return ResponseEntity.ok(updatedBlog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/blogPhoto/{id}")
    public ResponseEntity<Blog> updateBlogPhoto(@PathVariable String id,
                                                @RequestParam("file") MultipartFile blogPhoto) throws IOException {
        Blog updatedBlog = blogService.updateBlogPhoto(id, blogPhoto);
        if (updatedBlog != null) {
            return ResponseEntity.ok(updatedBlog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{blogId}/updateIsPublished")
    public ResponseEntity<Blog> updateIsPublished(
            @PathVariable String blogId,
            @RequestParam boolean newIsPublished
    ) {
        Blog updatedBlog = blogService.updateBlogPublishStatus(blogId, newIsPublished);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable String blogId) {
        blogService.deleteBlogById(blogId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lastTwoPublished")
    public ResponseEntity<List<Blog>> getLastTwoPublishedBlogs() {
        List<Blog> lastTwoPublishedBlogs = blogService.getLastTwoPublishedBlogs();
        return ResponseEntity.ok(lastTwoPublishedBlogs);
    }

}
