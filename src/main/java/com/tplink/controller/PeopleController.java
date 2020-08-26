package com.tplink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import com.tplink.constant.NumConstant;
import com.tplink.constant.StringConstant;
import com.tplink.entity.LoginNumber;
import com.tplink.entity.People;
import com.tplink.repository.LoginRepository;
import com.tplink.repository.PeopleRepository;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/22 16:18
 */
@Controller
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private LoginRepository loginRepository;

    @ResponseBody
    @PostMapping(StringConstant.REGISTER_MAPPING)
    public String register(@RequestParam(StringConstant.NAME) String name,
                           @RequestParam(StringConstant.PASSWORD) String password,
                           @RequestParam(StringConstant.EMAIL) String email,
                           @RequestParam(StringConstant.ADDRESS) String address) {
        // 先从库中查询是否用户名相同
        List<People> peopleList = peopleRepository.selectName(name);
        if (peopleList.isEmpty()) {
            People people = new People(name, password, email, address);
            People result = peopleRepository.save(people);
            if (result != null) {
                return StringConstant.SUCCESS_SIGN;
            } else {
                return StringConstant.FAIL_SIGN;
            }
        } else {
            return StringConstant.NAME_REPEAT;
        }
    }

    @ResponseBody
    @PostMapping(StringConstant.LOGIN_MAPPING)
    public String login(@RequestParam(StringConstant.NAME) String name,
                        @RequestParam(StringConstant.PASSWORD) String password) {
        List<People> peopleList = peopleRepository.login(name, password);
        if (peopleList.isEmpty()) {
            return StringConstant.FAIL_SIGN;
        } else {
            return StringConstant.SUCCESS_SIGN;
        }
    }

    @ResponseBody
    @PostMapping(StringConstant.LOGIN_NUM_MAPPING)
    public int loginNum(@RequestParam(StringConstant.NAME) String name) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(StringConstant.UTC));
        long currentTime = calendar.getTimeInMillis();
        long limitTime = currentTime - NumConstant.DAY_TIME;
        String userName = name;
        List<LoginNumber> loginList = loginRepository.countLogin(limitTime, userName);
        return loginList.size();
    }
}
