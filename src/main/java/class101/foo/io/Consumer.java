package class101.foo.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @RabbitListener(queues = "CREATE_POST_QUEUE") // 이 큐를 consume 하겠다.
    public void handler(String message) throws JsonProcessingException {
        Post post = objectMapper.readValue(message,Post.class);
        postRepository.save(post);
    }
}
