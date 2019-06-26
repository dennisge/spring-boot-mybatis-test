/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

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
@Mapper
public interface HotelMapper {

	Hotel selectByCityId(int cityId);

	@Update("update hotel set name = #{cityName} where city = #{cityId}")
	int updateHotelNameByCityId(@Param("cityName") String cityName, @Param("cityId") int cityId);

	@SelectProvider(type = HotelSqlProvider.class, method = "listHotel")
	List<Hotel> listHotel(HotelCriteria criteria);
}
