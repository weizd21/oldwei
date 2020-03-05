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
    protected String id;


    /**
     * 逻辑删除
     * 0-未删除
     * 1-已删除
     */
    @TableLogic
    protected Integer deleted;

    /**
     * 乐观锁
     */
    @Version
    protected Integer version;

    /**
     * 记录生成人
     */
    @TableField(fill = FieldFill.INSERT)
    protected String createBy;

    /**
     * 生成时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    protected String updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;



}
