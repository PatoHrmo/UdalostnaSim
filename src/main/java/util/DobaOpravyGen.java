package util;

import java.util.Random;

public class DobaOpravyGen {
	Random genSeedov;
	Random genNarocnostiOpravy;
	Random genJednoducha;
	Random genTypStredneTazkej;
	Random genStredneTazka1;
	Random genStredneTazka2;
	Random genStredneTazka3;
	Random genTazka;
	public DobaOpravyGen(long seed) {
		genSeedov = new Random(seed);
		genNarocnostiOpravy = new Random(genSeedov.nextLong());
		genJednoducha = new Random(genSeedov.nextLong());
		genTypStredneTazkej = new Random(genSeedov.nextLong());
		genStredneTazka1 = new Random(genSeedov.nextLong());
		genStredneTazka2 = new Random(genSeedov.nextLong());
		genStredneTazka3 = new Random(genSeedov.nextLong());
		genTazka = new Random(genSeedov.nextLong());
	}
	public int nextDlzkaOpravy() {
		double typOpravy = genNarocnostiOpravy.nextDouble();
		if(typOpravy<0.7) {
			// jednoducha oprava
			return (genJednoducha.nextInt(19)+2);
		} 
		if(typOpravy<0.9) {
			//stredne tazka oprava
			double typStredneTazkej = genTypStredneTazkej.nextDouble();
			if(typStredneTazkej<0.1) {
				return (genStredneTazka1.nextInt(31)+10);
			}
			if(typStredneTazkej<0.7) {
				return (genStredneTazka2.nextInt(21)+41);
			}
			return (genStredneTazka3.nextInt(39)+62);
		} else {
			//tazka oprava
			return (genTazka.nextInt(141)+120);
		}
	}

}
