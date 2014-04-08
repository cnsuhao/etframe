package com.taoeaten.et.client;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.client.constant.CommandConstant;
import com.taoeaten.et.protobuf.CommandProtobuf;


/**
 * Game client
 * @author taoeaten
 *
 */
public class GameClient {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Map<String , Channel> channels = new ConcurrentHashMap<String, Channel>();
	
	public int login(){
		this.logger.info("login");
		if(!this.channels.containsKey("login")){
			return -1;
		}
		
		/**
		 * protobuf command
		 */
		CommandProtobuf.cmdInfo.Builder builder = CommandProtobuf.cmdInfo.newBuilder();
		builder.setCmdNo(CommandConstant.CMD_LOGIN);
		builder.setCmdContent("taoeaten login");
		this.channels.get("login").write(builder.build());
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
		CommandProtobuf.cmdInfo.Builder builder = CommandProtobuf.cmdInfo.newBuilder();
		builder.setCmdNo(CommandConstant.CMD_LOGOUT);
		builder.setCmdContent("taoeaten logout");
		ChannelFuture future = this.channels.get("login").write(builder.build());
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				GameClient.this.channels.remove("login");
			}
		});
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
	
}
