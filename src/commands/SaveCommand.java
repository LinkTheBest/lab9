package commands;

import ComandPack.Command;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;
import spaceMarineProperties.SpaceMarine;

import java.io.*;

public class SaveCommand extends FatherOfCommands {

    public SaveCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        JSONArray toFileArray = new JSONArray();
        for (SpaceMarine spcMrn : collection.getObjects()) {
            JSONObject toJsonArrayObj = new JSONObject();
            try {
                toJsonArrayObj.put("name", spcMrn.getName());
                toJsonArrayObj.put("id", spcMrn.getId());
                toJsonArrayObj.put("coordinate_x", spcMrn.getCoordinates().getX());
                toJsonArrayObj.put("coordinate_y", spcMrn.getCoordinates().getY());
                toJsonArrayObj.put("health", spcMrn.getHealth());
                toJsonArrayObj.put("category", spcMrn.getCategory().name());
                toJsonArrayObj.put("weapon", spcMrn.getWeaponType().name());
                toJsonArrayObj.put("melee_weapon", spcMrn.getMeleeWeapon().name());
                toJsonArrayObj.put("chapter", spcMrn.getChapter().getName());
                toFileArray.add(toJsonArrayObj);
            } catch (Exception e) {
                System.out.println("Какое-то из полей не заполнено!");
            }
        }
        try {
            File finalFile = new File(System.getenv("JSON"));
            PrintWriter writer = new PrintWriter(finalFile);
            writer.print("");
            writer.close();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(finalFile));
            outputStream.write(toFileArray.toJSONString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (java.io.FileNotFoundException err) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
