package com.dev.whale.config.auth.dto;

import com.dev.whale.domain.model.Role;
import lombok.Builder;
import com.dev.whale.domain.model.User;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private String nameAttributeKey;
    private String username;
    private String email;
    private String nickname;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String username, String email, String nickname) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        // google, naver 정보를 registrationId로 받아온다
        /*
        switch (registrationId) {
            case "naver":
                return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
        */
        if (registrationId.equals("naver")) {
            return ofNaver("id", attributes);
        }
        return null;
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName,
                                          Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder() //네이버 Oauth 객체 생성 (네이버에서 전해주는 response에서 id, email, nickname 세팅)
                .username((String) response.get("id"))
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .naverLoginYn("Y")
                .role(Role.USER)
                .build();
    }
}
