package commandsRealization;

import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class FindMinElement {

    public Deque<SpaceMarine> makeDecision(List<SpaceMarine> input_deque) {
        List<SpaceMarine> tempList = input_deque;
        SpaceMarine newObject = tempList.get(input_deque.size() - 1);
        SpaceMarine tempObject = null;
        tempList.remove(input_deque.size() - 1);
        int maxId = 9999;
        for (SpaceMarine spc : tempList) {
            if (spc.getId() < maxId) {
                maxId = spc.getId();
                tempObject = spc;
            }
        }
        try {
            if (tempObject.getId() > newObject.getId()) {
                input_deque.add(newObject);
                System.out.println("Объект был добавлен");
            } else {
                System.out.println("Объект не был добавлен");
            }
        } catch (Exception e) {
        }
        Deque<SpaceMarine> temp_deque = new ArrayDeque<>(tempList);
        return temp_deque;
    }
}

