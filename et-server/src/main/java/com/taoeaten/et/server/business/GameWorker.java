package com.taoeaten.et.server.business;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.protobuf.CommandProtobuf.cmdInfo;

/**
 * work!work!work!
 * @author taoeaten
 *
 */
public class GameWorker implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Channel channel;
	
	private cmdInfo command;
	
	public GameWorker(Channel channel,cmdInfo command){
		this.channel = channel;
		this.command = command;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public cmdInfo getCommand() {
		return command;
	}

	public void setCommand(cmdInfo command) {
		this.command = command;
	}

	public void run() {
		this.logger.info("work!work!work!");
		switch (this.command.getCmdNo()) {
		case 0:
			this.logger.info("now check the username & password....");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			channel.write(this.command);
			break;
		default:
			this.logger.info("now logging out....");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			channel.write(this.command);
			break;
		}
	}

}
