package com.gzzsc.lai;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @className ReadWriteMap
 * @Deacription 使用读写锁包装map
 * @Author laizs
 * @Date 2021/7/5 17:39
 **/
public class ReadWriteMap<K,V> {
    private final Map<K,V> map;
    private final ReadWriteLock lock=new ReentrantReadWriteLock();
    private final Lock r=lock.readLock();
    private final Lock w=lock.writeLock();
    public ReadWriteMap(Map<K,V> map){
        this.map=map;
    }
    public V put(K k,V v){
        w.lock();
        try {
            return map.put(k,v);
        }
        finally {
            w.unlock();
        }
    }
    public V get(K k){
        r.lock();
        try {
            return map.get(k);
        }finally {
            r.unlock();
        }
    }
}
