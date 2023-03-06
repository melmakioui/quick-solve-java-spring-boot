package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.ChatDTO;
import com.quicksolve.proyecto.dto.ChatMessageDTO;
import com.quicksolve.proyecto.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/chats/tech")
    public String showAllChatsTech(Model model){
        model.addAttribute("chats", chatService.showAllChats());
        return "view/chats";
    }

    @MessageMapping("/chat/group/{to}")
    public void sendMessageToGroup(@DestinationVariable String to, ChatMessageDTO message) {
        if (!chatService.showChatBy(to).isClosed()) chatService.sendMessageToGroup(to, message);
    }
}
