package com.taoeaten.et.client.main;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import com.taoeaten.et.client.handler.GameHandler;
import com.taoeaten.et.core.domain.Command;

public class Main {
	public static void main(String[] args) {
		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		ClientBootstrap bootstrap = new ClientBootstrap(factory);
		//GameHandler is a sharable handler
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encode", new ObjectEncoder());  
                pipeline.addLast("decode", new ObjectDecoder(ClassResolvers.cacheDisabled(Command.class.getClassLoader()))); 
                pipeline.addLast("game", new GameHandler());
				return pipeline;
			}
		});
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		bootstrap.connect(new InetSocketAddress("localhost", 8080));
	}
}
