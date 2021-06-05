package com.bms.tenants.util.ua;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAgentService {

	private final UserAgentClient userAgentClient;

	public Optional<UserAgent> getUserAgent() {

		Optional<UserAgent> userAgent = Optional.empty();
		ResponseEntity<UdgerResponse> obj = userAgentClient.getUserAgent("3fa59bfe261a6bbc45c9e66379ca8807",
				getUserAgent.get());
		if (Objects.isNull(obj.getBody()))
			return userAgent;

		userAgent = Optional.ofNullable(obj.getBody().getUserAgent());
		return userAgent;
	}

	public static final Supplier<String> getUserAgent = () -> {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		var ua = "";
		if (!ObjectUtils.isEmpty(request))
			ua = request.getHeader("User-Agent");

		return ua;
	};

}
