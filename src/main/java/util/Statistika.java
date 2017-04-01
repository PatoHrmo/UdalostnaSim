package util;

public class Statistika {
	private double sucet;
	private int pocetCisel;
	private double sucetNaDruhu;
	public Statistika() {
		sucet = 0;
		pocetCisel = 0;
		sucetNaDruhu = 0;
	}
	/**
	 * prida do tejto statistiky nove cislo
	 * @param cislo ktore bude pridane do statistiky
	 */
	public void pridaj(double cislo) {
		sucet+=cislo;
		sucetNaDruhu+=Math.pow(cislo, 2);
		pocetCisel++;
	}
	public double getPriemer(){
		return (double)sucet/pocetCisel;
	}
	public double[] get90IntervalSpolahlivosti() {
		double interval[] = new double[2];
		interval[0] = getPriemer()-(1.645*getSmerodajnaOdchylka())/Math.sqrt(pocetCisel-1);
		interval[1] = getPriemer()+(1.645*getSmerodajnaOdchylka())/Math.sqrt(pocetCisel-1);
		return interval;
	}
	public double getRozptyl() {
		return ((sucetNaDruhu/pocetCisel)-(Math.pow(getPriemer(), 2)));
	}
	public double getSmerodajnaOdchylka() {
		return Math.sqrt(getRozptyl());
	}
	public void reset(){
		sucet = 0;
		pocetCisel = 0;
		sucetNaDruhu = 0;
	}

}
