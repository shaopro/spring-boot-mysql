package com.github.mysql.controller;

import com.github.mysql.pojo.UserInfoDO;
import com.github.mysql.pojo.dto.UserInfoDTO;
import com.github.mysql.repository.IUserInfoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 石少东
 * @date 2020-10-27 15:11
 * @since 1.0
 */

@RestController
public class SpecificationUserController {

    @Resource
    private IUserInfoRepository repository;

    @PutMapping("/user")
    public UserInfoDO updateUser(@RequestBody UserInfoDO userInfo) {
        return repository.save(userInfo);
    }


    @GetMapping("/users")
    public Page<UserInfoDO> listUsers(UserInfoDTO user, Pageable pageable) {
        return repository.findAll((Specification<UserInfoDO>) (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(user.getUsername())) {
                Predicate predicateParent = builder.equal(root.get("username").as(Long.class), user.getUsername());
                list.add(predicateParent);
            }
            if (!ObjectUtils.isEmpty(user.getAge())) {
                Predicate predicateParent = builder.ge(root.get("age").as(Integer.class), user.getAge());
                list.add(predicateParent);
            }
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return query.where(predicates).getRestriction();
        }, pageable);
    }


}