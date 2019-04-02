package dev.stocky37.epic7.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

public abstract class ForwardingConfigSource implements ConfigSource {
	abstract ConfigSource delegate();

	@Override
	public Map<String, String> getProperties() {
		return delegate().getProperties();
	}

	@Override
	public String getValue(String propertyName) {
		return delegate().getValue(propertyName);
	}

	@Override
	public String getName() {
		return delegate().getName();
	}

	@Override
	public Set<String> getPropertyNames() {
		return delegate().getPropertyNames();
	}

	@Override
	public int getOrdinal() {
		return delegate().getOrdinal();
	}
}
