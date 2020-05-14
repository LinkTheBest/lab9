package com.gnida.izkadetov.commandsRealization;


import com.gnida.izkadetov.SpaceMarine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LowerRemover {

    public Deque<SpaceMarine> removeLowerElement(List<SpaceMarine> input_deque) {
        List<SpaceMarine> templist = input_deque;
        SpaceMarine lastAddedElement = templist.get(input_deque.size() - 1);
        for (SpaceMarine spc : templist) {
            if (spc.getId() < lastAddedElement.getId()) {
                templist.remove(spc);
            }
        }
        Deque<SpaceMarine> tempDeque = new ArrayDeque<>(templist);
        return tempDeque;
    }

}
