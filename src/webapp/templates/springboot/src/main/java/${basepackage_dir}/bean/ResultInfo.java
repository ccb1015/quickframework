package ${basepackage}.bean;

import java.util.HashMap;
import java.util.Map;

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
}

