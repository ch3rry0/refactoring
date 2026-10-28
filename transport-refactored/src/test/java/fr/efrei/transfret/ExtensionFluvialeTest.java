package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtensionFluvialeTest {

    @Test
    void le_tarif_fluvial_respecte_le_minimum() {
        List<Colis> colis = List.of(
                new Colis("Petit colis", 10, 50, 40, 30, TypeMarchandise.STANDARD, 100));

        assertEquals(300, new TarifFluvial().calculer(colis, 100));
    }

    @Test
    void la_peniche_est_compatible_avec_le_fluvial_sans_refrigere() {
        Peniche peniche = new Peniche("PEN-0001", 2_500_000);
        List<Colis> colis = List.of(
                new Colis("Colis", 100, 50, 40, 30, TypeMarchandise.STANDARD, 100));

        assertTrue(peniche.peutPrendre(ModeTransport.FLUVIAL, colis));
        assertFalse(peniche.peutPrendre(ModeTransport.ROUTE, colis));
    }

    @Test
    void le_fluvial_interdit_le_refrigere_et_l_urgence() {
        Client client = new Client("Client", "client@mail.fr", "0600000000", "Adresse",
                TypeClient.PARTICULIER, null);
        List<Colis> colis = List.of(
                new Colis("Produits", 10, 50, 40, 30, TypeMarchandise.REFRIGERE, 100));
        ValidateurExpedition validateur = new ValidateurExpedition();

        assertFalse(validateur.valider(client, colis, "Paris", "Le Havre", ModeTransport.FLUVIAL, false));
        assertFalse(validateur.valider(client, List.of(
                new Colis("Colis", 10, 50, 40, 30, TypeMarchandise.STANDARD, 100)),
                "Paris", "Le Havre", ModeTransport.FLUVIAL, true));
    }

    @Test
    void le_delai_fluvial_est_arrondi_avec_un_jour_de_preparation() {
        EstimateurDelai estimateur = new EstimateurDelai();

        assertEquals(4, estimateur.estimer(721, ModeTransport.FLUVIAL, false));
    }

    @Test
    void le_hors_gabarit_est_applique_si_une_dimension_depasse_200_cm() {
        List<Colis> colis = List.of(
                new Colis("Hors gabarit", 10, 201, 40, 30, TypeMarchandise.STANDARD, 100));
        CalculPrix calcul = new CalculPrix(colis, 100, TypeClient.PARTICULIER);
        ComposantPrix prix = new SupplementHorsGabarit(new ComposantPrix() {
            @Override
            public double calculer(CalculPrix ignored) {
                return 100;
            }
        });

        assertEquals(110, prix.calculer(calcul), 0.001);
    }
}
