package com.tplink;

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
 * @since 2020/8/25 16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeopleApplication.class)
@WebAppConfiguration
public class PeopleRepositoryTest {
    @Autowired
    private PeopleController peopleController;

    private MockMvc mockMvc;
    private Logger logger = LoggerFactory.getLogger(PeopleRepositoryTest.class);

    @Before
    public void setup() {
        // 构造MockMVC对象
        mockMvc = MockMvcBuilders.standaloneSetup(peopleController).build();
    }

    @Test
    public void registerTest() {
        try {
            String result =mockMvc.perform(MockMvcRequestBuilders.post(StringConstant.REGISTER_MAPPING)
                    .param(StringConstant.NAME, StringConstant.REGISTER_TEST_NAME)
                    .param(StringConstant.PASSWORD, StringConstant.REGISTER_TEST_PASSWORD)
                    .param(StringConstant.EMAIL, StringConstant.REGISTER_TEST_EMAIL)
                    .param(StringConstant.ADDRESS, StringConstant.REGISTER_TEST_ADDRESS))
                    .andReturn().getResponse().getContentAsString();
            // 第一次注册时，此处断言应为正确
            assertEquals(StringConstant.SUCCESS_SIGN, result);
            // 重复注册时，此处断言应为正确
            assertEquals(StringConstant.NAME_REPEAT, result);
        } catch (Exception e) {
            logger.error(StringConstant.PEOPLE_REPOSITORY_TEST_EXCEPTION_MESSAGE, e);
        }
    }

    @Test
    public void loginTest() {
        try {
            String result = mockMvc.perform(MockMvcRequestBuilders.post(StringConstant.LOGIN_MAPPING)
                    .param(StringConstant.NAME, StringConstant.REGISTER_TEST_NAME)
                    .param(StringConstant.PASSWORD, StringConstant.REGISTER_TEST_PASSWORD))
                    .andReturn().getResponse().getContentAsString();
            // 张三已经在数据库中，此处断言应为正确
            assertEquals(StringConstant.SUCCESS_SIGN, result);
        } catch (Exception e) {
            logger.error(StringConstant.PEOPLE_REPOSITORY_TEST_EXCEPTION_MESSAGE, e);
        }
    }
}
