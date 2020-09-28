package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/22 上午11:03
 */
@Data
public class Parameter implements Serializable {
    private String type;
    private String name;
    private Object value;
    private String description;
    private String defaultValue;
    private boolean isAdvanced;
    private boolean readonly;
    private boolean option;
    private boolean input;
    private String options;
    private Map<String, Object> properties = new HashMap();
}
