package dev.stocky37.epic7.core;

import dev.stocky37.epic7.repr.CalculateStatsInput;
import dev.stocky37.epic7.repr.CalculateStatsOutput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StatsService {

	@Inject
	HeroService heroService;

	public CalculateStatsOutput calculate(CalculateStatsInput input) {
		heroService.getAwakenedStats(input.getHero(), 0, 0, 0);
		return null;
	}
}
