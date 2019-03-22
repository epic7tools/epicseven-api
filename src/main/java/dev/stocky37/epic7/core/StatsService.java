package dev.stocky37.epic7.core;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StatsService {
	public List<Stat> calculate() {
		return Stat.getAllBaseStats();
	}
}
