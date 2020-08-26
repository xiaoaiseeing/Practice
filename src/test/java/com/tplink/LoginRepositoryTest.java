package com.tplink;

import com.tplink.constant.NumConstant;
import com.tplink.constant.StringConstant;
import com.tplink.controller.PeopleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.Assert.assertEquals;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/26 9:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeopleApplication.class)
@WebAppConfiguration
public class LoginRepositoryTest {
    @Autowired
    private PeopleController peopleController;

    private MockMvc mockMvc;
    private Logger logger = LoggerFactory.getLogger(LoginRepositoryTest.class);

    @Before
    public void setup() {
        // 构造MockMVC对象
        mockMvc = MockMvcBuilders.standaloneSetup(peopleController).build();
    }

    @Test
    public void loginNumTest() {
        try {
            String result =mockMvc.perform(MockMvcRequestBuilders.post(StringConstant.LOGIN_NUM_MAPPING)
                    .param(StringConstant.NAME, StringConstant.REGISTER_TEST_NAME))
                    .andReturn().getResponse().getContentAsString();
            int loginNum = Integer.parseInt(result);
            // 数据库中有一条张三一天前的登录记录，和一天内的登录记录，他一天内的登录次数应为1
            assertEquals(NumConstant.ONE, loginNum);
        } catch (Exception e) {
            logger.error(StringConstant.LOGIN_REPOSITORY_TEST_EXCEPTION_MESSAGE, e);
        }
    }
}
