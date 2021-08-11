package com.web.curation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.curation.alarm.model.Message;
import com.web.curation.response.BasicResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request", response = BasicResponse.class) })

@Controller
//@RequestMapping(value = "app")
@CrossOrigin(origins = { "*" })
public class WSController {

//	@Autowired
//	WebSocketHandler webSocketHandler;
//	@Autowired
//	WebSocketEventListener webSocketEventListener;
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/alarm/{email}")
//	@SendTo("topic/{email}")
	public void alarmMessage(@DestinationVariable("email") String email, @Payload Message alarmMessage) {
		System.out.println("알람타겟: " + email);
		messagingTemplate.convertAndSend("/topic/" + email, alarmMessage);
//		messagingTemplate.convertAndSendToUser(email, "topic/", alarmMessage);
//		return alarmMessage;
	}

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	@ApiOperation(value = "메시지 보내기")
	public Message sendMessage(@Payload Message chatMessage) throws JsonMappingException, JsonProcessingException {
//		for(Message x: chatMessage)
//			System.out.println(x);
//		ObjectMapper objectMapper = new ObjectMapper();
//		byte[] result = objectMapper.readValue(chatMessage.toString(), byte[].class);
//		System.out.println(result);
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public Message addUser(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/follow")
//    @SendToUser("queue/follow")
	public void Follow(@Payload Message FollowMessage) {
		System.out.println("팔로우 타겟:" + FollowMessage.getReceiver());
		messagingTemplate.convertAndSend("/queue/" + FollowMessage.getReceiver(), FollowMessage);
//    	return FollowMessage;
	}
	
}