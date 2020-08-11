package tolley.jabp.dataTypes;

import java.util.Date;

public class PunishmentReason {
    public String playerName;
    public String reason;
    public String punishedBy;
    public Date length;
    public Date created;

    public PunishmentReason(String playerPunished, String punishmentReason, String staffPunishedBy, Date punishmentExpire, Date punishmentDate) {
        playerName = playerPunished;
        reason = punishmentReason;
        punishedBy = staffPunishedBy;
        length = punishmentExpire;
        created = punishmentDate;
    }
}
