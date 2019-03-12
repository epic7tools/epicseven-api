package dev.stocky37.epic7.web;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFeature implements Feature {
	@Override
	public boolean configure(FeatureContext context) {
		final CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().add("*");
		context.register(corsFilter);
		return true;
	}
}
