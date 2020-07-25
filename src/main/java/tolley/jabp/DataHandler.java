package tolley.jabp;

import com.google.gson.Gson;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class PlayerObject {
    public String username;
    public String ip;
    public int type1 = 0;
    public int type2 = 0;
    public int type3 = 0;
    public int type4 = 0;
    public int type5 = 0;
    PlayerObject(String playerUsername, String ipAddress) {
        username = playerUsername;
        ip = ipAddress;
    }
}

public class DataHandler {
    String dataFolder;
    public boolean initDataHandler(String path) {
        dataFolder = path;
        Bukkit.getLogger().info("DataHandler initialized with data path " + dataFolder);
        return true;
    }

    // Key: UUID List data: Username, IP, Type1 Violations, Type2 Violations, Type3 Violations, Type4 Violations, Type5 Violations

    public PlayerObject getPlayerData(String uuid) {
        try {
            //Read File
            String fileContents = "";
            File playerFile = new File(dataFolder + uuid);
            Scanner myReader = new Scanner(playerFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fileContents = fileContents + data;
            }
            myReader.close();

            //Parse JSON
            Gson gson = new Gson();
            PlayerObject playerData = gson.fromJson(fileContents, PlayerObject.class);

            return playerData;
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning("File not found for player: " + uuid);
            return null;
        }

    }
    public void savePlayerData(String uuid, PlayerObject player) {
        //Creating the file
        try {
            File playerFile = new File(dataFolder + uuid + ".json");
            if (playerFile.createNewFile()) {
                System.out.println("File created: " + playerFile.getName());
            } else {
                Bukkit.getLogger().info("Player " + uuid + " already has a file, not creating a new one");
            }

            //Generate JSON
            Gson gson = new Gson();
            String json = gson.toJson(player);


            //Write JSON to file
            try {
                FileWriter playerWriter = new FileWriter(dataFolder + uuid + ".json");
                playerWriter.write(json);
                playerWriter.close();
                Bukkit.getLogger().info("Successfully wrote json for " + uuid);
            } catch (IOException e) {
                Bukkit.getLogger().info("An error occurred while writing the data file for " + uuid);
            }
        } catch (IOException e) {
            Bukkit.getLogger().warning("An error occurred while creating the data file for " + uuid);
        }
    }
}