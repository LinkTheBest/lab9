package com.gnida.izkadetov.commandsRealization;


import com.gnida.izkadetov.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMaxElement {

    public ArrayDeque<SpaceMarine> makeDecision(ArrayDeque<SpaceMarine> spcArr) {
        List<SpaceMarine> tempList = Collections.synchronizedList(new ArrayList<>(spcArr));
        synchronized (tempList) {
            SpaceMarine newObject = tempList.get(tempList.size() - 1);
            SpaceMarine tempObject = null;
            tempList.remove(tempList.size() - 1);
            int maxId = 0;
            for (SpaceMarine spc : tempList) {
                if (spc.getId() > maxId) {
                    maxId = spc.getId();
                    tempObject = spc;
                }
            }
            try {
                if (tempObject.getId() < newObject.getId()) {
                    tempList.add(newObject);
                    System.out.println("Объект был добавлен");
                } else {
                    System.out.println("Объект не был добавлен");
                }
            } catch (Exception e) {
            }
            ArrayDeque<SpaceMarine> tempDeque = new ArrayDeque(tempList);
            return tempDeque;
        }
    }
}
