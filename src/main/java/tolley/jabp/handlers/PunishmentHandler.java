package tolley.jabp.handlers;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.ocpsoft.prettytime.PrettyTime;
import tolley.jabp.dataTypes.PlayerObject;
import tolley.jabp.dataTypes.PunishmentReason;

import java.io.File;
import java.util.Date;

public class PunishmentHandler {

    FileConfiguration config;

    public void initPunishmentHandler(FileConfiguration pluginConfig) {
        config = pluginConfig;
    }

    public void banPlayer(PlayerObject player, PunishmentReason reason) {
        Bukkit.getBanList(BanList.Type.IP).addBan(player.ip, parsePlaceholders(config.getString("BanReason"), reason), reason.expire, null);
    }
    public void banPlayer(Player player, PunishmentReason reason) {
        Bukkit.getBanList(BanList.Type.IP).addBan(player.getAddress().toString().substring(1).split(":")[0], parsePlaceholders(config.getString("BanReason"), reason), reason.expire, null);
    }

    public String parsePlaceholders(String stringToParse, PunishmentReason punishmentReason) {
        String output = stringToParse;
        output.replaceAll("[player]", punishmentReason.playerName);
        output.replaceAll("[staff]", punishmentReason.punishedBy);
        output.replaceAll("[reason]", punishmentReason.reason);

        long differenceInMilliseconds = Math.abs(punishmentReason.created.getTime() - punishmentReason.expire.getTime());
        PrettyTime p = new PrettyTime();
        String timeLeft = p.format(punishmentReason.expire);
        timeLeft = timeLeft.substring(0, timeLeft.length() - 9);

        String punishmentLength = p.format(new Date(System.currentTimeMillis() + differenceInMilliseconds));
        punishmentLength = punishmentLength.substring(0, timeLeft.length() - 9);

        output.replaceAll("[timeLeft]", timeLeft);
        output.replaceAll("[time]", punishmentLength);
        return output;
    }
}

