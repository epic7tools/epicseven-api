package dev.stocky37.epic7.repr;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import dev.stocky37.epic7.core.GearSet;
import dev.stocky37.epic7.core.Stat;
import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Value.Immutable
public abstract class EquipInput {
	@JsonbCreator
	public static EquipInput create(
		@JsonbProperty("level") int level,
		@JsonbProperty("stars") int stars,
		@JsonbProperty("awakening") int awakening,
		@JsonbProperty("artifact") ArtifactStats artifact,
		@JsonbProperty("weapon") GearPiece weapon,
		@JsonbProperty("helmet") GearPiece helmet,
		@JsonbProperty("armour") GearPiece armour,
		@JsonbProperty("necklace") GearPiece necklace,
		@JsonbProperty("ring") GearPiece ring,
		@JsonbProperty("boots") GearPiece boots
	) {
		return ImmutableEquipInput.builder()
			.level(level)
			.stars(stars)
			.awakening(awakening)
			.artifact(artifact)
			.weapon(weapon)
			.helmet(helmet)
			.armour(armour)
			.necklace(necklace)
			.ring(ring)
			.boots(boots)
			.build();
	}

	@Value.Default
	public int level() {
		return 50;
	}

	@Value.Default
	public int stars() {
		return 5;
	}

	@Value.Default
	public int awakening() {
		return 0;
	}

	public abstract ArtifactStats artifact();

	public abstract GearPiece weapon();

	public abstract GearPiece helmet();

	public abstract GearPiece armour();

	public abstract GearPiece necklace();

	public abstract GearPiece ring();

	public abstract GearPiece boots();


	@JsonbTransient
	public Map<Stat, BigDecimal> getGearStats() {
		final Map<Stat, BigDecimal> stats = Maps.newEnumMap(Stat.class);

		stats.putAll(Stream.of(
			artifact().stats(),
			weapon().stats(),
			helmet().stats(),
			armour().stats(),
			necklace().stats(),
			ring().stats(),
			boots().stats()
		)
			.flatMap(map -> map.entrySet().stream())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, BigDecimal::add)));


		//noinspection OptionalGetWithoutIsPresent
		getCompleteGearSets().stream()
			.filter(x -> x.getStat().isPresent())
			.forEach(set -> stats.merge(set.getStat().get(), set.getValue().get(), BigDecimal::add));

		return ImmutableMap.copyOf(stats);
	}

	@JsonbTransient
	public List<GearSet> getCompleteGearSets() {
		final ImmutableList.Builder<GearSet> builder = ImmutableList.builder();
		getGearSetCount().forEach((set, count) -> {
			final int numSets = Math.floorDiv(count.intValue(), set.getAmountForSet());
			for(int i = 0; i < numSets; i++) {
				builder.add(set);
			}
		});
		return builder.build();
	}

	private Map<GearSet, Long> getGearSetCount() {
		return getAllGearSets().stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	}

	private List<GearSet> getAllGearSets() {
		ImmutableList.Builder<GearSet> builder = ImmutableList.builder();
		weapon().gearSet().ifPresent(builder::add);
		helmet().gearSet().ifPresent(builder::add);
		armour().gearSet().ifPresent(builder::add);
		necklace().gearSet().ifPresent(builder::add);
		ring().gearSet().ifPresent(builder::add);
		boots().gearSet().ifPresent(builder::add);
		return builder.build();
	}
}
