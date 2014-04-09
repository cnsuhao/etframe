package com.taoeaten.et.server.card.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example:card game room
 * @author taoeaten
 *
 */
public class Room {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private int roomNo;
	
	private int playNum;
	
	private int status;
	
	private List<Player> players = new ArrayList<Player>();
	
	public int join(Player player){
		this.logger.info(player.toString() + " join the room"+ this.toString());
		this.players.add(player);
		return 0;
	}
	
	public int leave(Player player){
		this.players.remove(player);
		return 0;
	}
	
	public int startGame(){
		return 0;
	}
	
	public int endGame(){
		return 0;
	}

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", playNum=" + playNum + ", status="
				+ status + ", players=" + players + "]";
	}

}
