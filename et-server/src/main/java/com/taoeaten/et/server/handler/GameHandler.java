package com.taoeaten.et.server.handler;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChildChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.protobuf.CommandProtobuf.cmdInfo;

/**
 * GameHandler for Server
 * @author taoeaten
 *
 */

@Sharable
public class GameHandler extends SimpleChannelHandler{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>();
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getChannel().close();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		/**
		 * object 
		 */
//		Command cmd = (Command) e.getMessage();
//		this.logger.info("message received - " + cmd.toString());
//		e.getChannel().write(cmd);
		
		/**
		 * protobuf 
		 */
		cmdInfo cmd = (cmdInfo) e.getMessage();
		this.logger.info("message received - " + cmd.toString());
		e.getChannel().write(cmd);
//		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
//		this.logger.info("message received :"+ buffer.toString(Charset.defaultCharset()));
//		Channel channel =  e.getChannel();
//		String address = "I am ("+e.getChannel().getRemoteAddress().toString() +")";
//		ChannelBuffer cb = ChannelBuffers.buffer(address.length());
//		cb.writeBytes(address.getBytes());
//		channel.write(cb);
	}

	

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		this.logger.info("GameChannel"+ this);
		this.channels.put(e.getChannel().getRemoteAddress().toString(), e.getChannel());
		this.logger.info("connected from " + ctx.getChannel().getRemoteAddress() + " add channels success. now size:"+ channels.size());
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		this.logger.info("channel disconnect");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		super.channelClosed(ctx, e);
	}

	@Override
	public void childChannelClosed(ChannelHandlerContext ctx,
			ChildChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.childChannelClosed(ctx, e);
	}
	
	
	
}
