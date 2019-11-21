package com.sti.mki.reactiveChat.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Message {
    @Id
    private String id;
    private String nickName;
    private String text;
}
