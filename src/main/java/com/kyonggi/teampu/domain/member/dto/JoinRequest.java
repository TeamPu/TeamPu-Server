package com.kyonggi.teampu.domain.member.dto;

import lombok.Getter;

@Getter
public class JoinRequest {
    private String loginId;
    private String password;
    private String name;
    private String college;
    private String department;
    private String phoneNumber;
    private String email;
    private String type;
}