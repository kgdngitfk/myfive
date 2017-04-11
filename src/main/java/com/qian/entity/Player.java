package com.qian.entity;

import java.io.Serializable;
import java.util.Stack;
import java.util.UUID;

/**
 * 玩家实体类
 * Created by wuhuaiqian on 17-2-23.
 */
public class Player implements Serializable,Comparable {
    private Integer playerId;
    private String username;
    private Integer rank;

    @Override
    public int compareTo(Object o) {
        Player another = (Player) o;
        return this.rank>=another.rank?1:-1;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
