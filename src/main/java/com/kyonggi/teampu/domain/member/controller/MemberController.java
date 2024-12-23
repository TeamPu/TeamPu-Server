package com.kyonggi.teampu.domain.member.controller;

import com.kyonggi.teampu.domain.auth.domain.CustomMemberDetails;
import com.kyonggi.teampu.domain.member.dto.JoinRequest;
import com.kyonggi.teampu.domain.member.dto.MemberInfoResponse;
import com.kyonggi.teampu.domain.member.dto.MyPageRequest;
import com.kyonggi.teampu.domain.member.dto.MyPageResponse;
import com.kyonggi.teampu.domain.member.service.MemberService;
import com.kyonggi.teampu.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ApiResponse<Void> join(@RequestBody JoinRequest request) {
        memberService.join(request);

        return ApiResponse.ok();
    }

    @GetMapping("/profile")
    public ApiResponse<MemberInfoResponse> getProfile(@AuthenticationPrincipal CustomMemberDetails customMemberDetails) {
        Long memberId = customMemberDetails.getMember().getId();

        return ApiResponse.ok(memberService.findById(memberId));
    }

    @GetMapping("/members/{memberId}")
    public ApiResponse<MemberInfoResponse> getMemberInfo(@PathVariable Long memberId) {
        MemberInfoResponse response = memberService.findById(memberId);

        return ApiResponse.ok(response);
    }

    @GetMapping("/profile/{login_id}")
    public ApiResponse<MyPageResponse.MyPageDTO> findByLoginId(@PathVariable("login_id") String loginId) {
        MyPageResponse.MyPageDTO memberDTO = memberService.findByLoginId(loginId);
        return ApiResponse.ok(memberDTO);
    }

    @PatchMapping("/profile")
    public ApiResponse<MyPageResponse.MyPageDTO> updateProfile(
            @AuthenticationPrincipal CustomMemberDetails customMemberDetails,
            @RequestBody MyPageRequest.UpdateProfileDTO updateRequest) {
        String loginId = customMemberDetails.getMember().getLoginId();
        MyPageResponse.MyPageDTO updatedMember = memberService.updateProfile(loginId, updateRequest);
        return ApiResponse.ok(updatedMember);
    }
}
