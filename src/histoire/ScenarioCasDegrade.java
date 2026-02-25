package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		try {
			etal.libererEtal();
		} catch (NullPointerException e) {	// RuntimeException
			System.out.println("Exception catch: " + e);
		}
		etal.acheterProduit(0, null);
		System.out.println("Fin du test");
	}
}
