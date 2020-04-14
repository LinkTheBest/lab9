package server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spaceMarineProperties.*;

import java.math.BigDecimal;
import java.io.*;

public class JsonDataHandler {

    private String jsonFileName;
    private File jsonFile;

    public JsonDataHandler(String fileName){
        jsonFileName = fileName;
        jsonFile = new File(jsonFileName);
        if(!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
            }catch (IOException e){}
        }
    }
    public JsonDataHandler(){}

    private JSONParser jsonParser = new JSONParser();

    public Integer getJsonCollectionSize() {
        int counter = 0;
        try {
            //System.out.println(jsonFileName);
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            for (Object obj : json_array) {
                counter++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return counter;
    }

    public String getName(int index) {
        String fromJsonName = null;
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            fromJsonName = (String) json_data.get("name");
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return fromJsonName;
    }

    public int getId(int index) {
        int tempId = 0;
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            tempId = BigDecimal.valueOf((Long) json_data.get("id")).intValue();
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return tempId;

    }

    public Coordinates getCoordinates(int index) {
        Coordinates coordinates = new Coordinates();
        Double jsonX = 0.0;
        Float jsonY = 0f;
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            jsonX = (Double) json_data.get("coordinate_x");
            jsonY = BigDecimal.valueOf((Double) json_data.get("coordinate_y")).floatValue();
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        coordinates.setX(jsonX);
        coordinates.setY(jsonY);
        return coordinates;
    }


    public int getHealth(int index) {
        Integer jsonHealth = 0;
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            jsonHealth = BigDecimal.valueOf((Long) json_data.get("health")).intValue();
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return jsonHealth;
    }

    public AstartesCategory getAstartesCategory(int index) {
        AstartesCategory jsonCategory = null;
        String temp_comparison;
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            temp_comparison = (String) json_data.get("category");
            switch (temp_comparison) {
                case "AGGRESSOR":
                    jsonCategory = AstartesCategory.AGGRESSOR;
                    break;
                case "INCEPTOR":
                    jsonCategory = AstartesCategory.INCEPTOR;
                    break;
                case "TACTICAL":
                    jsonCategory = AstartesCategory.TACTICAL;
                    break;
                case "TERMINATOR":
                    jsonCategory = AstartesCategory.TERMINATOR;
                    break;
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return jsonCategory;
    }

    public Weapon getWeapon(int index) {
        Weapon jsonWeapon = null;
        String temp_comparison;
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            temp_comparison = (String) json_data.get("weapon");
            switch (temp_comparison) {
                case "MELTAGUN":
                    jsonWeapon = Weapon.MELTAGUN;
                    break;
                case "BOLT_PISTOL":
                    jsonWeapon = Weapon.BOLT_PISTOL;
                    break;
                case "COMBI_FLAMER":
                    jsonWeapon = Weapon.COMBI_FLAMER;
                    break;
                case "COMBI_PLASMA_GUN":
                    jsonWeapon = Weapon.COMBI_PLASMA_GUN;
                    break;
                case "MISSILE_LAUNCHER":
                    jsonWeapon = Weapon.MISSILE_LAUNCHER;
                    break;
                default:
                    jsonWeapon = null;
                    break;
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return jsonWeapon;

    }

    public MeleeWeapon getMeleeWeapon(int index) {
        MeleeWeapon jsonMeleeWeapon = null;
        String temp_comparison;
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject jsonData = (JSONObject) jsonArray.get(index);
            temp_comparison = (String) jsonData.get("melee_weapon");
            switch (temp_comparison) {
                case "CHAIN_SWORD":
                    jsonMeleeWeapon = MeleeWeapon.CHAIN_SWORD;
                    break;
                case "MANREAPER":
                    jsonMeleeWeapon = MeleeWeapon.MANREAPER;
                    break;
                case "POWER_BLADE":
                    jsonMeleeWeapon = MeleeWeapon.POWER_BLADE;
                    break;
                default:
                    jsonMeleeWeapon = null;
                    break;
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return jsonMeleeWeapon;

    }

    public Chapter getChapter(int index) {
        Chapter chapter = new Chapter();
        try {
            JSONArray json_array = (JSONArray) jsonParser.parse(new FileReader(jsonFileName));
            JSONObject json_data = (JSONObject) json_array.get(index);
            chapter.setName((String) json_data.get("chapter"));
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException pe) {
            System.err.println(pe);
        }
        return chapter;
    }

}
