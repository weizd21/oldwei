package top.oldwei.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:weizd
 * @Date:20-3-5
 *
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 表主键
     */
    @TableId(type = IdType.UUID)
    private String id;


    /**
     * 逻辑删除
     * 0-未删除
     * 1-已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 记录生成人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 生成时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;



}
