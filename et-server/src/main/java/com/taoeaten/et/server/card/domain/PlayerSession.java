package com.taoeaten.et.server.card.domain;

/**
 * Example: card game Player session
 * @author taoeaten
 *
 */
public class PlayerSession {

	public int playTime;
	
	public String userName;

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "PlayerSession [userName=" + userName + ", playTime=" + playTime
				+ "]";
	}
	
}
