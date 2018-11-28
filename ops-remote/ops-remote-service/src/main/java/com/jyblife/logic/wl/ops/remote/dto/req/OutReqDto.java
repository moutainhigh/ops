package com.jyblife.logic.wl.ops.remote.dto.req;

import java.io.Serializable;
import java.util.HashMap;

public class OutReqDto extends HashMap<String, Object> implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	public static final String CMD = "cmd";
	
	public static final String DATA = "data";
	
	public Object getCmd() {
        return this.get(CMD);
    }
	
	public void setCmd(Object cmd) {
        this.put(CMD, cmd);
    }
	
	public Object getData() {
        return this.get(DATA);
    }
	
	public void setData(Object data) {
        this.put(DATA, data);
    }

}
