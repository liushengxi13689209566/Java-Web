package com.huarun.test;

import java.util.List;

public class PrintTest {
    public static void printList(List<Object> objectList) {
        for (Object obj : objectList) {
            System.out.println("obj == " + obj.toString());
        }
    }
}
