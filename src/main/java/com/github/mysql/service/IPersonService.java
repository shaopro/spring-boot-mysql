package com.github.mysql.service;

import com.github.mysql.pojo.OneToOnePersonInfoDO;

/**
 * <p>
 * 创建时间为 下午12:57 2019/10/9
 * 项目名称 spring-boot-mysql
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IPersonService {

    OneToOnePersonInfoDO savePerson(OneToOnePersonInfoDO oneToOnePersonInfoDO);

    OneToOnePersonInfoDO updatePerson(OneToOnePersonInfoDO oneToOnePersonInfoDO);

}
