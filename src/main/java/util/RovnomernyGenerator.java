package util;

import java.util.Random;

public class RovnomernyGenerator {
	private Random rnd;
	private double rOd;
	private double rDo;
	public RovnomernyGenerator(long seed , double rozsahOd, double rozsahDo) {
		rnd = new Random(seed);
		rOd = rozsahOd;
		rDo = rozsahDo;
	}
	public double next() {
		return (rnd.nextDouble()*(rDo-rOd))+rOd;
	}

}
