package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarificationStrategyTest {

    private static final List<Colis> COLIS = List.of(
            new Colis("Colis", 100, 50, 40, 30, TypeMarchandise.STANDARD, 1000));

    @Test
    void les_strategies_appliquent_leur_tarif() {
        assertEquals(130, new TarifRoute().calculer(COLIS, 100));
        assertEquals(150, new TarifRail().calculer(COLIS, 100));
        assertEquals(500, new TarifMer().calculer(COLIS, 100));
        assertEquals(510, new TarifAir().calculer(COLIS, 100));
    }

    @Test
    void les_minima_rail_et_mer_sont_respectes() {
        List<Colis> petitColis = List.of(
                new Colis("Petit colis", 1, 10, 10, 10, TypeMarchandise.STANDARD, 10));

        assertEquals(150, new TarifRail().calculer(petitColis, 1));
        assertEquals(500, new TarifMer().calculer(petitColis, 1));
    }

    @Test
    void le_poids_taxable_aerien_est_volumetrique_si_necessaire() {
        List<Colis> colisVolumineux = List.of(
                new Colis("Colis volumineux", 1, 200, 200, 200, TypeMarchandise.STANDARD, 10));

        assertEquals(6012.6, new TarifAir().calculer(colisVolumineux, 1), 0.001);
    }

    @Test
    void les_politiques_de_remise_sont_independantes() {
        assertEquals(100, new AucuneRemise().appliquer(100));
        assertEquals(90, new RemiseEntreprise().appliquer(100));
        assertEquals(5100, new RemiseEntreprise().appliquer(6000));
        assertEquals(80, new RemisePremium().appliquer(100));
    }
}
