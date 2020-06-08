package com.ygy.liberal.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author guoyao
 * @date 2020-05-17
 */
public class HotColdLruCache<K,V> {

    private LruCache<K,V> hotLruCache;

    private LruCache<K,LruMonitorWrapper<V>> coldLruCache;

    public static void main(String[] args) {
        HotColdLruCache<String, String> hotColdLruCache = new HotColdLruCache<>(100, 4);
        for (int i = 0; i < 1000; i++) {
            hotColdLruCache.put("" + i, "" + i);
        }
    }

    public HotColdLruCache(int capacity, int hotColdRadio) {
        int hotCapacity = capacity * hotColdRadio / (hotColdRadio + 1);
        if (hotCapacity == capacity) {
            throw new IllegalStateException();
        }
        this.hotLruCache = new LruCache<>(hotCapacity, 16, 1.0f, true);
        this.coldLruCache = new LruCache<>(capacity - hotCapacity, 16, 1.0f, true);
    }


    public synchronized V get(K key) {
        V hotvalue = hotLruCache.get(key);
        if (hotvalue != null) {
            return hotvalue;
        }

        LruMonitorWrapper<V> coldWrapper = coldLruCache.get(key);
        if (coldWrapper != null) {
            //判断是否需要晋升
            int accessTimes = coldWrapper.accessTimes;
            long addTime = coldWrapper.addTime;
            //超过15次,并且时间大于5分钟
            if (accessTimes > 10 && ((System.currentTimeMillis() - addTime) > 5 * 60 * 1000)) {
                hotLruCache.put(key, coldWrapper.value);
                coldLruCache.remove(key);
            } else {
                coldWrapper.accessTimes = accessTimes + 1;
            }
            return coldWrapper.value;
        }
        return null;
    }

    public synchronized V put(K key, V value) {
        if (hotLruCache.containsKey(key)) {
            hotLruCache.remove(key);
        }

        LruMonitorWrapper<V> old = coldLruCache.put(key, new LruMonitorWrapper<>(value));

        return old == null ? null : old.value;
    }



    private static class LruMonitorWrapper<V> {

        private V value;

        private int accessTimes;

        private long addTime = System.currentTimeMillis();

        private LruMonitorWrapper(V value) {
            this.value = value;
            this.accessTimes = 1;
        }
    }



    private static class LruCache<K,V> extends LinkedHashMap<K,V> {

        private int maxSize;

        private LruCache(int maxSize,int initialCapacity, float loadFactor,boolean accessOrder) {
            super(initialCapacity, loadFactor, accessOrder);
            this.maxSize = maxSize;
        }


        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maxSize;
        }
    }




}

 class LRUCache {

    Entry head, tail;
    int capacity;
    int size;
    Map<Integer, Entry> cache;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        // 初始化链表
        initLinkedList();
        size = 0;
        cache = new HashMap<>(capacity + 2);
    }

    /**
     * 如果节点不存在，返回 -1.如果存在，将节点移动到头结点，并返回节点的数据。
     *
     * @param key
     * @return
     */
    public int get(int key) {
        Entry node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 存在移动节点
        moveToHead(node);
        return node.value;
    }

    /**
     * 将节点加入到头结点，如果容量已满，将会删除尾结点
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Entry node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }
        // 不存在。先加进去，再移除尾结点
        // 此时容量已满 删除尾结点
        if (size == capacity) {
            Entry lastNode = tail.pre;
            deleteNode(lastNode);
            cache.remove(lastNode.key);
            size--;
        }
        // 加入头结点

        Entry newNode = new Entry();
        newNode.key = key;
        newNode.value = value;
        addNode(newNode);
        cache.put(key, newNode);
        size++;

    }

    private void moveToHead(Entry node) {
        // 首先删除原来节点的关系
        deleteNode(node);
        addNode(node);
    }

    private void addNode(Entry node) {
        head.next.pre = node;
        node.next = head.next;

        node.pre = head;
        head.next = node;
    }

    private void deleteNode(Entry node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }


    public static class Entry {
        public Entry pre;
        public Entry next;
        public int key;
        public int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
        }
    }

    private void initLinkedList() {
        head = new Entry();
        tail = new Entry();

        head.next = tail;
        tail.pre = head;

    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));

    }
}
