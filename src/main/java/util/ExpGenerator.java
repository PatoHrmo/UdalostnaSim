package util;

import java.util.Random;

public class ExpGenerator {
	private final Random rnd;
	private final double lambda;
	/**
	 * Vytvory instanciu generatora exponencialneho rozdelenia 
	 * @param seed seed pouzity na pomocny generator
	 * @param strednaHodnota stredna hodnota exponencialneho rozdelenia
	 */
	public ExpGenerator(long seed, double strednaHodnota){
		rnd = new Random(seed);
		lambda = 1d/strednaHodnota;
	}
	/**
	 * vygeneruje cislo s exp rozdelenim
	 * @return cislo s exp rozdelenim
	 */
	public double nextExp() {
		return Math.log(1-rnd.nextDouble())/(-lambda);
	}
	
}
