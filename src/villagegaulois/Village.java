package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.VillageSansChefException;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMaximum);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		StringBuilder chaine = new StringBuilder();
		if (chef == null) {
			throw new VillageSansChefException(
				"Cannot invoke \"villagegaulois.Vilage.afficherVillageois()\" because \"Vilage.chef\" is null");
		}
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder result = new StringBuilder(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		int iEtal = marche.trouverEtalLibre() +1;
		if (iEtal > 0) {
			marche.utiliserEtal(iEtal -1, vendeur, produit, nbProduit);
			result.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + iEtal + ".\n");
			System.out.println();
		} else result.append("Le vendeur " + vendeur.getNom() + " ne peut pas vendre ces " + produit + ", il n'y à plus d'étal disponible.\n");
		return result.toString();
	}
	public String rechercherVendeursProduit(String produit) {
		StringBuilder result = new StringBuilder();
		Etal[] venduers = marche.trouverEtals(produit);
		if (venduers.length == 0) result.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.");
		else if (venduers.length == 1)
			result.append("Seul le vendeur " + venduers[0].getVendeur().getNom() + " propose des " + produit + " au marché.");
		else {
			result.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			for (int i = 0; i < venduers.length; ++i) {
				result.append("\t- " + venduers[i].getVendeur().getNom() + "\n");
			}
		} return result.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	public String partirVendeur(Gaulois vendeur) {
		Etal etal = marche.trouverVendeur(vendeur);
		return etal.libererEtal();
	}
	public String afficherMarche() {
		StringBuilder result = new StringBuilder("Le marché du village \"" + nom + "\" possède plusieurs étals :\n");
		result.append(marche.afficherMarche());
		return result.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		private int nb_etals = 0;

		private Marche(int nb_etals) {
			this.nb_etals = nb_etals;
			this.etals = new Etal[nb_etals];
			for (int i = 0; i < nb_etals; ++i) {
				this.etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		private int trouverEtalLibre() {
			for (int i = 0; i < nb_etals; ++i) {
				if (!etals[i].isEtalOccupe()) return i;
			} return -1;
		}
		private Etal[] trouverEtals(String produit) {
			int nbEtalsTrouver = 0, i, j;
			for (i = 0; i < nb_etals; ++i) {
				if (etals[i].contientProduit(produit)) nbEtalsTrouver++;
			}
			Etal[] result = new Etal[nbEtalsTrouver];
			for (i = 0, j = 0; i < nb_etals; ++i) {
				if (etals[i].contientProduit(produit))
					result[j++] = etals[i];
			} return result;
		}
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < nb_etals; ++i) {
				if (etals[i].getVendeur() == gaulois) return etals[i];
			} return null;
		}
		private String afficherMarche() {
			StringBuilder result = new StringBuilder();
			int nbEtalVide = 0;
			for (int i = 0; i < nb_etals; ++i) {
				if (etals[i].isEtalOccupe()) result.append(etals[i].afficherEtal());
				else nbEtalVide++;
			} if (nbEtalVide != 0) {
				result.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			} return result.toString();
		}
	}
}