package com.bms.tenants.login;

import javax.validation.constraints.NotNull;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.tenants.login.repo.DeviceMetaData;
import com.bms.tenants.login.repo.DeviceMetaDataRepository;
import com.bms.tenants.util.ua.UserAgent;
import com.bms.tenants.util.ua.UserAgentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceMetaDataService {

	private final UserAgentService userAgentService;
	private final ConversionService conversionService;
	private final DeviceMetaDataRepository deviceMetaDataRepository;

	@Transactional
	public DeviceMetaData saveLoginDeviceMetaData() {
		UserAgent userAgent = userAgentService.getUserAgent().orElse(new UserAgent());
		var deviceMetaData = conversionService.convert(userAgent, DeviceMetaData.class);
		deviceMetaData = deviceMetaDataRepository.save(deviceMetaData);
		return deviceMetaData;
	}

}
