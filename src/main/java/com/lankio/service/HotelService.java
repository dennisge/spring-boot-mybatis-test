/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.service;

import com.lankio.domain.Hotel;

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
}
