package com.gzzsc.lai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author laizs
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id,自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓
     */
    @TableField("lastName")
    private String lastName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * did
     */
    private Integer dId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
