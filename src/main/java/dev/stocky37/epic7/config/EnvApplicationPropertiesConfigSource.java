package dev.stocky37.epic7.config;

import io.quarkus.runtime.configuration.ApplicationPropertiesConfigSource;

public class EnvApplicationPropertiesConfigSource extends EnvLookupConfigSource {
	public EnvApplicationPropertiesConfigSource() {
		super(new ApplicationPropertiesConfigSource.InJar());
	}

	@Override
	public int getOrdinal() {
		return super.getOrdinal() + 1;
	}
}
