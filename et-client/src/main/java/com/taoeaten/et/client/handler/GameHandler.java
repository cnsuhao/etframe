package com.taoeaten.et.client.handler;


import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.client.GameClient;
import com.taoeaten.et.core.domain.Command;
import com.taoeaten.et.protobuf.CommandProtobuf.cmdInfo;



/**
 * GameHandler for Client
 * @author taoeaten
 *
 */
public class GameHandler extends SimpleChannelHandler{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private GameClient client = null;
	
	public GameHandler(GameClient client){
		this.client = client;
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		this.logger.info("channel connected");
		this.client.addChannel("login", e.getChannel());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		/**
		 * object
		 */
//		Command cmd = (Command) e.getMessage();
//		this.logger.info("message received - " + cmd.toString());
		
		/**
		 * protobuf
		 */
		cmdInfo cmd = (cmdInfo) e.getMessage();
		this.logger.info("message received - " + cmd.toString());
		
	}

}
