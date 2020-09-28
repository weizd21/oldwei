import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-6-13
 */
@Data
public class Graph implements Serializable {

    private Long id;

    private Set<Node> nodes;

    private Set<Relationship> relationships;
}
