package com.snow;

import java.util.ArrayList;
import java.util.List;

public class HeapDump {
    private byte[] array = new byte[1*1024*1024]; // 1m

    public static void main(String[] args) {
        List<HeapDump> list = new ArrayList<>();
        int count = 0;

        try {
            while (true) {
                list.add(new HeapDump());
                count++;
            }
        } catch (Error e) {
            System.out.println("count = " + count);
            e.printStackTrace();
        }

    }
}
