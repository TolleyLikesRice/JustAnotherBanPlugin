package tolley.jabp.handlers;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import tolley.jabp.PlayerObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataHandler {
    String dataFolder;

    public boolean initDataHandler(String path) throws IOException {
        dataFolder = path + "/UserData/";
        Bukkit.getLogger().info("DataHandler initialized with data path " + dataFolder);
        Path directory = Paths.get(dataFolder);
            Bukkit.getLogger().info("createDirectories returned " + Files.createDirectories(directory));
        return true;
    }

    // Key: UUID List data: Username, IP, Type1 Violations, Type2 Violations, Type3 Violations, Type4 Violations, Type5 Violations

    public PlayerObject getPlayerData(String uuid) {
        try {
            //Read File
            String fileContents = "";
            File playerFile = new File(dataFolder + uuid + ".json");
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

    public void modifyType1(String uuid, int newAmount) {
        PlayerObject playerData = getPlayerData(uuid);
        playerData.type1 = newAmount;
        savePlayerData(uuid, playerData);
    }
    public void modifyType2(String uuid, int newAmount) {
        PlayerObject playerData = getPlayerData(uuid);
        playerData.type2 = newAmount;
        savePlayerData(uuid, playerData);
    }
    public void modifyType3(String uuid, int newAmount) {
        PlayerObject playerData = getPlayerData(uuid);
        playerData.type3 = newAmount;
        savePlayerData(uuid, playerData);
    }
    public void modifyType4(String uuid, int newAmount) {
        PlayerObject playerData = getPlayerData(uuid);
        playerData.type4 = newAmount;
        savePlayerData(uuid, playerData);
    }
    public void modifyType5(String uuid, int newAmount) {
        PlayerObject playerData = getPlayerData(uuid);
        playerData.type5 = newAmount;
        savePlayerData(uuid, playerData);
    }

}
