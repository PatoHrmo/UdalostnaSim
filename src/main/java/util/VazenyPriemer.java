package util;



public class VazenyPriemer {
	private double suma;
	private double vaha;
	public VazenyPriemer() {
		suma = 0;
		vaha = 0;
	}
	public void pridaj(double hodnota, double vaha) {
		suma += hodnota*vaha;
		this.vaha+= vaha;
	}
	public double getPriemer() {
		return suma/vaha;
	}
	public void reset() {
		suma = 0;
		vaha = 0;
	} 

}
