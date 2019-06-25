/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lankio.domain.Hotel;
import com.lankio.domain.common.Result;
import com.lankio.domain.common.Results;
import com.lankio.service.HotelService;

/**
 * 
 *
 *
 * @author: DENNIS
 *
 **/
@RestController
@RequestMapping("hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@GetMapping("city/{cityId}")
	public Result<Hotel> getHotelByCityId(@PathVariable Integer cityId) {

		Hotel hotel = hotelService.selectByCityId(cityId);

		return Results.ok(hotel);
	}

	/**
	 * Form update
	 * 
	 * @param hotel
	 * @return
	 */
	@PutMapping("form-update")
	public Result<String> updateHotelNameByCityId(Hotel hotel) {

		hotelService.updateHotelNameByCityId(hotel.getName(), hotel.getCity());

		return Results.ok();
	}

	/**
	 * Request body update
	 * 
	 * @param hotel
	 * @return
	 */
	@PutMapping("body-update")
	public Result<String> updateHotelName(@RequestBody Hotel hotel) {

		hotelService.updateHotelNameByCityId(hotel.getName(), hotel.getCity());

		return Results.ok();
	}

}
