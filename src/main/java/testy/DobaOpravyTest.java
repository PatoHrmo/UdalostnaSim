package testy;

import util.DobaOpravyGen;

public class DobaOpravyTest {

	public static void main(String[] args) {
		DobaOpravyGen rnd = new DobaOpravyGen(20000);
		double celkom = 0;
		for(int i =0; i< 100000000;i++) {
			celkom+= rnd.nextDlzkaOpravy();
		}
		System.out.println(celkom/100000000);

	}

}
