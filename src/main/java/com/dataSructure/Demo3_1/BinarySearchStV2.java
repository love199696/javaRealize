package com.dataSructure.Demo3_1;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-18 19:20
 */
public class BinarySearchStV2<Key extends Comparable, Value> implements OrderedMap<Key, Value> {


    private Key[] keys;

    private Value[] values;

    private int len;

    public BinarySearchStV2(int length) {
        keys = (Key[]) new Comparable[length];
        values = (Value[]) new Object[length];
    }

    public static void main(String[] arge) {
        BinarySearchStV2<Integer, Integer> binarySearchSt = new BinarySearchStV2(100);
        for (int i = 0; i < 10; i++) {
            binarySearchSt.put(StdRandom.uniform(10), StdRandom.uniform(10));
        }
        System.out.print(binarySearchSt.get(1) + " ");
        System.out.print(binarySearchSt.get(3) + " ");
        System.out.print(binarySearchSt.get(5) + " ");
        System.out.print(binarySearchSt.get(7) + " ");
        System.out.print(binarySearchSt.get(9) + " ");
        System.out.println();

        System.out.print(binarySearchSt.min() + " ");
        System.out.print(binarySearchSt.max() + " ");

        System.out.print(binarySearchSt.floor(5) + " ");
        System.out.print(binarySearchSt.ceil(5) + " ");
        System.out.println(binarySearchSt.rank(5) + " ");
        System.out.println();

        binarySearchSt.deleteMax();
        binarySearchSt.deleteMin();

        System.out.println();


    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[len - 1];
    }

    @Override
    public Key floor(Key key) {
        int index;
        for (index = 0; index < len; index++) {
            if (keys[index].compareTo(key) > 0) {
                break;
            }
        }
        if (index == len) return null;
        return keys[index - 1];
    }

    @Override
    public Key ceil(Key key) {
        int index;
        for (index = len - 1; index > 0; index--) {
            if (keys[index].compareTo(key) < 0) {
                break;
            }
        }
        if (index < 0) return null;
        return keys[index + 1];
    }

    @Override
    public int rank(Key key) {
        int index;
        for (index = 0; index < len; index++) {
            int result = keys[index].compareTo(key);
            if (result >= 0) {
                break;
            }
        }
        return index;
    }

    @Override
    public Key select(int index) {
        return keys[index];
    }

    @Override
    public void deleteMin() {
        deleteByIndex(0);


    }

    @Override
    public void deleteMax() {
        deleteByIndex(len);
    }

    @Override
    public int size(Key lo, Key hi) {
        return len;
    }


    @Override
    public void put(Key key, Value value) {
        int rank = rank(key);

        if (rank < len && keys[rank].compareTo(key) == 0) {
            values[rank] = value;
            return;
        }
        for (int i = len; i > rank; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[rank] = key;
        values[rank] = value;
        len++;
    }


    @Override
    public Value get(Key key) {
        return getByIndex(key, 0, len - 1);
    }

    private Value getByIndex(Key key, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;
        int result = key.compareTo(keys[mid]);
        if (result == 0) {
            return values[mid];
        } else if (result < 0) {
            return getByIndex(key, lo, mid - 1);
        } else {
            return getByIndex(key, mid + 1, hi);
        }
    }


    @Override
    public void delete(Key key) {
        int rank = rank(key);
        if (rank > len) return;
        deleteByIndex(rank);
    }

    private void deleteByIndex(int rank) {


        for (int i = rank; i < len - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        len--;
        keys[len] = null;
        values[len] = null;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }
}
