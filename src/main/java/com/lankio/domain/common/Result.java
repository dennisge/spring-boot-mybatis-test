/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.domain.common;

/**
 * 
 *
 * //TODO comments
 *
 *
 * @author: DENNIS
 *
 **/
public class Result<T> {

	private int code;

	private String msg;

	private T data;

	public Result() {
		super();
	}

	public Result(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Result(ResultCode resultCode) {
		this(resultCode.getCode(), resultCode.getMsg(), null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
