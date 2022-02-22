package com.dev.whale.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailVO {

    private String address;
    private String title;
    private String message;
}
