package com.taoeaten.et.client;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.client.constant.CommandConstant;
import com.taoeaten.et.core.domain.Command;


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
		Command loginCmd = new Command();
		loginCmd.setCmdNo(CommandConstant.CMD_LOGIN);
		loginCmd.setCmdContent("taoeaten login");
		this.channels.get("login").write(loginCmd);
		return 0;
	}
	
	public int logout(){
		this.logger.info("logout");
		if(!this.channels.containsKey("login")){
			return -1;
		}
		Command logOutCmd = new Command();
		logOutCmd.setCmdNo(CommandConstant.CMD_LOGOUT);
		logOutCmd.setCmdContent("taoeaten logout");
		this.channels.get("login").write(logOutCmd);
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
