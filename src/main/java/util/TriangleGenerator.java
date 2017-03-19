package util;

import java.util.Random;

public class TriangleGenerator {
	double min;
	double mod;
	double max;
	double cumulationInMod;
	Random genU;
	public TriangleGenerator(long seed, double min, double max, double mod) {
		genU = new Random(seed);
		this.min = min;
		this. max = max;
		this.mod = mod;
		cumulationInMod = (mod-min)/(max-min);
	}
	public double nextTria() {
		double pom = genU.nextDouble();
		if(pom<cumulationInMod) {
			return min + Math.sqrt(pom*(max-min)*(mod-min));
		}
		return max - Math.sqrt((1-pom)*(max-min)*(max-mod));
	}

}
