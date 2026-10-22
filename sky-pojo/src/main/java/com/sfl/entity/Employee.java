package com.sfl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 账号状态，1正常，0禁用
     */
    private Integer status;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人id
     */
    private Long createUser;

    /**
     * 最后修改人id
     */
    private Long updateUser;
}
