package com.taoeaten.et.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.core.domain.Player;
import com.taoeaten.et.protobuf.CmdPbTools;
import com.taoeaten.et.protobuf.CommandProtobuf;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessage;


/**
 * Game client
 * @author taoeaten
 *
 */
public class GameClient {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Player player = null;
	
	private Map<String , Channel> channels = new ConcurrentHashMap<String, Channel>();
	
	public int login(String username,String password){
		this.logger.info("login");
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genLoginCmd(username, password);
		this.channels.get("login").write(message);
		return 0;
	}
	
	public int logout(){
		this.logger.info("logout");
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genLogoutCmd(this.player.getId());
		ChannelFuture future = this.channels.get("login").write(message);
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				GameClient.this.channels.remove("login");
			}
		});
		return 0;
	}
	
	public int joinRoom(int roomNo){
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genJoinRoomCmd(this.player.getId(),roomNo);
		this.channels.get("login").write(message);
		return 0;
	}
	
	public int leaveRoom(int roomNo){
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genLeaveRoomCmd(this.player.getId(),roomNo);
		this.channels.get("login").write(message);
		return 0;
	}
	
	public int ready(int roomNo){
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genReadyCmd(this.player.getId(),roomNo);
		this.channels.get("login").write(message);
		return 0;
	}
	
	public int start(int roomNo){
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genStartCmd(this.player.getId(),roomNo);
		this.channels.get("login").write(message);
		return 0;
	}
	
	public int getRoomList(){
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		EtMessage message = CmdPbTools.genRoomlistCmd();
		this.channels.get("login").write(message);
		return 0;
	}
	
	/**
	 * maintain the Map for client channels
	 * @param address
	 * @param channel
	 */
	public void addChannel(String address, Channel channel){
		this.channels.put(address, channel);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
