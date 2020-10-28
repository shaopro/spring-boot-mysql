package com.github.mysql.controller;

import com.github.mysql.pojo.orm.DeptInfoDO;
import com.github.mysql.pojo.orm.EmpInfoDO;
import com.github.mysql.pojo.dto.DeptInfoDTO;
import com.github.mysql.repository.IDeptInfoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 创建时间为 下午5:08 2020/2/16
 * 项目名称 spring-boot-mysql
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
public class SpecificationController {

    @Resource
    private IDeptInfoRepository repository;

    @GetMapping("dept/spec")
    public Page<DeptInfoDO> listDeptInfo(DeptInfoDTO deptInfo, Pageable pageable) {
        return repository.findAll((Specification<DeptInfoDO>) (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!ObjectUtils.isEmpty(deptInfo.getDeptId())) {
                Predicate predicateParent = builder.equal(root.get("deptId").as(Long.class), deptInfo.getDeptId());
                list.add(predicateParent);
            }
            if (StringUtils.isNotBlank(deptInfo.getName())) {
                Predicate predicateParent = builder.equal(root.get("name").as(String.class), deptInfo.getName());
                list.add(predicateParent);
            }
            return query.where(list.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }


    @GetMapping("dept/spec/{name}")
    public DeptInfoDO findByName(@PathVariable String name) {
        Optional<DeptInfoDO> optional = repository.findOne((Specification<DeptInfoDO>) (root, query, builder) -> {
            Path<String> path = root.get("name");
            return builder.equal(path.as(String.class), name);
        });
        return optional.orElse(new DeptInfoDO());
    }

    @GetMapping("dept/spec/sub/{name}")
    public DeptInfoDO findByNameAnd(@PathVariable String name) {
        Optional<DeptInfoDO> optional = repository.findOne((Specification<DeptInfoDO>) (root, query, builder) -> {
            Path<String> parentPath = root.get("name");
            Predicate predicateParent = builder.equal(parentPath.as(String.class), name);

            Subquery<EmpInfoDO> subQuery = query.subquery(EmpInfoDO.class);
            Root<EmpInfoDO> subRoot = subQuery.from(EmpInfoDO.class);
            Path<Integer> agePath = subRoot.get("age");
            subQuery.select(subRoot).where(builder.ge(agePath.as(Integer.class), 5));


            return builder.and(predicateParent, subQuery.getRestriction());


//            Subquery<EmpInfoDO> subQuery = query.subquery(EmpInfoDO.class);
//            Root<EmpInfoDO> subRoot = subQuery.from(EmpInfoDO.class);
//            Path<Integer> agePath = subRoot.get("age");
//            subQuery.select(subRoot).where(builder.ge(agePath.as(Integer.class), 5));
//            Path<String> parentPath = root.get("name");
//            Predicate parentPredicate = builder.equal(parentPath, name);
//            return builder.and(parentPredicate);
        });
        return optional.orElse(new DeptInfoDO());
    }


}
