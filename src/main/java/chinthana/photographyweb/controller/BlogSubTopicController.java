package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.Blog;
import chinthana.photographyweb.entity.BlogSubTopic;
import chinthana.photographyweb.service.BlogSubTopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/blogSubTopic")
@AllArgsConstructor
public class BlogSubTopicController {
    private final BlogSubTopicService blogSubTopicService;

    @PostMapping("/create")
    public ResponseEntity<BlogSubTopic> createBlogSubTopic(@RequestParam String blogId,
                                                           @RequestParam int subTopicNo,
                                                           @RequestParam String subTitle,
                                                           @RequestParam String content,
                                                           @RequestParam("file") MultipartFile subTopicPhoto) throws IOException {
        BlogSubTopic createdSubTopic = blogSubTopicService.createBlogSubTopic(blogId, subTopicNo, subTitle, content, subTopicPhoto);
        return ResponseEntity.ok(createdSubTopic);
    }

    @GetMapping("/byBlog/{blogId}")
    public ResponseEntity<List<BlogSubTopic>> getSubTopicsByBlogId(@PathVariable String blogId) {
        List<BlogSubTopic> subTopics = blogSubTopicService.getSubTopicsByBlogId(blogId);
        if (subTopics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subTopics);
    }

    @PutMapping("/{id}/updateSubTitle")
    public ResponseEntity<BlogSubTopic> updateBlogTitle(@PathVariable String id,
                                                @RequestParam String subtitle) throws IOException {
        BlogSubTopic updatedBlogSubTopic = blogSubTopicService.updateSubTitle(id, subtitle);
        if (updatedBlogSubTopic != null) {
            return ResponseEntity.ok(updatedBlogSubTopic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("content/{id}")
    public ResponseEntity<BlogSubTopic> updateBlogSubtopicContent(@PathVariable String id,
                                                                 @RequestParam String content) throws IOException {
        BlogSubTopic updatedBlogSubtopic = blogSubTopicService.updateContent(id,content);
        if (updatedBlogSubtopic != null) {
            return ResponseEntity.ok(updatedBlogSubtopic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/blogPhoto/{id}")
    public ResponseEntity<BlogSubTopic> updateBlogSubTopicPhoto(@PathVariable String id,
                                                @RequestParam("file") MultipartFile blogSubTopicPhoto) throws IOException {
        BlogSubTopic updatedBlogSubtopic = blogSubTopicService.updateBlogSubTopicPhoto(id, blogSubTopicPhoto);
        if (updatedBlogSubtopic != null) {
            return ResponseEntity.ok(updatedBlogSubtopic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubBlogById(@PathVariable String id) {
        blogSubTopicService.deleteSubBlogById(id);
        return ResponseEntity.noContent().build();
    }
}
