package dev.stocky37.epic7.config;

import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.eclipse.microprofile.config.spi.ConfigSource;

public class EnvLookupConfigSource extends SubstitutingConfigSource {
	public EnvLookupConfigSource(ConfigSource delegate) {
		super(delegate, new StringSubstitutor(StringLookupFactory.INSTANCE.environmentVariableStringLookup()));
	}
}
