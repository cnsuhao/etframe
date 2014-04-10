package com.taotean.et.client.test;

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

import com.taoeaten.et.client.GameClient;
import com.taoeaten.et.client.handler.GameHandler;
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
				 * protobuf codec
				 */
				pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());  
				pipeline.addLast("protobufDecoder", new ProtobufDecoder(CommandProtobuf.EtMessageR.getDefaultInstance()));  
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
			if(cmdNo == 0){
				client.login("taoeaten","password");
			}else if(cmdNo==1){
				client.logout();
				break;
			}else if(cmdNo == 2){
				client.getRoomList();
			}else if(cmdNo == 3){
				System.out.println("input roomNo:");
				int roomNo = sc.nextInt();
				client.joinRoom(roomNo);
			}else if(cmdNo == 4){
				System.out.println("input roomNo:");
				int roomNo = sc.nextInt();
				client.leaveRoom(roomNo);
			}else if(cmdNo == 5){
				System.out.println("input roomNo:");
				int roomNo = sc.nextInt();
				client.ready(roomNo);
			}else if(cmdNo == 6){
				System.out.println("input roomNo:");
				int roomNo = sc.nextInt();
				client.start(roomNo);
			}
		}
		
	}
}
