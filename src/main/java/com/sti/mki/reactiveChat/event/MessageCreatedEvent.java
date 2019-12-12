package com.sti.mki.reactiveChat.event;

import com.sti.mki.reactiveChat.domain.Message;
import org.springframework.context.ApplicationEvent;

/**
 * @author Q-MKI
 */
public class MessageCreatedEvent extends ApplicationEvent {
    public MessageCreatedEvent(Message source) {
        super(source);
    }
}
