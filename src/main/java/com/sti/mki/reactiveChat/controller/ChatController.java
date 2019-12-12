package com.sti.mki.reactiveChat.controller;

import com.sti.mki.reactiveChat.domain.Message;
import com.sti.mki.reactiveChat.service.MessageService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    Publisher<Message> getById(@PathVariable("id") String id) {
        return this.messageService.get(id);
    }

    @PostMapping
    Publisher<ResponseEntity<Message>> create(@RequestBody Message message) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(name -> this.messageService
                        .create(name, message.getText())
                        .map(p -> ResponseEntity.created(URI.create("/message/" + p.getId()))
                                .contentType(mediaType)
                                .build()));
    }
}