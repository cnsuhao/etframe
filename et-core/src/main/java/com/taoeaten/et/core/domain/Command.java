package com.taoeaten.et.core.domain;

import java.io.Serializable;

public class Command implements Serializable {

	private static final long serialVersionUID = -3986703404612524054L;

	private int cmdNo = -1;

	private String cmdContent = "";

	public int getCmdNo() {
		return cmdNo;
	}

	public void setCmdNo(int cmdNo) {
		this.cmdNo = cmdNo;
	}

	public String getCmdContent() {
		return cmdContent;
	}

	public void setCmdContent(String cmdContent) {
		this.cmdContent = cmdContent;
	}

	@Override
	public String toString() {
		return String.format("Command(cmdNo:%d,cmdContent:%s)", this.cmdNo,this.cmdContent);
	}
	
}
