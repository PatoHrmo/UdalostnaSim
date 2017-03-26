package autoservis;

public class Oprava {
	private double zaciatokCakaniaVoFronteZakaznikov;
	private boolean nastavenyZaciatokCakaniaVoFronteZakaznikov;
	private double koniecCakaniaVoFronteZakaznikov;
	private boolean nastavenyKoniecCakaniaVoFronteZakaznikov;
	private double zaciatokCakaniaNaOpravuOdovydanehoAuta;
	private boolean nastavenyZaciatokCakaniaNaOpravuOdovydanehoAuta;
	private double koniecCakaniaNaOpravuOdovydanehoAuta;
	private boolean nastavenyKoniecCakaniaNaOpravuOdovydanehoAuta;
	private double dlzkaOpravy;
	
	public Oprava() {
		nastavenyKoniecCakaniaNaOpravuOdovydanehoAuta = false;
		nastavenyKoniecCakaniaVoFronteZakaznikov = false;
		nastavenyZaciatokCakaniaNaOpravuOdovydanehoAuta = false;
		nastavenyZaciatokCakaniaVoFronteZakaznikov = false;
	}
	public double getDlzkaCakaniaVoFronteZakaznikov(){
		if(nastavenyZaciatokCakaniaVoFronteZakaznikov && nastavenyKoniecCakaniaVoFronteZakaznikov) {
			return getKoniecCakaniaVoFronteZakaznikov()-getZaciatokCakaniaVoFronteZakaznikov();
		}
		System.out.println("zle nastavene- nenastavene casy cakania vo fronte");
		return Double.MIN_VALUE;
	}
	public double getDlzkaCakaniaNaOpravuAuta() {
		if(nastavenyKoniecCakaniaNaOpravuOdovydanehoAuta && nastavenyZaciatokCakaniaNaOpravuOdovydanehoAuta) {
			return getKoniecCakaniaNaOpravuOdovzdanehoAuta()-getZaciatokCakaniaNaOpravuOdovzdanehoAuta();
		}
		System.out.println("zle nastavene- nenastavene casy opravy");
		return Double.MIN_VALUE;
	}
	
	public double getZaciatokCakaniaVoFronteZakaznikov() {
		return zaciatokCakaniaVoFronteZakaznikov;
	}
	public void setZaciatokCakaniaVoFronteZakaznikov(double zaciatokCakaniaVoFronteZakaznikov) {
		this.zaciatokCakaniaVoFronteZakaznikov = zaciatokCakaniaVoFronteZakaznikov;
		nastavenyZaciatokCakaniaVoFronteZakaznikov = true;
	}
	public double getKoniecCakaniaVoFronteZakaznikov() {
		return koniecCakaniaVoFronteZakaznikov;
	}
	public void setKoniecCakaniaVoFronteZakaznikov(double koniecCakaniaVoFronteZakaznikov) {
		this.koniecCakaniaVoFronteZakaznikov = koniecCakaniaVoFronteZakaznikov;
		nastavenyKoniecCakaniaVoFronteZakaznikov = true;
	}
	public double getZaciatokCakaniaNaOpravuOdovzdanehoAuta() {
		return zaciatokCakaniaNaOpravuOdovydanehoAuta;
	}
	public void setZaciatokCakaniaNaOpravuOdovzdanehoAuta(double zaciatokCakaniaNaOpravuOdovydanehoAuta) {
		this.zaciatokCakaniaNaOpravuOdovydanehoAuta = zaciatokCakaniaNaOpravuOdovydanehoAuta;
		nastavenyZaciatokCakaniaNaOpravuOdovydanehoAuta = true;
	}
	public double getKoniecCakaniaNaOpravuOdovzdanehoAuta() {
		return koniecCakaniaNaOpravuOdovydanehoAuta;
	}
	public void setKoniecCakaniaNaOpravuOdovzdanehoAuta(double koniecCakaniaNaOpravuOdovydanehoAuta) {
		this.koniecCakaniaNaOpravuOdovydanehoAuta = koniecCakaniaNaOpravuOdovydanehoAuta;
		nastavenyKoniecCakaniaNaOpravuOdovydanehoAuta = true;
	}
	public double getDlzkaOpravy() {
		return dlzkaOpravy;
	}
	public void setDlzkaOpravy(double dlzkaOpravy) {
		this.dlzkaOpravy = dlzkaOpravy;
	}
	@Override
	public String toString() {
		return "Oprava [zaciatokCakaniaVoFronteZakaznikov=" + zaciatokCakaniaVoFronteZakaznikov
				+ ", koniecCakaniaVoFronteZakaznikov=" + koniecCakaniaVoFronteZakaznikov
				+ ", zaciatokCakaniaNaOpravuOdovydanehoAuta=" + zaciatokCakaniaNaOpravuOdovydanehoAuta
				+ ", koniecCakaniaNaOpravuOdovydanehoAuta=" + koniecCakaniaNaOpravuOdovydanehoAuta + ", dlzkaOpravy="
				+ dlzkaOpravy + "]"+System.lineSeparator()
				+"dlzka cakania vo fronte: "+getDlzkaCakaniaVoFronteZakaznikov();
	}
	public void vypis(){
		System.out.println(toString());
	}
	
}
