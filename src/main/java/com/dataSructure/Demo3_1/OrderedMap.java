package com.dataSructure.Demo3_1;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-18 18:51
 */
public interface OrderedMap<Key extends Comparable,Value>  extends  MyMap<Key,Value>{

    /**
    *获取最小的key
    **/
    Key min();
    /**
     *获取最大的key
     **/
    Key max();

    /**
    *获取一个小于等于 key的最大Key
    **/
    Key floor(Key key);


    /**
     *获取一个大于等于 key的最小Key
     **/
    Key ceil(Key key);
    
    /**
    *小与key 的键的数量
    **/
    int rank(Key key);

    /**
     *获取第index个key
     **/
    Key select(int index);

    
    /**
    *删除最小的key
    **/
    void deleteMin();

    /**
    *删除最大的key
    **/
    void deleteMax();

    /**
    *获取 lo 到hi之间有多少个key
    **/
    int size(Key lo,Key hi);
    /**
     *获取 lo 到hi之间的key
     **/
    Iterable<Key> keys(Key lo,Key hi);

    /**
     *获取 所有的key
     **/
    Iterable<Key> keys();

}
