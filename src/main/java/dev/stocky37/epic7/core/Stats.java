package dev.stocky37.epic7.core;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Streams;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stats extends ForwardingMap<Stat, BigDecimal> {
	private final ImmutableMap<Stat, BigDecimal> delegate;

	public Stats() {
		this(ImmutableMap.of());
	}

	public Stats(Map<Stat, BigDecimal> delegate) {
		this(ImmutableMap.copyOf(delegate));
	}

	public Stats(ImmutableMap<Stat, BigDecimal> delegate) {
		this.delegate = delegate;
	}

	public static Stats from(Map<String, BigDecimal> from) {
		return new Stats(from.entrySet().stream()
			.collect(Collectors.toMap(
				entry -> Stat.fromId(entry.getKey()),
				Entry::getValue
			)));
	}

	@Override
	protected Map<Stat, BigDecimal> delegate() {
		return delegate;
	}

	public Stats merge(Map<Stat, BigDecimal> other) {
		return mergeEntries(other.entrySet().stream());
	}

	public Stats merge(Iterable<? extends Map<Stat, BigDecimal>> others) {
		return mergeEntries(flattenMap(others));
	}

	public Stats apply(Map<Stat, BigDecimal> other) throws IllegalStateException {
		ensureCanRunApply();
		return applyEntries(other.entrySet().stream());
	}

	public Stats apply(Iterable<? extends Map<Stat, BigDecimal>> others) throws IllegalStateException {
		ensureCanRunApply();
		return applyEntries(flattenMap(others));
	}

	private void ensureCanRunApply() {
		if(!containsOnlyBaseStats()) {
			throw new IllegalStateException("Can't call apply() on non-base stats");
		}
	}

	private boolean containsOnlyBaseStats() {
		return keySet().stream().allMatch(Stat::isBaseStat);
	}

	private Stats mergeEntries(Stream<Entry<Stat, BigDecimal>> entries) {
		return collectEntries(entries, Collectors.toMap(Entry::getKey, Entry::getValue, BigDecimal::add));
	}

	private Stats applyEntries(Stream<Entry<Stat, BigDecimal>> entries) {
		return collectEntries(entries, Collectors.toMap(entry -> entry.getKey().getBaseStat(), entry -> {
			final Stat stat = entry.getKey();
			final BigDecimal value = entry.getValue();
			return stat.isBaseStat() ? value : getOrDefault(stat.getBaseStat(), BigDecimal.ZERO).multiply(value);
		}, BigDecimal::add));
	}

	@SuppressWarnings("UnstableApiUsage")
	private Stats collectEntries(
		Stream<Map.Entry<Stat, BigDecimal>> entries,
		Collector<Map.Entry<Stat, BigDecimal>, ?, Map<Stat, BigDecimal>> collector
	) {
		return new Stats(Streams.concat(this.entrySet().stream(), entries).collect(collector));
	}

	@SuppressWarnings("UnstableApiUsage")
	private Stream<Map.Entry<Stat, BigDecimal>> flattenMap(Iterable<? extends Map<Stat, BigDecimal>> maps) {
		return Streams.stream(maps).map(Map::entrySet).flatMap(Collection::stream);
	}
}
