package com.taoeaten.et.protobuf;

import com.taoeaten.et.protobuf.CommandProtobuf.EtMessage;
import com.taoeaten.et.protobuf.CommandProtobuf.EtMessageType;

/**
 * tools to generate commands for netty
 * @author taoeaten
 *
 */
public class CmdPbTools {
	public static EtMessage genLoginCmd(String useranme,String password){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet1001);
		CommandProtobuf.Et1001.Builder et1001Builder = CommandProtobuf.Et1001.newBuilder();
		et1001Builder.setUsername(useranme);
		et1001Builder.setPassword(password);
		builder.setEt1001(et1001Builder);
		return builder.build();
	}
	
	public static EtMessage genLogoutCmd(int userId){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet1002);
		CommandProtobuf.Et1002.Builder et1002Builder = CommandProtobuf.Et1002.newBuilder();
		et1002Builder.setUserId(userId);
		builder.setEt1002(et1002Builder);
		return builder.build();
	}
	
	public static EtMessage genRoomlistCmd(){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet2001);
		CommandProtobuf.Et2001.Builder et2001Builder = CommandProtobuf.Et2001.newBuilder();
		builder.setEt2001(et2001Builder);
		return builder.build();
	}
	
	public static EtMessage genJoinRoomCmd(int userId,int roomId){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet3001);
		CommandProtobuf.Et3001.Builder et3001Builder = CommandProtobuf.Et3001.newBuilder();
		et3001Builder.setUserId(userId);
		et3001Builder.setRoomId(roomId);
		builder.setEt3001(et3001Builder);
		return builder.build();
	}
	
	public static EtMessage genLeaveRoomCmd(int userId,int roomId){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet3002);
		CommandProtobuf.Et3002.Builder et3002Builder = CommandProtobuf.Et3002.newBuilder();
		et3002Builder.setUserId(userId);
		et3002Builder.setRoomId(roomId);
		builder.setEt3002(et3002Builder);
		return builder.build();
	}
	
	public static EtMessage genReadyCmd(int userId,int roomId){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet3003);
		CommandProtobuf.Et3003.Builder et3003Builder = CommandProtobuf.Et3003.newBuilder();
		et3003Builder.setUserId(userId);
		et3003Builder.setRoomId(roomId);
		builder.setEt3003(et3003Builder);
		return builder.build();
	}
	
	public static EtMessage genStartCmd(int userId,int roomId){
		CommandProtobuf.EtMessage.Builder builder = CommandProtobuf.EtMessage.newBuilder();
		builder.setType(EtMessageType.Tet3004);
		CommandProtobuf.Et3004.Builder et3004Builder = CommandProtobuf.Et3004.newBuilder();
		et3004Builder.setUserId(userId);
		et3004Builder.setRoomId(roomId);
		builder.setEt3004(et3004Builder);
		return builder.build();
	}
	
}
