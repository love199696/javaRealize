package com.dataSructure.Demo3_1;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-16 17:26
 */
public interface MyMap<Key,Value> {


    void put(Key key,Value value);
    Value get(Key key);
    void delete(Key key);
    boolean  contains(Key key);
    boolean isEmpty();
    int size();
    Iterable<Key> keys();
}
