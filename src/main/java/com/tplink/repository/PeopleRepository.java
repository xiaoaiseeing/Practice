package com.tplink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.tplink.constant.StringConstant;
import com.tplink.entity.People;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/22 16:12
 */
@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    /**
     * 登录
     * @param name 用户姓名
     * @param password 用户密码
     * @return 通过name和password查找的结果
     */
    @Query(StringConstant.LOGIN_SQL)
    @Modifying
    List<People> login(@Param(StringConstant.NAME) String name,
                       @Param(StringConstant.PASSWORD) String password);

    /**
     * 查找用户名是否重复
     * @param name 用户姓名
     * @return 通过name查找的结果
     */
    @Query(StringConstant.SELECT_NAME_SQL)
    @Modifying
    List<People> selectName(@Param(StringConstant.NAME) String name);
}
