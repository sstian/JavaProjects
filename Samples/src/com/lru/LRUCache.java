package com.lru;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * 使用 LinkedHashMap 实现的一个 LRU 缓存
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    // 设定最大缓存空间 MAX_ENTRIES 为 3
    private static final int MAX_ENTRIES = 3;

    // 覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    // 使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序
    public LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.get(1);
        cache.put(4, "d");
        System.out.println(cache.keySet());
    }
}
/*
[3, 1, 4]
 */