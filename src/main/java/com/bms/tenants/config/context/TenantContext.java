package com.bms.tenants.config.context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {

	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	private TenantContext() {

	}

	public static void setTenant(String tenant) {
		log.debug("Setting tenant to {}", tenant);
		CONTEXT.set(tenant);

	}

	public static String getTenant() {
		return CONTEXT.get();

	}

	public static void clear() {
		CONTEXT.remove();

	}

}
