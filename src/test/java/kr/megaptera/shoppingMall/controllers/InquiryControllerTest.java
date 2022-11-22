package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.annotations.MockMvcEncoding;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import kr.megaptera.shoppingMall.services.CreateInquiryService;
import kr.megaptera.shoppingMall.services.GetInquiryDetailService;
import kr.megaptera.shoppingMall.services.GetInquiryListService;
import kr.megaptera.shoppingMall.services.GetMyInquiryListService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InquiryController.class)
@MockMvcEncoding
class InquiryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetInquiryListService getInquiryListService;

    @MockBean
    private GetMyInquiryListService getMyInquiryListService;

    @MockBean
    private InquiryRepository inquiryRepository;

    @MockBean
    private CreateInquiryService createInquiryService;

    @MockBean
    private GetInquiryDetailService getInquiryDetailService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void inquiryList() throws Exception {
        Long userId = 1L;

        String accessToken = jwtUtil.encode(userId);

        List<Inquiry> inquiryList = List.of(
            Inquiry.fake(true, userId)
        );

        given(inquiryRepository.findAllByProductId(1L))
            .willReturn(inquiryList);

        mockMvc.perform(MockMvcRequestBuilders.get("/inquiries/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("")));

        verify(getInquiryListService).getInquiryList(1L, userId, 1);
    }

    @Test
    void myInquiryList() throws Exception {
        Long userId = 1L;

        String accessToken = jwtUtil.encode(userId);

        List<Inquiry> inquiryList = List.of(
            Inquiry.fake(true, userId),
            Inquiry.fake(true, userId)
        );

        given(inquiryRepository.findAllByProductId(1L))
            .willReturn(inquiryList);

        mockMvc.perform(MockMvcRequestBuilders.get("/inquiries/products/1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("")));

        verify(getMyInquiryListService).getMyInquiryList(1L, userId, 1);
    }

    @Test
    void createInquiry() throws Exception {
        Long userId = 1L;

        String accessToken = jwtUtil.encode(userId);

        mockMvc.perform(MockMvcRequestBuilders.post("/inquiries/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content("{" +
                    "\"userId\":\"1\"," +
                    "\"productId\":\"1\"," +
                    "\"content\":\"상품 문의\"," +
                    "\"userNickName\":\"Jo\"," +
                    "\"isSecret\":" + "" + true +
                    "}"))
            .andExpect(status().isCreated());

        verify(createInquiryService).createInquiry(any(), any(), any());
    }

    @Test
    void createInquiryWithBlankContent() throws Exception {
        Long userId = 1L;

        String accessToken = jwtUtil.encode(userId);

        mockMvc.perform(MockMvcRequestBuilders.post("/inquiries/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content("{" +
                    "\"userId\":\"1\"," +
                    "\"productId\":\"1\"," +
                    "\"content\":\"\"" +
                    "\"userNickName\":\"Jo\"," +
                    "\"isSecret\":" + "" + true +
                    "}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getInquiryDetail() throws Exception {
        Inquiry inquiry = Inquiry.fake(true, 1L);

        given(inquiryRepository.findById(1L))
            .willReturn(Optional.of(inquiry));

        Long userId = 1L;

        String accessToken = jwtUtil.encode(userId);

        given(getInquiryDetailService.detail(1L, userId))
            .willReturn(inquiry.toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/inquiries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "1"
            )));
    }

    @Test
    void getInquiryDetailWith() throws Exception {
        Inquiry inquiry = Inquiry.fake(true, 1L);

        given(inquiryRepository.findById(1L))
            .willReturn(Optional.of(inquiry));

        Long userId = 1L;

        String accessToken = jwtUtil.encode(userId);

        given(getInquiryDetailService.detail(1L, userId))
            .willReturn(inquiry.toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/inquiries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "문의 내용")));
    }
}
