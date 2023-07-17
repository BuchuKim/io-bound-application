package class101.foo.io;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendTo(String message) {
        rabbitMessagingTemplate.convertAndSend("CREATE_POST_QUEUE",message);
    }
}
