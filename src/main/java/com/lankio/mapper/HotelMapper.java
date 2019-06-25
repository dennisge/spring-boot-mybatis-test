/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
@Mapper
public interface HotelMapper {

	Hotel selectByCityId(int cityId);

	@Update("update hotel set name = #{cityName} where city = #{cityId}")
	int updateHotelNameByCityId(@Param("cityName") String cityName, @Param("cityId") int cityId);
}
