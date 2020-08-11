package tolley.jabp.handlers;

import org.bukkit.Bukkit;

public class MessageHandler {

    public void announceBanPublic(String playerName, String bannedBy, String length, String reason) {
        Bukkit.broadcastMessage(playerName + " has been banned by " + bannedBy +" for " + length + " for: " + reason);
    }
    public void announceBanStaff(String playerName, String bannedBy, String length, String reason) {
        Bukkit.broadcast(playerName + " has been banned by " + bannedBy +" for " + length + " for: " + reason, "jabp.receiveStaffMessages");
    }

}
