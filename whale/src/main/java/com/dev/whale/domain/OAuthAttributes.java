package com.dev.whale.domain;

import lombok.Builder;
import com.dev.whale.domain.model.User;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String nickname;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name, String email, String nickname) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
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
        return OAuthAttributes.builder() //네이버 Oauth 객체 생성
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .username(name)
                .email(email)
                .nickname(nickname)
//                .role(Role.)
                .build();
    }
}
