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
public class Results {

	public static <T> Result<T> ok() {

		return ok(null);
	}

	public static <T> Result<T> ok(T data) {

		return new Result<>(200, null, data);
	}

	public static <T> Result<T> badRequest(int code, String msg) {

		return new Result<>(code, msg, null);
	}

}
