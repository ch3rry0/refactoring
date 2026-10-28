package fr.efrei.transfret;

import java.util.List;

public record CalculPrix(List<Colis> colis, int distance, TypeClient typeClient) {

    public double valeurDeclareeTotale() {
        return colis.stream().mapToDouble(Colis::getValeurDeclaree).sum();
    }

    public boolean contient(TypeMarchandise type) {
        return colis.stream().anyMatch(colis -> colis.getType() == type);
    }
}
