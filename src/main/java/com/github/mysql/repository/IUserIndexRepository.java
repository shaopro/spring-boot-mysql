package com.github.mysql.repository;

import com.github.mysql.pojo.UserIndexDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 创建时间为 下午10:10 2019/11/26
 * 项目名称 spring-boot-mysql
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IUserIndexRepository extends JpaRepository<UserIndexDO, Long> {

}
