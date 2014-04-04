package com.taoeaten.et.server.main;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.core.domain.Command;
import com.taoeaten.et.server.handler.GameHandler;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	private static int PORT = 8080;

	public static void main(String[] args) {
		logger.info("start the game server");
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(factory);
		//GameHandler is a sharable handler
		final ChannelHandler handler = new GameHandler();
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encode", new ObjectEncoder());  
                pipeline.addLast("decode", new ObjectDecoder(ClassResolvers.cacheDisabled(Command.class.getClassLoader()))); 
                pipeline.addLast("game", handler);
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		 bootstrap.bind(new InetSocketAddress(PORT));
		logger.info("game server started at localhost:" + PORT );
	}
}
