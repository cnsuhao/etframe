package com.taoeaten.et.server.card.domain;

/**
 * Example: card game Player
 * @author taoeaten
 *
 */
public class Player {
	
	private String username;
	
	private int score;
	
	private int status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Player [username=" + username + ", score=" + score
				+ ", status=" + status + "]";
	}
	
	
}
