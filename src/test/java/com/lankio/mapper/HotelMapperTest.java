/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelMapperTest {

	@Autowired
	private HotelMapper hotelMapper;

	@Test
	public void selectByCityId() {

		Hotel hotel = hotelMapper.selectByCityId(1);

		Assert.assertNotNull(hotel);

	}

	@Test
	public void updateHotelNameByCityId() {

		String cityName = "测试test-city-name";

		int result = hotelMapper.updateHotelNameByCityId(cityName, 1);

		assertEquals(1, result);
	}

	@Test
	public void listHotel() {

		HotelCriteria criteria = new HotelCriteria();
		criteria.setCity(1);
		List<Hotel> hotels = hotelMapper.listHotel(criteria);

		assertNotNull(hotels);
	}

}
