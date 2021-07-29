package com.web.curation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.curation.request.model.Message;
import com.web.curation.response.BasicResponse;

import aj.org.objectweb.asm.TypeReference;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request", response = BasicResponse.class) })

@Controller
//@RequestMapping(value = "app")
@CrossOrigin(origins = { "*" })
public class NotifyController {
	
//	@Autowired
//	WebSocketHandler webSocketHandler;
//	@Autowired
//	WebSocketEventListener webSocketEventListener;
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    @ApiOperation(value = "메시지 보내기")
    public Message sendMessage(@Payload Message chatMessage) throws JsonMappingException, JsonProcessingException {
//		for(Message x: chatMessage)
//			System.out.println(x);
//		ObjectMapper objectMapper = new ObjectMapper();
//		byte[] result = objectMapper.readValue(chatMessage.toString(), byte[].class);
//		System.out.println(result);
		//뭐지 갑자기 되늰데?
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    @MessageMapping("/profile.doFollow")
    @SendToUser("queue/follow")
    public Message Follow(@Payload Message FollowMessage, String to) {
    	System.out.println("팔로우 타겟:" + FollowMessage.getContent());
    	System.out.println(to);
    	messagingTemplate.convertAndSend("user/queue/follow", FollowMessage); //왜 안됨?
    	return FollowMessage;
    }
}