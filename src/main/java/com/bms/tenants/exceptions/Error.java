package com.bms.tenants.exceptions;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Error implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String traceId;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private int series;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private long timestamp;
	private String message;
	private String debugMessage;
	private String method;
	private String path;
	private List<SubError> subErrors;

	private Error() {
		timestamp = System.currentTimeMillis();
	}

	public Error(HttpStatus status) {
		this();
		this.status = status;
		this.series = status.value();
	}

	public Error(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.series = status.value();
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public Error(HttpStatus status, String message, Throwable ex, String path, String method, String traceId) {
		this();
		this.status = status;
		this.series = status.value();
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
		this.path = path;
		this.method = method;
		this.traceId = traceId;
	}

}
