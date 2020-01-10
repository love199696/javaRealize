package com.dataSructure.Demo3_1;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-16 17:11
 */
public class ArraySt<Key,Value> {

    private Key[] keys;

    private Value[] values;

    private int len =0;

    public ArraySt() {
        this.keys =(Key[]) new Object[100];
        this.values = (Value[]) new Object[100];
    }

    public static void main(String[] arge){
        ArraySt<String,Integer> arraySt = new ArraySt<>();
        arraySt.put("1",6);
        arraySt.put("2",5);
        arraySt.put("3",4);
        arraySt.put("4",3);
        arraySt.put("5",2);
        arraySt.put("6",1);

        System.out.println(arraySt.get("1"));;
        System.out.println(arraySt.get("2"));;
        System.out.println(arraySt.get("3"));;
        System.out.println(arraySt.get("4"));;
        System.out.println(arraySt.get("5"));;
        System.out.println(arraySt.get("6"));;


        arraySt.delete("6");
        System.out.println(arraySt.get("6"));

        arraySt.delete("6");
        System.out.println(arraySt.contains("5"));

        System.out.println(arraySt.isEmpty());

    }


    public void put(Key key,Value value){
        for (int i = 0; i < len; i++) {
            if(keys[i].equals(key)){
                values[i] =value;
                return;
            }
        }
        keys[len] = key;
        values[len] = value;
        len ++;
    }
    public Value get(Key key){
        for (int i = 0; i < len; i++) {
            if(keys[i].equals(key)){
                return values[i];
            }
        }
        return  null;
    }

    public void delete(Key key){
        put(key, null);
        len--;
    }

    public boolean contains(Key key){
        for (int i = 0; i < len; i++) {
            if(keys[i].equals(key)){
                return values[i] !=null;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return len ==0;
    }


}
