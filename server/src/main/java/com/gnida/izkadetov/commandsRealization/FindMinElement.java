package com.gnida.izkadetov.commandsRealization;


import com.gnida.izkadetov.Collection;
import com.gnida.izkadetov.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class FindMinElement {

    public ArrayDeque<SpaceMarine> makeDecision(Collection collection) {
        List<SpaceMarine> tempList = new ArrayList(collection.getObjects());
        SpaceMarine newObject = tempList.get(tempList.size() - 1);
        SpaceMarine tempObject = null;
        tempList.remove(tempList.size() - 1);
        int maxId = 1000000;
        for (SpaceMarine spc : tempList) {
            if (spc.getId() < maxId) {
                maxId = spc.getId();
                tempObject = spc;
            }
        }
        try {
            if (tempObject.getId() > newObject.getId()) {
                tempList.add(newObject);
                System.out.println("Объект был добавлен");
            } else {
                System.out.println("Объект не был добавлен");
            }
        } catch (Exception e) {
        }
        ArrayDeque<SpaceMarine> temp_deque = new ArrayDeque<>(tempList);
        return temp_deque;
    }
}

