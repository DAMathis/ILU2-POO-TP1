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
			System.out.println("Exception catch libererEtal: " + e);
		}
		try {
			etal.acheterProduit(0, null);
		} catch (NullPointerException e) {
			System.out.println("Exception catch acheterProduit3: " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception catch acheterProduit3: " + e);
		} catch (IllegalStateException e) {
			System.out.println("Exception catch acheterProduit3:" + e);
		}
		etal.occuperEtal(new Gaulois("Francis", 0), null, 0);
		try {
			etal.acheterProduit(7, null);
		} catch (NullPointerException e) {
			System.out.println("Exception catch acheterProduit1: " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception catch acheterProduit1: " + e);
		} catch (IllegalStateException e) {
			System.out.println("Exception catch acheterProduit1:" + e);
		}
		try {
			etal.acheterProduit(0, new Gaulois("Asterix", 0));
		} catch (NullPointerException e) {
			System.out.println("Exception catch acheterProduit2: " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception catch acheterProduit2: " + e);
		} catch (IllegalStateException e) {
			System.out.println("Exception catch acheterProduit2:" + e);
		}
		etal.libererEtal();
		System.out.println("Fin du test");
	}
}
