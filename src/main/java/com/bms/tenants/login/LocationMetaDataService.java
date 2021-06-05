package com.bms.tenants.login;

import java.util.Objects;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bms.tenants.login.repo.LocationMetaData;
import com.bms.tenants.login.repo.LocationMetaDataRepository;
import com.bms.tenants.util.location.GeoLocationResponse;
import com.bms.tenants.util.location.GeoLocationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationMetaDataService {

	private final GeoLocationService geoLocationService;
	private final LocationMetaDataRepository locationMetaDataRepository;
	private final ConversionService conversionService;

	@Transactional
	public LocationMetaData saveLoginLocMetaData() {
		String ipAddress = getIpAddress.get();
		var geoLocationResponse = geoLocationService.getIPLocation(ipAddress);
		LocationMetaData loc = conversionService.convert(geoLocationResponse, LocationMetaData.class);
		loc.setIpAddress(ipAddress);
		loc = locationMetaDataRepository.save(loc);
		return loc;
	}

	public static final Supplier<String> getIpAddress = () -> {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String clientIp;
		String clientXForwardedForIp = request.getHeader("x-forwarded-for");
		if (Objects.nonNull(clientXForwardedForIp)) {
			clientIp = parseXForwardedHeader(clientXForwardedForIp);
		} else {
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	};

	private static String parseXForwardedHeader(String header) {
		return header.split(" *, *")[0];
	}

}
