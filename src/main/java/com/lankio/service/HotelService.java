/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.service;

import java.util.List;

import com.lankio.domain.Hotel;
import com.lankio.domain.HotelCriteria;

/**
 * 
 *
 * //TODO comments
 *
 *
 * @author: DENNIS
 *
 **/
public interface HotelService {

	Hotel selectByCityId(int cityId);

	void updateHotelNameByCityId(String cityName, int cityId);

	List<Hotel> listHotel(HotelCriteria criteria);
}
