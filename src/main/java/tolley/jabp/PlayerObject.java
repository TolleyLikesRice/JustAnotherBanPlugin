package tolley.jabp;

public class PlayerObject {
    public String username;
    public String ip;
    public int type1 = 0;
    public int type2 = 0;
    public int type3 = 0;
    public int type4 = 0;
    public int type5 = 0;
    public PlayerObject(String playerUsername, String ipAddress) {
        username = playerUsername;
        ip = ipAddress;
    }
}
