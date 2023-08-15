package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.Blog;
import chinthana.photographyweb.entity.BlogSubTopic;
import chinthana.photographyweb.repository.BlogSubTopicRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class BlogSubTopicService {
    @Autowired
    private BlogSubTopicRepository blogSubTopicRepository;

    @Autowired
    private Cloudinary cloudinary;

    public BlogSubTopic createBlogSubTopic(String blogId, int subTopicNo, String subTitle, String content, MultipartFile subTopicPhoto) throws IOException {
        String subTopicPhotoUrl = null;

        if (subTopicPhoto != null && !subTopicPhoto.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(subTopicPhoto.getBytes(), ObjectUtils.emptyMap());
            subTopicPhotoUrl = uploadResult.get("url").toString();
        }

        BlogSubTopic subTopic = new BlogSubTopic();
        subTopic.setBlogId(blogId);
        subTopic.setSubTopicNo(subTopicNo);
        subTopic.setSubTitle(subTitle);
        subTopic.setContent(content);
        subTopic.setSubTopicPhoto(subTopicPhotoUrl);
        return blogSubTopicRepository.save(subTopic);
    }

    public List<BlogSubTopic> getSubTopicsByBlogId(String blogId) {
        return blogSubTopicRepository.findByBlogId(blogId);
    }


    public BlogSubTopic updateSubTitle(String id, String sibTitle) throws IOException {
        BlogSubTopic blogSubTopic = blogSubTopicRepository.findById(id).orElse(null);
        if (blogSubTopic != null) {
            blogSubTopic.setSubTitle(sibTitle);
            return blogSubTopicRepository.save(blogSubTopic);
        } else {
            // Blog with the given id not found
            return null;
        }
    }


    public BlogSubTopic updateContent(String id, String content) throws IOException {
        BlogSubTopic blogSubTopic = blogSubTopicRepository.findById(id).orElse(null);
        if (blogSubTopic != null) {
            blogSubTopic.setContent(content);
            return blogSubTopicRepository.save(blogSubTopic);
        } else {
            // Blog with the given id not found
            return null;
        }
    }

    public BlogSubTopic updateBlogSubTopicPhoto(String id, MultipartFile blogPhoto) throws IOException {
        BlogSubTopic blogSubTopic = blogSubTopicRepository.findById(id).orElse(null);
        if (blogSubTopic != null) {
            Map uploadResult = cloudinary.uploader().upload(blogPhoto.getBytes(), ObjectUtils.emptyMap());
            String subTopicPhoto = uploadResult.get("url").toString();
            blogSubTopic.setSubTopicPhoto(subTopicPhoto);
            return blogSubTopicRepository.save(blogSubTopic);
        } else {
            // Blog with the given id not found
            return null;
        }
    }

    public void deleteSubBlogById(String blogId){
        blogSubTopicRepository.deleteById(blogId);
    }
}
