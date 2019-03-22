package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.json.bind.annotation.JsonbProperty;

public class CalculateStatsInput {
	private final String hero;
	private final int level;
	private final int awakening;
	private final ArtifactStats artifact;
	private final GearPiece weapon;
	private final GearPiece helmet;
	private final GearPiece armour;
	private final GearPiece necklace;
	private final GearPiece ring;
	private final GearPiece boots;

	public CalculateStatsInput(
			@JsonbProperty("hero") String hero,
			@JsonbProperty("level") int level,
			@JsonbProperty("awakening") int awakening,
			@JsonbProperty("artifact") ArtifactStats artifact,
			@JsonbProperty("weapon") GearPiece weapon,
			@JsonbProperty("helmet") GearPiece helmet,
			@JsonbProperty("armour") GearPiece armour,
			@JsonbProperty("necklace") GearPiece necklace,
			@JsonbProperty("ring") GearPiece ring,
			@JsonbProperty("boots") GearPiece boots
	) {
		this.hero = hero;
		this.level = level;
		this.awakening = awakening;
		this.artifact = artifact;
		this.weapon = weapon;
		this.helmet = helmet;
		this.armour = armour;
		this.necklace = necklace;
		this.ring = ring;
		this.boots = boots;
	}

	public String getHero() {
		return hero;
	}

	public int getLevel() {
		return level;
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

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		CalculateStatsInput that = (CalculateStatsInput) o;
		return level == that.level &&
				awakening == that.awakening &&
				Objects.equal(hero, that.hero) &&
				Objects.equal(weapon, that.weapon) &&
				Objects.equal(helmet, that.helmet) &&
				Objects.equal(armour, that.armour) &&
				Objects.equal(necklace, that.necklace) &&
				Objects.equal(ring, that.ring) &&
				Objects.equal(boots, that.boots);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(hero, level, awakening, weapon, helmet, armour, necklace, ring, boots);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("hero", hero)
				.add("level", level)
				.add("awakening", awakening)
				.add("weapon", weapon)
				.add("helmet", helmet)
				.add("armour", armour)
				.add("necklace", necklace)
				.add("ring", ring)
				.add("boots", boots)
				.toString();
	}
}
