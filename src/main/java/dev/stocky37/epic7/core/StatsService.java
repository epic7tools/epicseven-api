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
		System.err.println(input);
		System.out.println(heroService.getHeroStats(input.getHero()));
		return null;
	}
}
