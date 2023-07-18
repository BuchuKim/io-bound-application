package class101.foo.io;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Data
@Document(indexName = "post")
public class Post {
    @Id
    private String id;
    private String content;
}
