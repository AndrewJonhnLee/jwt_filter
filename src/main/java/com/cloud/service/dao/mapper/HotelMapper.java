package com.cloud.service.dao.mapper;

import com.cloud.service.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelMapper {

    Hotel selectByCityId(int city_id);

}