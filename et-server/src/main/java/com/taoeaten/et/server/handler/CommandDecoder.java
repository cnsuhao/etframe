package com.taoeaten.et.server.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.taoeaten.et.core.domain.Command;


public class CommandDecoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext arg0, Channel arg1,
			ChannelBuffer arg2) throws Exception {
		Command cmd = new Command();
		return cmd;
	}

}
