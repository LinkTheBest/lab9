package com.gnida.izkadetov.commandsRealization;


import com.gnida.izkadetov.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMinElement {
    private SpaceMarine newObject;

    public boolean makeDecision(ArrayDeque<SpaceMarine> spcArr) {
        List<SpaceMarine> tempList = Collections.synchronizedList(new ArrayList<>(spcArr));
        synchronized (tempList) {
            newObject = tempList.get(tempList.size() - 1);
            tempList.remove(tempList.size() - 1);
            SpaceMarine tempObject = null;
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
                    return true;
                } else {
                    System.out.println("Объект не был добавлен");
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }

    public SpaceMarine getNewElement() {
        return newObject;
    }
}

