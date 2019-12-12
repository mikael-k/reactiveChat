package com.sti.mki.reactiveChat.service;

import com.sti.mki.reactiveChat.domain.Message;
import com.sti.mki.reactiveChat.event.MessageCreatedEvent;
import com.sti.mki.reactiveChat.repository.MessageRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final ApplicationEventPublisher publisher;
    private final MessageRepository messageRepository;

    MessageService(ApplicationEventPublisher publisher, MessageRepository messageRepository) {
        this.publisher = publisher;
        this.messageRepository = messageRepository;
    }

    public Flux<Message> all() { // <3>
        return this.messageRepository.findAll();
    }

    public Mono<Message> get(String id) { // <4>
        return this.messageRepository.findById(id);
    }

    public Mono<Message> update(String id, String nickName, String text) {
        return this.messageRepository
                .findById(id)
                .map(p -> new Message(p.getId(), nickName, text))
                .flatMap(this.messageRepository::save);
    }

    public Mono<Message> delete(String id) {
        return this.messageRepository
                .findById(id)
                .flatMap(p -> this.messageRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Message> create(String nickName, String text) {
        return this.messageRepository
                .save(new Message(null, nickName, text))
                .doOnSuccess(profile -> this.publisher.publishEvent(new MessageCreatedEvent(profile)));
    }
}