package org.george.pm.controller.chat;

import org.george.pm.model.ChatMessage;
import org.george.pm.model.Hr;
import org.george.pm.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WSController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleFrontEndMessage(Authentication authentication, ChatMessage message){
        Hr hr = (Hr) authentication.getPrincipal();
        message.setFrom(hr.getUsername());
        message.setFromNickName(hr.getName());
        message.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/chat", message);
    }
}
