package com.dab.household.utils;

import java.util.ArrayList;
import java.util.List;

public class Pager {

    public static List<Object> getList(List<?> list, Long id) {
        List<Object> pager = new ArrayList<>();
        if (list.size() >= id * 10) {
            for (long i = ((id - 1) * 10); i < id * 10 ; i++) {
                pager.add(list.get((int) i));
            }
        } else {
            for (long i = ((id - 1) * 10); i < list.size(); i++) {
                pager.add(list.get((int) i));
            }
        }
        return pager;
    }

    public static List<Integer> getPageList(List<?> list) {
        List<Integer> pageList = new ArrayList<>();

        int numberOfPage = (list.size() / 10);

        for (int i = 0; i < numberOfPage + 1; i++) {
            pageList.add(i + 1);
        }

        return pageList;
    }
}
