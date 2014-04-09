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
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.protobuf.CommandProtobuf;
import com.taoeaten.et.server.handler.GameHandler;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	private static int LOGIC_PORT = 8080;
	private static int CHAT_PORT = 8088;

	public static void main(String[] args) {
		/**
		 * logic server
		 */
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
                
                /**
				 * protobuf codec
				 */
				pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());  
				pipeline.addLast("protobufDecoder", new ProtobufDecoder(CommandProtobuf.Command.getDefaultInstance()));  
				pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());  
				pipeline.addLast("protobufEncoder", new ProtobufEncoder());  
                
                /**
                 * business handler
                 */
                pipeline.addLast("game", handler);
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		 bootstrap.bind(new InetSocketAddress(LOGIC_PORT));
		logger.info("game server started at localhost:" + LOGIC_PORT );
		
		/**
		 * chat server
		 */
	}
}
