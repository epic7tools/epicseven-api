package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import dev.stocky37.epic7.core.GearSet;
import dev.stocky37.epic7.core.Stat;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EquipInput {
	private final int level;
	private final int stars;
	private final int awakening;
	private final ArtifactStats artifact;
	private final GearPiece weapon;
	private final GearPiece helmet;
	private final GearPiece armour;
	private final GearPiece necklace;
	private final GearPiece ring;
	private final GearPiece boots;

	@JsonbCreator
	public EquipInput(
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
		this.level = level;
		this.stars = stars;
		this.awakening = awakening;
		this.artifact = artifact;
		this.weapon = weapon;
		this.helmet = helmet;
		this.armour = armour;
		this.necklace = necklace;
		this.ring = ring;
		this.boots = boots;
	}

	public int getLevel() {
		return level;
	}

	public int getStars() {
		return stars;
	}

	public int getAwakening() {
		return awakening;
	}

	public ArtifactStats getArtifact() {
		return artifact;
	}

	public GearPiece getWeapon() {
		return weapon;
	}

	public GearPiece getHelmet() {
		return helmet;
	}

	public GearPiece getArmour() {
		return armour;
	}

	public GearPiece getNecklace() {
		return necklace;
	}

	public GearPiece getRing() {
		return ring;
	}

	public GearPiece getBoots() {
		return boots;
	}

	@JsonbTransient
	public Map<Stat, BigDecimal> getGearStats() {
		final Map<Stat, BigDecimal> stats = Maps.newEnumMap(Stat.class);
		final Consumer<StatValue> merge = (stat) -> stats.merge(stat.getStat(), stat.getValue(), BigDecimal::add);

		artifact.getStats().forEach(merge);
		weapon.getStats().forEach(merge);
		helmet.getStats().forEach(merge);
		armour.getStats().forEach(merge);
		necklace.getStats().forEach(merge);
		ring.getStats().forEach(merge);
		boots.getStats().forEach(merge);

		//noinspection OptionalGetWithoutIsPresent
		getCompleteGearSets().stream()
			.filter(x -> x.getStat().isPresent())
			.forEach(set -> stats.merge(set.getStat().get(), set.getValue().get(), BigDecimal::add));

		return ImmutableMap.copyOf(stats);
	}

	public Collection<GearSet> getAllGearSets() {
		return ImmutableList.<GearSet>builder()
			.add(weapon.getSet())
			.add(helmet.getSet())
			.add(armour.getSet())
			.add(necklace.getSet())
			.add(ring.getSet())
			.add(boots.getSet())
			.build();
	}

	public Collection<GearSet> getCompleteGearSets() {
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("level", level)
			.add("stars", stars)
			.add("awakening", awakening)
			.add("artifact", artifact)
			.add("weapon", weapon)
			.add("helmet", helmet)
			.add("armour", armour)
			.add("necklace", necklace)
			.add("ring", ring)
			.add("boots", boots)
			.toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof EquipInput)) {
			return false;
		}
		EquipInput that = (EquipInput) o;
		return level == that.level &&
			stars == that.stars &&
			awakening == that.awakening &&
			artifact.equals(that.artifact) &&
			weapon.equals(that.weapon) &&
			helmet.equals(that.helmet) &&
			armour.equals(that.armour) &&
			necklace.equals(that.necklace) &&
			ring.equals(that.ring) &&
			boots.equals(that.boots);
	}

	@Override
	public int hashCode() {
		return Objects.hash(level, stars, awakening, artifact, weapon, helmet, armour, necklace, ring, boots);
	}
}
