package com.taoeaten.et.client.handler;


import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.core.domain.Command;



/**
 * GameHandler for Client
 * @author taoeaten
 *
 */
public class GameHandler extends SimpleChannelHandler{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		this.logger.info("channel connected");
//		String username = "taoeaten";
//		ChannelBuffer buffer = ChannelBuffers.buffer(username.length());
//		buffer.writeBytes(username.getBytes());
		/**
		 * obj codec 
		 */
		this.logger.info("send a client cmd obj.");
		Command cmd = new Command();
		cmd.setCmdNo(1);
		cmd.setCmdContent("taoeaten login");
		e.getChannel().write(cmd);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Command cmd = (Command) e.getMessage();
		this.logger.info("message received - " + cmd.toString());
	}

}
