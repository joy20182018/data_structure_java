import java.util.TreeMap;

// 使用动态数组的方式对哈希表进行动态空间处理，使得每次扩容都是一个素数
public class HashTable3<K, V> {

    private final int[] capacity = {
            13,   19,   29,   41,   59,   79,   107,   149,   197,   263,   347,   457,   599,   787,   1031,
            1361,   1777,   2333,   3037,   3967,   5167,   6719,   8737,   11369,   14783,
            19219,   24989,   32491,   42257,   54941,   71429,   92861,   120721,   156941,
            204047,   265271,   344857,   448321,   582821,   757693,   985003,   1280519,
            1664681,   2164111,   2813353,   3657361,   4754591,   6180989,   8035301,
            10445899,   13579681,   17653589,   22949669,   29834603,   38784989,
            50420551,   65546729,   85210757,   110774011,   144006217,   187208107,
            243370577,   316381771,   411296309,   534685237,   695090819,   903618083,
            1174703521,   1527114613,   1837299131,   2147483647
    };   // 扩容或缩容的变化值

    private static final int upperTol = 10;   // 扩容的上界
    private static final int lowerTol = 2;   // 缩容的下界
    private int capacityIndex = 0;   // 初始的容量的索引


    private TreeMap[] hashtable;
    private int M;   // 哈希表长度,数组长度
    private int size;   // 已经存储的数

    public HashTable3(){
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];

        for (int i = 0; i < M; i ++)
            hashtable[i] = new TreeMap<>();
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

            if (size >= upperTol * M && capacityIndex +1 < capacity.length){
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    // 移除key对应的值
    public V remove(K key){

        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;

        if (map.containsKey(key)){
            ret = map.remove(key);
            size --;

            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
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

    private void resize(int newM){

        TreeMap<K, V>[] newHashTable = new TreeMap[newM];

        for (int i = 0; i < newM; i ++)
            newHashTable[i] = new TreeMap<>();

        int oldM = M;   // 原来数组的大小
        this.M = newM;   // 新数组的大小

        for (int i = 0; i < oldM; i ++){
            TreeMap<K, V> map = hashtable[i];

            for (K key : map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashtable = newHashTable;
    }


}
