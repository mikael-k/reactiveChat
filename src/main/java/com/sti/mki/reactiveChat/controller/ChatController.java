package com.sti.mki.reactiveChat.controller;

import com.sti.mki.reactiveChat.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatForm(Model model) {
        model.addAttribute("message", new Message());
        return "chat";
    }

    @PostMapping("/chat")
    public String chatSubmit(@ModelAttribute Message message) {
        message.getNickName();
        return "result";
    }

}