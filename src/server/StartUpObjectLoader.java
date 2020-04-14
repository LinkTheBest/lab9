package server;

import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class StartUpObjectLoader {
    private Collection collection = new Collection();
    private ArrayDeque<SpaceMarine> spaceDeque;
    private JsonDataHandler jsonDataHandler;
    private int collectionSize;

    public StartUpObjectLoader(int collectionSize, JsonDataHandler jsonDataHandler) {
        this.collectionSize = collectionSize;
        this.jsonDataHandler = jsonDataHandler;
        spaceDeque = new ArrayDeque<>(loader());
        collection.setObjects(getSpaceDeque());
    }

    public List<SpaceMarine> loader() {
        List<SpaceMarine> startObjList = new ArrayList<>();
        for (int counter = 0; counter < collectionSize; counter++) {
            SpaceMarine tempSpc = new SpaceMarine();
            tempSpc.setName(jsonDataHandler.getName(counter));
            if (jsonDataHandler.getId(counter) != 0) {
                tempSpc.setId(jsonDataHandler.getId(counter));
            }
            tempSpc.setHealth(jsonDataHandler.getHealth(counter));
            tempSpc.setCoordinates(jsonDataHandler.getCoordinates(counter));
            tempSpc.setCategory(jsonDataHandler.getAstartesCategory(counter));
            tempSpc.setWeaponType(jsonDataHandler.getWeapon(counter));
            tempSpc.setMeleeWeapon(jsonDataHandler.getMeleeWeapon(counter));
            tempSpc.setChapter(jsonDataHandler.getChapter(counter));
            startObjList.add(tempSpc);
        }
        return startObjList;
    }

    public ArrayDeque<SpaceMarine> getSpaceDeque(){
        return spaceDeque;
    }
}