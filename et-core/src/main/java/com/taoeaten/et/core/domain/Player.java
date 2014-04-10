package com.taoeaten.et.core.domain;

import com.taoeaten.et.protobuf.CommandProtobuf.PlayerStatus;

/**
 * player 
 * @author taoeaten
 *
 */
public class Player {
	private int id;
	
	private String name;
	
	private PlayerStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	
}



