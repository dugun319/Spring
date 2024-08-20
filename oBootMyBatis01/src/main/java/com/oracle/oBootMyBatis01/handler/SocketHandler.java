package com.oracle.oBootMyBatis01.handler;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocketHandler extends TextWebSocketHandler {
	//WebSocketSession container Map
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	//WebSocketSession ID & member container Map
	HashMap<String, String> sessionUserMap = new HashMap<>();
	
	JSONObject jsonUser = null;
	
	//WebSocket Connection #2 - 메시지를 수신하면 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		//super.handleTextMessage(session, message);
		
		String msg				= message.getPayload();
		System.out.println("SocketHandler handleTextMessage() msg -> " + msg);
		
		JSONObject jsonObject	= jsonToObjectParser(msg);
		
		String msgType			= (String) jsonObject.get("type");
		System.out.println("SocketHandler handleTextMessage() msgType -> " + msgType);
		
		
	}
	
	//WebSocket Connection #1
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("SocketHandler afterConnectionEstablished(WebSocketSession session) is started ");
		super.afterConnectionEstablished(session);
		
		// Connection socket 을 Map 에 등록
		sessionMap.put(session.getId(), session);
		
		
	}
	
	// WebSocket Connection #3
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("SocketHandler afterConnectionClosed() is started ");
		super.afterConnectionEstablished(session);
		
		// Web socket close
		sessionMap.remove(session.getId(), session);		
		super.afterConnectionClosed(session, status);
	}
	
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser 		= new JSONParser();
		
		JSONObject jsonpObject = null;
		
		try {
			jsonpObject = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return jsonpObject;
	}
}
