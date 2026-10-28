package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class CanalSmsConsole implements CanalNotification {

    private final List<NotificationEnvoyee> notifications = new ArrayList<>();

    @Override
    public void envoyer(String destinataire, String sujet, String corps) {
        System.out.println("[SMS] -> " + destinataire + " : " + corps);
        notifications.add(new NotificationEnvoyee(TypeCanal.SMS, destinataire, corps));
    }

    @Override
    public List<NotificationEnvoyee> notifications() {
        return List.copyOf(notifications);
    }
}
