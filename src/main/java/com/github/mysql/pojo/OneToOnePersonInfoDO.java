package com.github.mysql.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 一对一映射
 *
 * <p>
 * 创建时间为 21:34 2019-07-03
 * 项目名称 spring-boot-mysql
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@Table(name = "table_person")
@Entity(name = "entity_person")
public class OneToOnePersonInfoDO {

    /**
     * -@GeneratedValue(generator = "uuid2")
     * -@GenericGenerator(name = "uuid2", strategy = "uuid2")
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private Integer age;

    private Integer addressId;

    /**
     * name = "addressId" 指定了上面 String addressId,映射到 AddressDO 的 id 字段.
     */
    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id", insertable = false, updatable = false)
    private OneToOnePersonAddressDO address;


}
