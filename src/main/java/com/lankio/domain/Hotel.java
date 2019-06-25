/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.domain;

/**
 * 
 *
 *
 *
 * @author: DENNIS
 *
 **/
public class Hotel {
	private Integer city;

	private String name;

	private String address;

	private String zip;

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return getCity() + "," + getName() + "," + getAddress() + "," + getZip();
	}
}
