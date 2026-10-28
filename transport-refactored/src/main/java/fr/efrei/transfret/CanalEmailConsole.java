package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class CanalEmailConsole implements CanalNotification {

    private final List<NotificationEnvoyee> notifications = new ArrayList<>();

    @Override
    public void envoyer(String destinataire, String sujet, String corps) {
        System.out.println("[EMAIL] -> " + destinataire + " | " + sujet);
        notifications.add(new NotificationEnvoyee(TypeCanal.EMAIL, destinataire, sujet));
    }

    @Override
    public List<NotificationEnvoyee> notifications() {
        return List.copyOf(notifications);
    }
}
