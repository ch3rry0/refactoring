package fr.efrei.transfret;

import java.util.HashMap;
import java.util.Map;

public class MemoireExpeditions implements ExpeditionRepository {

    private final Map<String, Expedition> expeditions = new HashMap<>();

    @Override
    public void sauvegarder(Expedition expedition) {
        expeditions.put(expedition.getId(), expedition);
    }

    @Override
    public Expedition trouver(String id) {
        return expeditions.get(id);
    }

    @Override
    public void mettreAJour(Expedition expedition) {
        expeditions.put(expedition.getId(), expedition);
    }
}
