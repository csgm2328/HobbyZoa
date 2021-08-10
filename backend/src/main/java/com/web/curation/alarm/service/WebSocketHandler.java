package com.web.curation.alarm.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler{
	Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		String senderId = getUserId(session);
		System.out.println(session.getId());
		if(senderId != null) {
			System.out.println(new Date() + " : " +senderId + "연결 됨");
			users.put(senderId, session);
		}
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
		String senderId = getUserId(session);
		//특정 유저에게 보내기
		String msg = message.getPayload();
		if(msg != null) {
			String[] arr = msg.split(",");
			System.out.println(new Date() + " : " + arr.toString());
			if(arr != null && arr.length == 4) {
				String type = arr[0];
				String target = arr[1]; // m_id 저장
				String content = arr[2];
				String url = arr[3];
				WebSocketSession targetSession = users.get(target);  // 메시지를 받을 세션 조회
				
				// 실시간 접속시
				if(targetSession!=null) {
					// ex: [&분의일] 신청이 들어왔습니다.
					TextMessage tmpMsg = new TextMessage("<a target='_blank' href='"+ url +"'>[<b>" + type + "</b>] " + content + "</a>" );
					targetSession.sendMessage(tmpMsg);
				}
			}
		}
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		String senderId = getUserId(session);
		if(senderId!=null) {	// 로그인 값이 있는 경우만
			users.remove(senderId);
			session.close();
			System.out.println(new Date() + " : " +senderId + "연결 종료됨");
		}
	}
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		super.handleTransportError(session, exception);
		System.out.println(new Date() + " : " + session.getId() + " 익셉션 발생: " + exception.getMessage());
	}
	private String getUserId(WebSocketSession session) {
		Map<String, Object> httpSession =  session.getAttributes();
		String id = (String) httpSession.get("email");
		return id;
	}
}
