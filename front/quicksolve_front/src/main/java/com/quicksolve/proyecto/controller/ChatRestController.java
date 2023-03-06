package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.ChatDTO;
import com.quicksolve.proyecto.service.ChatService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatRestController {

    @Autowired
    private ChatService chatService;


    @PostMapping("/create/chat")
    public @ResponseBody ResponseEntity<?> createChat(@RequestBody String jsonSessionChat){
        JSONObject json = new JSONObject(jsonSessionChat);
        String user = json.getString("user");
        String sessionId = json.getString("sessionId");

        ChatDTO chatDTO = new ChatDTO(sessionId, user);
        chatService.saveChat(chatDTO);
        return new ResponseEntity<>("Connection established", HttpStatus.OK);
    }

    @PostMapping(value = "/close/chat")
    public @ResponseBody ResponseEntity<?> cerrarChat(@RequestBody String sessionId){
        ChatDTO chatDTO = chatService.showChatBy(sessionId);
        chatDTO.setClosed(true);
        chatService.saveChat(chatDTO);
        return new ResponseEntity<>("Closed chat", HttpStatus.OK);
    }
}
