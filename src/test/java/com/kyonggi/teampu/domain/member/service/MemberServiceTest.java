//package com.kyonggi.teampu.domain.member.service;
//
//import com.kyonggi.teampu.domain.member.domain.Member;
//import com.kyonggi.teampu.domain.member.domain.MemberType;
//import com.kyonggi.teampu.domain.member.dto.JoinRequest;
//import com.kyonggi.teampu.domain.member.dto.MemberInfoResponse;
//import com.kyonggi.teampu.domain.member.dto.MyPageRequest;
//import com.kyonggi.teampu.domain.member.dto.MyPageResponse;
//import com.kyonggi.teampu.domain.member.repository.MemberRepository;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static com.kyonggi.teampu.global.exception.ErrorCode.MEMBER_NOT_FOUND;
//import static com.kyonggi.teampu.global.exception.ErrorCode.MEMBER_NOT_FOUND_BY_LOGIN_ID;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@SpringBootTest
//@Transactional
//@ActiveProfiles("test")
//@DisplayName("MemberService 테스트")
//class MemberServiceTest {
//    @Autowired
//    private MemberService memberService;
//    @Autowired
//    private MemberRepository memberRepository;
//    private Member member;
//
//    @BeforeEach
//    void setUp() {
//
//        // 회원 가입 요청을 Member 객체로 변환
//        member = Member.builder()
//                .loginId("loginId1")
//                .password("password1")
//                .name("member1")
//                .college("소프트웨어경영대학")
//                .department("AI컴퓨터공학부")
//                .phoneNumber("010-1234-5678")
//                .email("member1@kyonggi.ac.kr")
//                .type(MemberType.valueOf("UNDERGRADUATE"))
//                .build();
//        memberRepository.save(member);
//    }
//
//    @Test
//    @DisplayName("회원을 저장한다.")
//    void saveMember() {
//        // given
//        JoinRequest joinRequest = new JoinRequest();
//        joinRequest.setLoginId("loginId2");
//        joinRequest.setPassword("password2");
//        joinRequest.setName("member2");
//        joinRequest.setCollege("소프트웨어경영대학");
//        joinRequest.setDepartment("AI컴퓨터공학부");
//        joinRequest.setPhoneNumber("010-9876-5432");
//        joinRequest.setEmail("member2@kyonggi.ac.kr");
//        joinRequest.setType("UNDERGRADUATE");
//
//        // when
//        memberService.join(joinRequest);
//        Member findMember = memberRepository.findByLoginId("loginId2").get();
//
//        // then
//        assertThat(findMember.getLoginId()).isEqualTo(joinRequest.getLoginId());
//    }
//
//    @Test
//    @DisplayName("PK로 회원을 조회한다.")
//    void findById() {
//        // when
//        MemberInfoResponse findMember = memberService.findById(member.getId());
//
//        // then
//        assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 PK로 회원을 조회했을 때 예외를 반환한다.")
//    void findByIdNotExist() {
//        // when, then
//        assertThatThrownBy(() -> memberService.findById(0L))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining(MEMBER_NOT_FOUND.getMessage());
//    }
//
//    @Test
//    @DisplayName("아이디로 회원을 조회한다.")
//    void findByLoginId() {
//        // when
//        MyPageResponse.MyPageDTO findMember = memberService.findByLoginId(member.getLoginId());
//
//        // then
//        assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 아이디로 회원을 조회했을 때 예외를 반환한다.")
//    void findByLoginIdNotExist() {
//        // when, then
//        assertThatThrownBy(() -> memberService.findByLoginId("notExistLoginId"))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining(MEMBER_NOT_FOUND_BY_LOGIN_ID.getMessage());
//    }
//
//    @Test
//    @DisplayName("회원 프로필을 업데이트한다.")
//    void updateProfile() {
//        // given
//        MyPageRequest.UpdateProfileDTO updateRequest = new MyPageRequest.UpdateProfileDTO(
//                "updatedName",
//                "010-2222-3333",
//                "updatedEmail@kyonggi.ac.kr"
//        );
//
//        // when
//        MyPageResponse.MyPageDTO updatedProfile = memberService.updateProfile(member.getLoginId(), updateRequest);
//
//        // then
//        assertThat(updatedProfile.getName()).isEqualTo("updatedName");
//        assertThat(updatedProfile.getPhoneNumber()).isEqualTo("010-2222-3333");
//        assertThat(updatedProfile.getEmail()).isEqualTo("updatedEmail@kyonggi.ac.kr");
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 아이디로 프로필을 업데이트했을 때 예외를 반환한다.")
//    void updateProfileNotExist() {
//        // given
//        MyPageRequest.UpdateProfileDTO updateRequest = new MyPageRequest.UpdateProfileDTO(
//                "updatedName",
//                "010-2222-3333",
//                "updatedEmail@kyonggi.ac.kr"
//        );
//
//        // when, then
//        assertThatThrownBy(() -> memberService.updateProfile("notExistLoginId", updateRequest))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining(MEMBER_NOT_FOUND_BY_LOGIN_ID.getMessage());
//    }
//}