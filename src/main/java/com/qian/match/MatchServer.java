package com.qian.match;

import com.alibaba.fastjson.JSON;
import com.qian.entity.Player;
import com.qian.entity.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

/**
 * Created by wuhuaiqian on 17-2-23.
 */
@ServerEndpoint("/match")
public class MatchServer {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private  Matcher matcher = new Matcher();

    @OnOpen
    public void onOpen(Session session){
        session.getAsyncRemote().sendText(session.hashCode()+"");
    }
    @OnMessage
    public void onMessage(Session session,String message){
        logger.debug("current Thread is{}",Thread.currentThread());
        RemoteEndpoint.Async asyncRemote = session.getAsyncRemote();
        List<Player> players = JSON.parseArray(message, Player.class);
        Player match = matcher.match(players.get(0));
        while(match==null){
            asyncRemote.sendText(Protocol.WAITE_FOR_MATCH);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            match = matcher.match(players.get(0));
        }
            session.getAsyncRemote().sendText(Protocol.WAITE_FOR_MATCH);
    }
}
