import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-6-13
 */
@Data
public class Node implements Serializable {


    private Long id;

    /**
     * 属性
     */
    private Map<String,Object> properties;

    /**
     * 标签
     */
    private List<String> labels;

}
