package pl.zielinska.trashAlert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@RunWith(SpringRunner.class)
//@WebMvcTest(WebControllerTest.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;


    public void testHomePage() throws Exception {
        mockMvc.perform(formLogin("/login").user("sanzie").password("123456"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
