package com.github.mysql.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * <p>
 * 创建时间为 下午4:16 2019/9/12
 * 项目名称 spring-boot-mysql
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Data
@Builder
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table_employee")
@Entity(name = "entity_employee")
public class OneToManyEmployeeDO implements Serializable {

    private static final long serialVersionUID = -1491389679180230248L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    private String eName;

    @Transient
    @JsonIgnore
    @ManyToOne(targetEntity = OneToManyDepartmentDO.class)
    @JoinColumn(name = "dept_id", referencedColumnName = "department_id")
    private OneToManyDepartmentDO department;

}
