package com.bonc.quickframework.bean;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

public class ResultInfo {
	private boolean success;
	private String message;
	private Map<String, Object> params;

	//
	public ResultInfo() {
		this(false);
	}
	public ResultInfo(Boolean success){
		this(success, null);
	}
	public ResultInfo(Boolean success, String message) {
		this(success, message, null);
	}

	public ResultInfo(Boolean success, String message,
			Map<String, Object> params) {
		this.success = success;
		this.message = message == null ? "" : message;
		this.params = params == null ? new HashMap<String, Object>() : params;
	}

	//
	/*public JSONObject toJSON() {
		return JSONObject.fromObject(this);
	}*/
	public String toJSONString() {
		//return JSONObject.fromObject(this).toString();
		ObjectMapper mapper = new ObjectMapper(); 
        try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultInfo(false,"序列化json异常").toJSONString();
		}
	}
	// get,set
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 111);
		System.err.println(new ResultInfo(false, "test", map).toJSONString());
	}
}
