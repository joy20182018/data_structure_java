public interface UF {
    int getSize();
    boolean isConnected(int p, int q);   // 真正关心的是ID为p和ID为q的两个元素是否连接或是否同属于一个集合
    void unionElements(int p, int q);   // 合并两个元素至一个集合中,使得这两个元素所属的集合也连为一个
}
