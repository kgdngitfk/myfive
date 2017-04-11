package com.qian.match;

import com.qian.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 实现玩家对战的匹配
 * Created by wuhuaiqian on 17-2-23.
 */
public class Matcher {
    //already matched players;
    private Map<Integer,Integer> matchDone = new HashMap();
    private ConcurrentLinkedQueue<Player> waitting = new ConcurrentLinkedQueue();

    public Matcher() {
    }

    public void add(Player newPlayer){
        waitting.add(newPlayer);
    }
    public Player match(Player player){
        if(waitting.isEmpty()){
             waitting.add(player);
             return null;
        }
        else return  waitting.remove();
    }

}
