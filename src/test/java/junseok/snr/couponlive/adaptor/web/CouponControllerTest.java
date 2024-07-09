package junseok.snr.couponlive.adaptor.web;

import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.adaptor.in.web.coupon.CouponController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CouponService couponService;

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

        mockMvc.perform(post("/v1/coupons/issue")
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

        mockMvc.perform(post("/v1/coupons/issue")
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

        mockMvc.perform(post("/v1/coupons/issue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].code", containsInAnyOrder("userId", "couponId")))
                .andExpect(jsonPath("$[*].message", containsInAnyOrder("must be greater than or equal to 1", "must be greater than or equal to 1")));

    }
}