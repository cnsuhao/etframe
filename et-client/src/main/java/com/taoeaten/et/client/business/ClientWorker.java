package com.taoeaten.et.client.business;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.client.GameClient;
import com.taoeaten.et.core.domain.Player;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessageR;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessageRType;
import com.taoeaten.et.protobuf.CommandProtobuf.PlayerStatus;
import com.taoeaten.et.protobuf.CommandProtobuf.Room;

/**
 * work!work!work!
 * @author taoeaten
 *
 */
public class ClientWorker implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	GameClient client;
	
	private Channel channel;
	
	private EtMessageR command;
	
	public ClientWorker(GameClient client, Channel channel,EtMessageR command){
		this.client = client;
		this.channel = channel;
		this.command = command;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public EtMessageR getCommand() {
		return command;
	}

	public void setCommand(EtMessageR command) {
		this.command = command;
	}

	public void run() {
		this.logger.info("work!work!work!");
		switch (this.command.getType().getNumber()) {
		case EtMessageRType.Tet1001R_VALUE://login 
			Player player = new Player();
			player.setId(1);
			player.setName("taoeaten");
			player.setStatus(PlayerStatus.ONLINE);
			this.client.setPlayer(player);
			this.logger.info("login successfully");
			break;
		case EtMessageRType.Tet1002R_VALUE://logout
			this.logger.info("logout successfully");
			break;
		case EtMessageRType.Tet2001R_VALUE://get room list
			this.logger.info("getting room list successfully");
			for (Room room : this.command.getEt2001R().getRoomsList()) {
				this.logger.info(room.toString());
			}
			break;
		case EtMessageRType.Tet3001R_VALUE://join room
			this.logger.info("join the room successfully");
			break;
		case EtMessageRType.Tet3002R_VALUE://leave room
			this.logger.info("leave the room successfully");
			break;
		case EtMessageRType.Tet3003R_VALUE://ready to game
			this.logger.info("I am ready ");
			break;
		case EtMessageRType.Tet3004R_VALUE://ready to game
			this.logger.info("start the game ");
			break;
		default:
			this.logger.info("recogonized command");
			break;
		}
	}

}
