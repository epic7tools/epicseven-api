package dev.stocky37.epic7.config;

import org.apache.commons.text.StringSubstitutor;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SubstitutingConfigSource extends ForwardingConfigSource {
	private final ConfigSource delegate;
	private final StringSubstitutor substitutor;

	public SubstitutingConfigSource(ConfigSource delegate, StringSubstitutor substitutor) {
		this.delegate = delegate;
		this.substitutor = substitutor;
	}

	@Override
	public String getValue(String propertyName) {
		return substitute(super.getValue(propertyName));
	}

	@Override
	public Map<String, String> getProperties() {
		final Map<String, String> properties = super.getProperties();
		final Map<String, String> substituted = new HashMap<>();
		for(final String key : properties.keySet()) {
			substituted.put(key, substitute(properties.get(key)));
		}
		return Collections.unmodifiableMap(substituted);
	}

	private String substitute(String value) {
		return substitutor.replace(value);
	}

	@Override
	ConfigSource delegate() {
		return delegate;
	}
}
