package clothes.shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs // rest docs 자동 설정
class MemberControllerTest  {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void member_get() throws Exception {
        // 조회 API -> 대상의 데이터가 있어야 합니다.
        mockMvc.perform(
                        get("/api/members/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo( // rest docs 문서 작성 시작
                        document("member", // 문서 조각 디렉토리 명
                                pathParameters( // path 파라미터 정보 입력
                                        parameterWithName("id").description("Member ID")
                                ),
                                responseFields( // response 필드 정보 입력
                                        fieldWithPath("id").description("id"),
                                        fieldWithPath("name").description("name"),
                                        fieldWithPath("email").description("email"),
                                        fieldWithPath("id").description("Member ID"),
                                        fieldWithPath("name").description("Member name"),
                                        fieldWithPath("email").description("Member email"),
                                        fieldWithPath("loginId").description("Member login ID"),
                                        fieldWithPath("password").description("Member password"),
                                        fieldWithPath("address").description("Member address"),
                                        fieldWithPath("phone").description("Member phone number"),
                                        fieldWithPath("userStatus").description("Member user status"),
                                        // 다른 필드들도 필요에 따라 추가
                                        fieldWithPath("rewardPointsList").description("List of reward points"),
                                        fieldWithPath("memberCoupons").description("List of member coupons"),
                                        fieldWithPath("orders").description("List of orders"),
                                        fieldWithPath("recentlyViewedItems").description("List of recently viewed items"),
                                        fieldWithPath("cart").description("Member's shopping cart"),
                                        fieldWithPath("memberWishList").description("List of member wish list items"),
                                        fieldWithPath("questions").description("List of member's questions"),
                                        fieldWithPath("reviews").description("List of member's reviews")
                                )

                        )
                )
        ;
    }
}