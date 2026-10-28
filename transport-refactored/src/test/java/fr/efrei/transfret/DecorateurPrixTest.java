package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecorateurPrixTest {

    private static final List<Colis> COLIS = List.of(
            new Colis("Fragile", 10, 50, 40, 30, TypeMarchandise.FRAGILE, 1000));

    @Test
    void les_supplements_s_empilent_dans_l_ordre() {
        CalculPrix calcul = new CalculPrix(COLIS, 100, TypeClient.PARTICULIER);
        ComposantPrix prix = new SupplementFragile(new ComposantPrix() {
            @Override
            public double calculer(CalculPrix ignored) {
                return 100;
            }
        });

        assertEquals(115, prix.calculer(calcul), 0.001);
    }

    @Test
    void l_assurance_est_appliquee_apres_la_remise() {
        CalculPrix calcul = new CalculPrix(COLIS, 100, TypeClient.ENTREPRISE);
        ComposantPrix prix = new Assurance(
                new DecorateurRemise(new ComposantPrix() {
                    @Override
                    public double calculer(CalculPrix ignored) {
                        return 100;
                    }
                }, new RemiseEntreprise()),
                true);

        assertEquals(110, prix.calculer(calcul), 0.001);
    }

    @Test
    void le_calculateur_assemble_les_decorateurs() {
        double prix = new CalculateurPrix().calculerPrixHT(
                COLIS, 100, ModeTransport.ROUTE, true, true, TypeClient.ENTREPRISE);

        assertEquals(207.85, prix, 0.001);
    }
}
