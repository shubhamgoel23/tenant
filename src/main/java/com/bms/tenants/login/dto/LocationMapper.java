package com.bms.tenants.login.dto;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.bms.tenants.config.MapperCustomConfig;
import com.bms.tenants.login.repo.LocationMetaData;
import com.bms.tenants.util.location.GeoLocationResponse;

@Mapper(config = MapperCustomConfig.class)
public interface LocationMapper extends Converter<GeoLocationResponse, LocationMetaData> {

	@Override
	LocationMetaData convert(GeoLocationResponse geoResponse);

}
