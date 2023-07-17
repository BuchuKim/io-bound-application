package class101.foo.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    private Producer producer;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Integer PAGE_SIZE = 10;

    // 1. 글을 작성한다.
    @PostMapping("/post")
    public Post createPost(@RequestBody Post post) throws JsonProcessingException {
        producer.sendTo(objectMapper.writeValueAsString(post));
        return post;
    }

    // 2. 페이지 단위로 글 목록 받기 : default = 1
    @GetMapping("/posts")
    public Page<Post> getPostList(@RequestParam(defaultValue = "1") Integer page) {
        return postRepository.findAll(PageRequest.of(page-1,PAGE_SIZE, Sort.by("id").descending()));
    }
    
    // 3. 글 번호로 조회
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id) {
        return postRepository.findById(id).get();
    }
    
    // 4. 글 내용으로 검색 -> 해당 내용이 포함된 모든 글
    @GetMapping("/search")
    public List<Post> findPostByContent(@RequestParam String content) {
        return postRepository.findByContentContains(content);
    }
}
