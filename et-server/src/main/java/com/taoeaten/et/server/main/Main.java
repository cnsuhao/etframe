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
				/**
				 * object codec
				 */
//				pipeline.addLast("encode", new ObjectEncoder());  
//                pipeline.addLast("decode", new ObjectDecoder(ClassResolvers.cacheDisabled(Command.class.getClassLoader()))); 
                
                /**
				 * protobuf codec
				 */
				pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());  
		        //构造函数传递要解码成的类型  
				pipeline.addLast("protobufDecoder", new ProtobufDecoder(CommandProtobuf.cmdInfo.getDefaultInstance()));  
		 //编码用  
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
		 bootstrap.bind(new InetSocketAddress(PORT));
		logger.info("game server started at localhost:" + PORT );
	}
}
