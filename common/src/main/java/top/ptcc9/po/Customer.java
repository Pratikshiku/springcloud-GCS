package top.ptcc9.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Pratik_shiku
 */
@Data
@Accessors(chain = true)
public class Customer {
    /**
     * 顾客 id （主键）
     */
    private String id;

    /**
     * 顾客 phone
     */
    private String phone;

    /**
     * 顾客 password
     */
    private String password;

    /**
     * 顾客 余额
     * 默认值 0.00
     */
    private Double balance;

    /**
     * 顾客 注册时间
     */
    private Date createTime;

    /**
     * 顾客 会员过期时间
     * 无会员可为 null
     */
    private Date vipExpiration;

    /**
     * 顾客 逻辑删除
     * 默认值 0
     */
    private Integer deleted;
}
