package com.qian.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.qian.entity.Message;

/**
 * 
 * @author wuhuaiqian
 *
 */
@ServerEndpoint("/five")
public class FiveChessServer {
	private static int nextId = 0;
	private static boolean isWaite = true;// true 有用户等待匹配对手
	private static Map<Integer, Session> clients = new HashMap<Integer, Session>();

	// private Queue clientQueue = new LinkedList<>();
	public FiveChessServer() {
	}

	@OnOpen
	public void onopen(Session session) {
		/**
		 * 简易用户匹配，前一个用户决定后其对手的id
		 */
		System.out.println(session);
		clients.put(nextId, session);
		Message message = new Message();
		message.setType("match");
		if (isWaite) {
			message.setMyId(nextId);
			message.setOpponentId(++nextId);
			isWaite = !isWaite;
		} else {
			message.setMyId(nextId);
			message.setOpponentId(nextId - 1);
			nextId++;
			isWaite = !isWaite;
		}
		session.getAsyncRemote().sendText(JSON.toJSONString(message));
	}

	@OnClose
	public void onclose(Session session) {
		System.out.println("close....someone exit");

	}

	@OnMessage
	public void onMessage(String msg) {
		Message obj = JSON.parseObject(msg, Message.class);
		obj.setType("step");
		//将走棋步步数发送给其对手
		clients.get(obj.getOpponentId()).getAsyncRemote().sendText(JSON.toJSONString(obj));
	}
}