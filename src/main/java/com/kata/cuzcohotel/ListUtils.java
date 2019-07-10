package com.kata.cuzcohotel;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List substract(List mainList, List listToSubstract) {
        List<Room> copy = new ArrayList<>(mainList);
        copy.removeAll(listToSubstract);
        return copy;
    }
}
