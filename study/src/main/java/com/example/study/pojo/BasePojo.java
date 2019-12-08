package com.example.study.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author libo
 */
@Data
public class BasePojo implements Serializable {
    /**
     * 主键id
     */
    @TableId
    @TableField(value = "key_id")
    private String keyId;

    /**
     * 更新时间
     */
    @TableId
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private LocalDateTime modifyTime;
}
