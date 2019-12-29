import java.util.TreeMap;

public class HashTable<K, V> {

    private TreeMap[] hashtable;
    private int M;   // 哈希表长度,数组长度
    private int size;   // 已经存储的数

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];

        for (int i = 0; i < M; i ++)
            hashtable[i] = new TreeMap<>();
    }

    public HashTable(){
        this(97);
    }

    // 求哈希值,将传入的值映射为表中的索引
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    // 添加一个（key， value）的map
    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        // 当成传入到二分搜索树的一个头节点中

        if (map.containsKey(key))
            map.put(key, value);
        else{
            map.put(key, value);
            size ++;
        }
    }

    // 移除key对应的值
    public V remove(K key){

        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;

        if (map.containsKey(key)){
            ret = map.remove(key);
            size --;
        }

        return ret;
    }


    // 修改key对应的值
    public void set(K key, V value){

        TreeMap<K, V> map = hashtable[hash(key)];

        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + "doesn`t exist!");

        map.put(key, value);
    }

    // 是否存在某一个key
    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    // 获得key对应的value
    public V get(K key){
        return (V) hashtable[hash(key)].get(key);
    }


}
