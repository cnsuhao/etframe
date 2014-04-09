package com.taoeaten.et.client.handler;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.client.GameClient;
import com.taoeaten.et.client.business.ClientWorker;
import com.taoeaten.et.protobuf.CommandProtobuf.Command;



/**
 * GameHandler for Client
 * @author taoeaten
 *
 */
public class GameHandler extends SimpleChannelHandler{
	private static final int THREADPOOL_SIZE = 10;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ExecutorService executors = Executors.newFixedThreadPool(THREADPOOL_SIZE);
	
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
		 * protobuf
		 */
		Command cmd = (Command) e.getMessage();
		this.logger.info("message received - " + cmd.toString());
		ClientWorker worker = new ClientWorker(e.getChannel(), cmd);
		executors.execute(worker);
	}

}
