package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.annotations.MockMvcEncoding;
import kr.megaptera.shoppingMall.dtos.AddressDto;
import kr.megaptera.shoppingMall.dtos.LoginResultDto;
import kr.megaptera.shoppingMall.exceptions.LoginFailed;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.services.LoginService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@MockMvcEncoding
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @SpyBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        AddressDto addressDto = new AddressDto(
            44637L, "울산광역시 남구 정광로 3번길 20", "울산광역시 남구 1233-12번지"
        );

        LoginResultDto loginResultDto = new LoginResultDto(
            "ACCESS.TOKEN", "조성환", "01031447938", addressDto
        );

        LoginResultDto userInformation = new LoginResultDto(
            "조성환", "01031447938", addressDto);

        given(loginService.login("tidls45", "Tjdghks245@"))
            .willReturn(loginResultDto);

        given(loginService.login("tidls45", "xxx"))
            .willThrow(new LoginFailed());

        given(loginService.login("tidls1234", "Tjdghks245@"))
            .willThrow(new LoginFailed());

        given(loginService.fetchUser(1L))
            .willReturn(userInformation);

        given(loginService.fetchUser(2L))
            .willThrow(new UserNotFoundException());
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identifier\":\"tidls45\"," +
                    " \"password\":\"Tjdghks245@\"" +
                    "}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("울산광역시 남구 정광로 3번길 20")
            ));
    }

    @Test
    void loginWithInvalidIdentifier() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identifier\":\"tidls1234\"," +
                    " \"password\":\"Tjdghks245@\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("아이디 혹은 비밀번호가 일치하지 않습니다")
            ));
    }

    @Test
    void loginWithInvalidPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identifier\":\"tidls45\"," +
                    " \"password\":\"xxx\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("아이디 혹은 비밀번호가 일치하지 않습니다")
            ));
    }

    @Test
    void fetchUser() throws Exception {
        String accessToken = jwtUtil.encode(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/session/me")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("울산광역시 남구 정광로 3번길 20")
            ));
    }
}
