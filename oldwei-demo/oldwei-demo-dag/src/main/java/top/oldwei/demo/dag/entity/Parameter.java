package top.oldwei.demo.dag.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Parameter implements Serializable {
    private String type;

    private String name;

    private Object value;

    private String description;

    private String defaultValue;

    private boolean readonly;

    private boolean option;

    private boolean input;

}
