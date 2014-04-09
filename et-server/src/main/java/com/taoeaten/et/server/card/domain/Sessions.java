package com.taoeaten.et.server.card.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * stimulate centralized session
 * @author taoeaten
 *
 */
public class Sessions {
	
	private static Map<String, PlayerSession> sessions = new ConcurrentHashMap<String, PlayerSession>();
	
	public static PlayerSession getPlayerSession(String userName){
		if(sessions.containsKey(userName)){
			return sessions.get(userName);
		}
		return null;
	}
	
	public static void deletePlayerSession(String userName){
		if(sessions.containsKey(userName)){
			sessions.remove(userName);
		}
	}
	
	public static void clearup(){
		sessions.clear();
	}
}
