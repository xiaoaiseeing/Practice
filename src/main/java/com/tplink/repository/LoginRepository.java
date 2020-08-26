package com.tplink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.tplink.constant.StringConstant;
import com.tplink.entity.LoginNumber;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/25 11:03
 */

@Repository
public interface LoginRepository extends JpaRepository<LoginNumber, Long> {
    /**
     * 统计一个用户24小时内登录了多少次
     * @param time 当前UTC时间往前24小时的时间点
     * @param name 用户姓名
     * @return 查找的符合条件的结果集
     */
    @Query(StringConstant.LOGIN_NUM_SQL)
    @Modifying
    List<LoginNumber> countLogin(@Param(StringConstant.LOGIN_TIME) long time,
                                        @Param(StringConstant.NAME) String name);
}
