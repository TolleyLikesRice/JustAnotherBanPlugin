package tolley.jabp.handlers;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import tolley.jabp.dataTypes.PlayerObject;
import tolley.jabp.dataTypes.PunishmentReason;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class PunishmentHandler {

    FileConfiguration config;
    DataHandler dataHandler;

    public void initPunishmentHandler(FileConfiguration pluginConfig, DataHandler dataHandlerInput) {
        config = pluginConfig;
        dataHandler = dataHandlerInput;
    }

    public void banPlayer(PlayerObject player, PunishmentReason reason) {
        //Bukkit.broadcastMessage(parsePlaceholders(config.getString("BanReason"), reason));
        Bukkit.getBanList(BanList.Type.IP).addBan(player.ip, parsePlaceholders(config.getString("BanReason"), reason), new Date(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis() + reason.length.getTime()), null);
        dataHandler.addPunishment(Bukkit.getOfflinePlayer(player.username).getUniqueId().toString(), reason);
        if (Bukkit.getOfflinePlayer(player.username).isOnline()) {
            Bukkit.getPlayer(player.username).kickPlayer(parsePlaceholders(ChatColor.translateAlternateColorCodes('&', config.getString("BanReason")), reason));
        }
    }

    public String parsePlaceholders(String stringToParse, PunishmentReason punishmentReason) {
        String output = stringToParse;
        output = output.replaceAll("\\[player]", punishmentReason.playerName);
        output = output.replaceAll("\\[staff]", punishmentReason.punishedBy);
        output = output.replaceAll("\\[reason]", punishmentReason.reason);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(punishmentReason.length);

        String punishmentLength = "";
        if (calendar.get(Calendar.DAY_OF_YEAR) != 0) {
            punishmentLength = punishmentLength + " " + calendar.get(Calendar.DAY_OF_YEAR) + " days";
        }
        if (calendar.get(Calendar.HOUR) != 0) {
            punishmentLength = punishmentLength + " " + calendar.get(Calendar.HOUR) + " hours";
        }
        if (calendar.get(Calendar.MINUTE) != 0) {
            punishmentLength = punishmentLength + " " + calendar.get(Calendar.MINUTE) + " minutes";
        }
        if (calendar.get(Calendar.SECOND) != 0) {
            punishmentLength = punishmentLength + " " + calendar.get(Calendar.SECOND) + " seconds";
        }

        calendar.setTime(new Date((punishmentReason.created.getTime() + punishmentReason.length.getTime()) - Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis()));

        String timeLeft = "";
        if (calendar.get(Calendar.DAY_OF_YEAR) != 0) {
            timeLeft = timeLeft + " " + calendar.get(Calendar.DAY_OF_YEAR) + " days";
        }
        if (calendar.get(Calendar.HOUR) != 0) {
            timeLeft = timeLeft + " " + calendar.get(Calendar.HOUR) + " hours";
        }
        if (calendar.get(Calendar.MINUTE) != 0) {
            timeLeft = timeLeft + " " + calendar.get(Calendar.MINUTE) + " minutes";
        }
        if (calendar.get(Calendar.SECOND) != 0) {
            timeLeft = timeLeft + " " + calendar.get(Calendar.SECOND) + " seconds";
        }

        output = output.replaceAll("\\[timeLeft]", timeLeft);
        output = output.replaceAll("\\[time]", punishmentLength);
        return output;
    }

    public Date parseLengthString(String length) {
        String lastCharacter = length.substring(length.length() - 1).toLowerCase();
        int duration = parseInt(length.substring(0, length.length() - 1));
        long milliseconds;

        switch (lastCharacter) {
            case "d":
                milliseconds = TimeUnit.DAYS.toMillis(duration);
                break;
            case "h":
                milliseconds = TimeUnit.HOURS.toMillis(duration);
                break;
            case "m":
                milliseconds = TimeUnit.MINUTES.toMillis(duration);
                break;
            case "s":
                milliseconds = TimeUnit.SECONDS.toMillis(duration);
                break;
            default:
                return null;
        }
        return new Date(milliseconds);
    }
}

