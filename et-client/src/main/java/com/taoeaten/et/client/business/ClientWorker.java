package com.taoeaten.et.client.business;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.core.domain.CommandConstant;
import com.taoeaten.et.protobuf.CommandProtobuf.Command;
import com.taoeaten.et.protobuf.CommandProtobuf.Room;

/**
 * work!work!work!
 * @author taoeaten
 *
 */
public class ClientWorker implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Channel channel;
	
	private Command command;
	
	public ClientWorker(Channel channel,Command command){
		this.channel = channel;
		this.command = command;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void run() {
		this.logger.info("work!work!work!");
		switch (this.command.getCmdNo()) {
		case 0://login 
			this.logger.info("login successfully");
			break;
		case 1://logout
			this.logger.info("logout successfully");
			break;
		case 2://get room list
			this.logger.info("getting room list successfully");
			for (Room room : this.command.getRoomsList()) {
				this.logger.info(room.toString());
			}
			break;
		case 3://join room
			this.logger.info("join the room successfully");
			break;
		case 4://ready to game
			this.logger.info("I am ready ");
			break;
		default:
			this.logger.info("recogonized command");
			break;
		}
	}

}
