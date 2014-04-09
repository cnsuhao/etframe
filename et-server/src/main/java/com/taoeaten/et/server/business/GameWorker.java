package com.taoeaten.et.server.business;

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
public class GameWorker implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Channel channel;
	
	private Command command;
	
	public GameWorker(Channel channel,Command command){
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
		Command.Builder builder = null; 
		Command cmd = null;
		switch (this.command.getCmdNo()) {
		case 0://login 
			this.logger.info("now check the username & password....");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			channel.write(this.command);
			break;
		case 1://logout
			this.logger.info("now logging out....");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			channel.write(this.command);
			break;
		case 2://get room list
			this.logger.info("now getting room list....");
			builder = Command.newBuilder();
			builder.setCmdNo(CommandConstant.CMD_ROOMLIST);
			builder.setUserName(this.command.getUserName());
			Room.Builder roomBuilder = Room.newBuilder();
			for(int i=0;i<10;i++){
				roomBuilder.setRoomId(i+1);
				builder.addRooms(roomBuilder.build());
			}
			cmd = builder.build();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			channel.write(cmd);
			break;
		case 3://join room
			this.logger.info("now  join the room.");
			builder = Command.newBuilder();
			builder.setCmdNo(CommandConstant.CMD_ROOMLIST);
			builder.setRoomNo(this.command.getRoomNo());
			builder.setUserName(this.command.getUserName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cmd = builder.build();
			channel.write(cmd);
			break;
		case 4://ready to game
			this.logger.info("ready for game");
			builder = Command.newBuilder();
			builder.setCmdNo(CommandConstant.CMD_ROOMLIST);
			builder.setRoomNo(this.command.getRoomNo());
			builder.setUserName(this.command.getUserName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cmd = builder.build();
			channel.write(cmd);
			break;
		default:
			this.logger.info("recogonized command");
			break;
		}
	}

}
