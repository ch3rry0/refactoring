package fr.efrei.transfret;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CalculateurPrix {

    private final Map<ModeTransport, TarifTransport> tarifs;
    private final Map<TypeClient, PolitiqueRemise> remises;

    public CalculateurPrix() {
        tarifs = new EnumMap<>(ModeTransport.class);
        tarifs.put(ModeTransport.ROUTE, new TarifRoute());
        tarifs.put(ModeTransport.RAIL, new TarifRail());
        tarifs.put(ModeTransport.MER, new TarifMer());
        tarifs.put(ModeTransport.AIR, new TarifAir());
        tarifs.put(ModeTransport.FLUVIAL, new TarifFluvial());

        remises = new EnumMap<>(TypeClient.class);
        remises.put(TypeClient.PARTICULIER, new AucuneRemise());
        remises.put(TypeClient.ENTREPRISE, new RemiseEntreprise());
        remises.put(TypeClient.PREMIUM, new RemisePremium());
    }

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent,
                                 boolean assurance, TypeClient typeClient) {
        ComposantPrix prix = assembler(mode, urgent, assurance, typeClient);
        return Utils.arrondir(prix.calculer(new CalculPrix(colis, distance, typeClient)));
    }

    private ComposantPrix assembler(ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        ComposantPrix prix = new ComposantTarif(tarif(mode));
        prix = new SupplementFragile(prix);
        prix = new SupplementDangereux(prix);
        prix = new SupplementRefrigere(prix);
        if (mode == ModeTransport.FLUVIAL) {
            prix = new SupplementHorsGabarit(prix);
        }
        prix = new SupplementUrgence(prix, urgent);
        prix = new DecorateurRemise(prix, remise(typeClient));
        return new Assurance(prix, assurance);
    }

    private TarifTransport tarif(ModeTransport mode) {
        TarifTransport tarif = tarifs.get(mode);
        if (tarif == null) {
            throw new IllegalArgumentException("Mode inconnu : " + mode);
        }
        return tarif;
    }

    private PolitiqueRemise remise(TypeClient typeClient) {
        PolitiqueRemise remise = remises.get(typeClient);
        if (remise == null) {
            throw new IllegalArgumentException("Type de client inconnu : " + typeClient);
        }
        return remise;
    }
}
