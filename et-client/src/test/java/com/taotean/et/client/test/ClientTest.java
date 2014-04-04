package com.taotean.et.client.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import com.taoeaten.et.client.GameClient;
import com.taoeaten.et.client.handler.GameHandler;
import com.taoeaten.et.core.domain.Command;
import com.taoeaten.et.protobuf.CommandProtobuf;

public class ClientTest {
	public static void main(String[] args) {
		/**
		 * et part
		 */
		final GameClient client = new GameClient();
		
		/**
		 * netty part
		 */
		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		ClientBootstrap bootstrap = new ClientBootstrap(factory);
		//GameHandler is a sharable handler
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
                pipeline.addLast("game", new GameHandler(client));
				return pipeline;
			}
		});
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		bootstrap.connect(new InetSocketAddress("localhost", 8080));
		
		/**
		 * Test
		 */
		//login
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("input cmdNo:");
			int cmdNo = sc.nextInt();
			if(cmdNo == 1){
				client.login();
			}else{
				client.logout();
				break;
			}
		}
		
	}
}
