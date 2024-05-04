package junseok.snr.couponlive.intrastructure.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testIssueCoupon() throws Exception {
        String jsonRequest = """
                                {
                                  "userId": 1,
                                  "couponId": 1
                                }
                            """;

        mockMvc.perform(post("/v1/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void testIssueCouponFail_1() throws Exception {
        String jsonRequest = """
                                {
                                  "userId": 1
                                }
                            """;

        mockMvc.perform(post("/v1/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("couponId"))
                .andExpect(jsonPath("$.message").value("must be greater than or equal to 1"));

    }


    @Test
    void testIssueCouponFail_2() throws Exception {
        String jsonRequest = "{}";

        mockMvc.perform(post("/v1/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].code").value("userId"))
                .andExpect(jsonPath("$[0].message").value("must be greater than or equal to 1"))
                .andExpect(jsonPath("$[1].code").value("couponId"))
                .andExpect(jsonPath("$[1].message").value("must be greater than or equal to 1"));

    }
}