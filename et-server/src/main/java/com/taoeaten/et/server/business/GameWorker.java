package com.taoeaten.et.server.business;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoeaten.et.protobuf.CommandProtobuf.Et1001R;
import com.taoeaten.et.protobuf.CommandProtobuf.Et1002R;
import com.taoeaten.et.protobuf.CommandProtobuf.Et2001R;
import com.taoeaten.et.protobuf.CommandProtobuf.Et3001R;
import com.taoeaten.et.protobuf.CommandProtobuf.Et3002R;
import com.taoeaten.et.protobuf.CommandProtobuf.Et3003R;
import com.taoeaten.et.protobuf.CommandProtobuf.Et3004R;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessage;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessageR;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessageRType;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessageType;
import com.taoeaten.et.protobuf.CommandProtobuf.EtResponse;
import com.taoeaten.et.protobuf.CommandProtobuf.EtResponseStatus;
import com.taoeaten.et.protobuf.CommandProtobuf.Room;
import com.taoeaten.et.protobuf.CommandProtobuf.RoomStatus;

/**
 * work!work!work!
 * @author taoeaten
 *
 */
public class GameWorker implements Runnable{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Channel channel;
	
	private EtMessage command;
	
	public GameWorker(Channel channel,EtMessage command){
		this.channel = channel;
		this.command = command;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public EtMessage getCommand() {
		return command;
	}

	public void setCommand(EtMessage command) {
		this.command = command;
	}

	public void run() {
		this.logger.info("work!work!work!");
		EtMessageR.Builder builder = null; 
		EtResponse.Builder response = null;
		switch (this.command.getType().getNumber()) {
		case EtMessageType.Tet1001_VALUE://login 
			this.logger.info("now check the username & password....");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet1001R);
			Et1001R.Builder et1001Rbuilder = Et1001R.newBuilder();
			response = EtResponse.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("login success");
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("login failed");
			}
			et1001Rbuilder.setResponse(response);
			builder.setEt1001R(et1001Rbuilder);
			this.channel.write(builder.build());
			break;
		case EtMessageType.Tet1002_VALUE://logout
			this.logger.info("now logging out....");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet1002R);
			Et1002R.Builder et1002Rbuilder = Et1002R.newBuilder();
			response = EtResponse.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("logout success");
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("logout failed");
			}
			et1002Rbuilder.setResponse(response);
			builder.setEt1002R(et1002Rbuilder);
			this.channel.write(builder.build());
			break;
		case EtMessageType.Tet2001_VALUE://get room list
			this.logger.info("now getting room list....");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet2001R);
			response = EtResponse.newBuilder();
			Et2001R.Builder et2001Rbuilder = Et2001R.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("get roomlist success");
				Room.Builder roomBuiler = null;
				for(int i = 0;i<5;i++){
					roomBuiler = Room.newBuilder();
					roomBuiler.setId(i+1);
					roomBuiler.setName("Game Room" + (i+1));
					roomBuiler.setStatus(RoomStatus.WAIT);
					et2001Rbuilder.addRooms(roomBuiler);
				}
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("get roomlist failed");
			}
			et2001Rbuilder.setResponse(response);
			builder.setEt2001R(et2001Rbuilder);
			this.channel.write(builder.build());
			break;
		case EtMessageType.Tet3001_VALUE://join room
			this.logger.info("now  join the room.");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet3001R);
			response = EtResponse.newBuilder();
			Et3001R.Builder et3001Rbuilder = Et3001R.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("join the room successfully");
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("join the room failed");
			}
			et3001Rbuilder.setResponse(response);
			builder.setEt3001R(et3001Rbuilder);
			this.channel.write(builder.build());
			break;
		case EtMessageType.Tet3002_VALUE://leave room
			this.logger.info("now  leave the room.");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet3002R);
			response = EtResponse.newBuilder();
			Et3002R.Builder et3002Rbuilder = Et3002R.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("leave the room successfully");
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("leave the room failed");
			}
			et3002Rbuilder.setResponse(response);
			builder.setEt3002R(et3002Rbuilder);
			this.channel.write(builder.build());
			break;
		case EtMessageType.Tet3003_VALUE://ready to game
			this.logger.info("ready for game");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet3003R);
			response = EtResponse.newBuilder();
			Et3003R.Builder et3003Rbuilder = Et3003R.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("ready - success");
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("ready - failed");
			}
			et3003Rbuilder.setResponse(response);
			builder.setEt3003R(et3003Rbuilder);
			this.channel.write(builder.build());
			break;
		case EtMessageType.Tet3004_VALUE://start game
			this.logger.info("start game");
			builder = EtMessageR.newBuilder();
			builder.setType(EtMessageRType.Tet3004R);
			response = EtResponse.newBuilder();
			Et3004R.Builder et3004Rbuilder = Et3004R.newBuilder();
			if(this.logger != null){
				response.setStatus(EtResponseStatus.SUCCESS);
				response.setHint("start game  successfully");
			}else{
				response.setStatus(EtResponseStatus.FAILED);
				response.setHint("start game failed");
			}
			et3004Rbuilder.setResponse(response);
			builder.setEt3004R(et3004Rbuilder);
			this.channel.write(builder.build());
			break;
		default:
			this.logger.info("recogonized command");
			break;
		}
	}

}
