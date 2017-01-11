package com.qian.entity;
/**
 * 
 * @author wuhuaiqian
 * @date 2017年1月12日
 */
public class Message {
	private String type;
	private int x;
	private int y;
	private int myId;
	private int opponentId;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getMyId() {
		return myId;
	}
	public void setMyId(int myId) {
		this.myId = myId;
	}
	public int getOpponentId() {
		return opponentId;
	}
	public void setOpponentId(int opponentId) {
		this.opponentId = opponentId;
	}
	@Override
	public String toString() {
		return "Message [type=" + type + ", x=" + x + ", y=" + y + ", myId=" + myId + ", opponentId=" + opponentId
				+ "]";
	}
	

}
