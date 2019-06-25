/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lankio.domain.Hotel;
import com.lankio.mapper.HotelMapper;
import com.lankio.service.HotelService;

/**
 * 
 *
 * //TODO comments
 *
 *
 * @author: DENNIS
 *
 **/
@Component
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelMapper hotelMapper;

	@Override
	public Hotel selectByCityId(int cityId) {

		return hotelMapper.selectByCityId(cityId);
	}

	@Override
	public void updateHotelNameByCityId(String cityName, int cityId) {

		hotelMapper.updateHotelNameByCityId(cityName, cityId);
	}

}
