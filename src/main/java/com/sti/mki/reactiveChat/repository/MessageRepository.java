package com.sti.mki.reactiveChat.repository;

import com.sti.mki.reactiveChat.domain.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
}
