package com.cdyfsz.base.biz.svc.zhongdeng.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO基类  mapper
 * 成都一方思致科技有限公司
 *
 * @Author luojiao
 * @Date Created in 2019/5/17 16:35
 * @Version V0.1
 */
@Data
public class BasePojo implements Serializable {

    @TableId
    @TableField(value = "key_id")
    @ApiModelProperty("key_id")
    private Long keyId;

    /**
     * 添加人
     */
    @TableField(value = "add_user_id", fill = FieldFill.INSERT)
    private Long addUserId;

    /**
     * 添加时间
     */
    @TableField(value = "add_time", fill = FieldFill.INSERT)
    private Date addTime;

    /**
     * 更新人
     */
    @TableField(value = "modify_user_id", fill = FieldFill.UPDATE)
    private Long modifyUserId;

    /**
     * 更新时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;

    /**
     * 删除标识
     */
    @TableField(value = "del_status", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean delStatus = false;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id", fill = FieldFill.INSERT)
    private Long tenantId;

    /**
     * 创建请求ID
     */
    @TableField(value = "add_request_id", fill = FieldFill.INSERT)
    private String addRequestId;

    /**
     * 修改请求ID
     */
    @TableField(value = "modify_request_id", fill = FieldFill.UPDATE)
    private String modifyRequestId;
}
