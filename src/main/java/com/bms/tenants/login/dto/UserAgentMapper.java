package com.bms.tenants.login.dto;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.bms.tenants.config.MapperCustomConfig;
import com.bms.tenants.login.repo.DeviceMetaData;
import com.bms.tenants.util.ua.UserAgent;

@Mapper(config = MapperCustomConfig.class)
public interface UserAgentMapper extends Converter<UserAgent, DeviceMetaData> {

	@Override
	DeviceMetaData convert(UserAgent userAgent);

}
