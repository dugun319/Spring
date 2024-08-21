package com.oracle.oBootMyBatis01.handler;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

//@SuppressWarning
//  이건 컴파일러가 일반적으로 경고하는 내용 중	"이건 하지마" 하고 제외시킬 때 쓰임
//	따라서 어떤 경고를 제외시킬지 옵션
//	1. all : 모든 경고를 억제
//	2. cast : 캐스트 연산자 관련 경고 억제
//	3. dep-ann : 사용하지 말아야 할 주석 관련 경고 억제
//	4. deprecation : 사용하지 말아야 할 메소드 관련 경고 억제
//	5. fallthrough : switch문에서의 break 누락 관련 경고 억제
//	6. finally : 반환하지 않는 finally 블럭 관련 경고 억제
//	7. null : null 분석 관련 경고 억제
//	8. rawtypes : 제네릭을 사용하는 클래스 매개 변수가 불특정일 때의 경고 억제
//	9. unchecked : 검증되지 않은 연산자 관련 경고 억제
//	10. unused : 사용하지 않는 코드 관련 경고 억제

@Slf4j
@Component
public class SocketHandler extends TextWebSocketHandler {
	//WebSocket Session container Map
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	//WebSocket Session ID & member container Map
	HashMap<String, String> sessionUserMap = new HashMap<>();
	
	JSONObject jsonUser = null;
	
	//WebSocket Connection #2 - 메시지를 수신하면 실행
	@SuppressWarnings("unchecked")
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		//super.handleTextMessage(session, message);
		
		String msg				= message.getPayload();
		System.out.println("SocketHandler handleTextMessage() msg -> " + msg);
		
		JSONObject jsonObject	= jsonToObjectParser(msg);
		
		String msgType			= (String) jsonObject.get("type");
		System.out.println("SocketHandler handleTextMessage() msgType -> " + msgType);
		
		switch(msgType) {
			case "message":
				jsonUser = new JSONObject(sessionUserMap);
				System.out.printf("JSONUser : %s", jsonUser);
				
				//전송대상으로 기준으로 분기 -> ALL or Opposite
				String yourName = (String) jsonObject.get("yourName");
				System.out.println("SocketHandler handleTextMessage() yourName -> " + yourName);
				
				//WHOLE
				if(yourName.equals("ALL")) {
					for(String key : sessionMap.keySet()) {
						WebSocketSession wss = sessionMap.get(key);
						try {
							System.out.println("SocketHandler handleTextMessage() message key -> " + key);
							System.out.println("SocketHandler handleTextMessage() message wss -> " + wss);
							wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					// 개인 전송 대상자(yourName is personal sessionId)
					// Opposite
					System.out.println("SocketHandler handleTextMessage() OPPOSITE session ID -> " + yourName);
					WebSocketSession wss1 = sessionMap.get(yourName);
					try {
						wss1.sendMessage(new TextMessage(jsonObject.toJSONString()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String meName = (String) jsonObject.get("sessionId");
					WebSocketSession wss2 = sessionMap.get(meName);
					System.out.println("SocketHandler handleTextMessage() My session ID -> " + meName);

					try {
						wss2.sendMessage(new TextMessage(jsonObject.toJSONString()));
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				break;
				
				
				
			case "userDelete":
				log.info("SocketHandler handleTextMessage() userDelete is started");
				System.out.println("SocketHandler handleTextMessage() userDelete session.getId() -> " + session.getId());
				
				//Closing web socket
				sessionMap.remove(session.getId());
				
				//Closing sessionUserMap
				sessionUserMap.remove(session.getId());
				break;
				
				
		
			case "userSave":
				// sessionUserMap 에 sessionID 와 userName 등록 
				String sessionId	= (String) jsonObject.get("sessionId");
				String userName		= (String) jsonObject.get("userName");
				String saveStatus	= (String) jsonObject.get("saveStatus");
				
				if(saveStatus.equals("Create")) {
					sessionUserMap.put(sessionId, userName);
					System.out.println("=================================================");
		     	    System.out.println("== sessionUserMap 저장내용 조회하여 arrayJsonUser에 ==");
		     	    System.out.println("==  각각의 JSONObject jsonUser로  변환            ==");
		     	    System.out.println("== 1. type : userSave                          ==");
		     	    System.out.println("== 2. sessionId : sessionUserMap.sessionId     ==");
		     	    System.out.println("== 3. userName  : sessionUserMap.userName      ==");
		     	    System.out.println("=================================================");
				} else {
					// Delete
					System.out.println("SocketHandler handleTextMessage() userDelete is started");
					System.out.println("SocketHandler handleTextMessage() userDelete session.getId() -> " + session.getId());
					
					//Web Socket Closing
					sessionMap.remove(session.getId());
					
					// sessionUserMap Closing
					sessionUserMap.remove(session.getId());
					// break;
				}
				
				// User가 새로 등록되면 무조건 새로 생성 -> append가 더 어려움
				JSONArray arrayJsonUser = new JSONArray();
				System.out.println("== 1. type : userSave                          ==");
				Iterator<String> mapIter = sessionUserMap.keySet().iterator();
				System.out.println("== 2. sessionId : sessionUserMap.sessionId     ==");
	     	    System.out.println("== 3. userName  : sessionUserMap.userName      ==");
	     	    while(mapIter.hasNext()) {
	     	    	String key		= mapIter.next();
	     	    	String value	= sessionUserMap.get(key);
	     	    	System.out.println("Key : Value ==> " + key + " : " + value);
	     	    	jsonUser		= new JSONObject();
	     	    	jsonUser.put("type", "userSave");
	     	    	jsonUser.put("sessionId", key);
	     	    	jsonUser.put("userName", value);	     	    	
	     	    	arrayJsonUser.add(jsonUser);
	     	    }
	     	    
	     	   System.out.println("====== session Get & User Message Sending ========");
	     	   System.out.printf("arrayJsonUser : %s", arrayJsonUser);
	     	   
	     	   // 전체에 User 등록
	     	   for(String key : sessionMap.keySet()) {
	     		   WebSocketSession wss = sessionMap.get(key);
	     		   try {
					wss.sendMessage(new TextMessage(arrayJsonUser.toJSONString()));
	     		   } catch (Exception e) {
	     			   e.printStackTrace();
	     		   }
	     	   }
	     	   break;		
		}		
	}
	
	//WebSocket Connection #1
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("SocketHandler afterConnectionEstablished(WebSocketSession session) is started ");
		super.afterConnectionEstablished(session);
		
		// Connection socket 을 Map 에 등록
		sessionMap.put(session.getId(), session);
		
		JSONObject jsonObject = new JSONObject();
		
		// 대상 : Client
		jsonObject.put("type", "getId");
		jsonObject.put("sessionId", session.getId());
		System.out.println("SocketHandler afterConnectionEstablished() jsonObject.toJSONString() -> " + jsonObject.toJSONString());
		
		// Session Server Register -> Socket Server, Send to Client
		session.sendMessage(new TextMessage(jsonObject.toJSONString()));
		
		
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
