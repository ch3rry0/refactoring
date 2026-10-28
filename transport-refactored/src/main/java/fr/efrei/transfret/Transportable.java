package fr.efrei.transfret;

import java.util.List;

public interface Transportable {

    boolean peutPrendre(ModeTransport mode, List<Colis> colis);
}
