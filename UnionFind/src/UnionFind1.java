import javax.swing.*;

public class UnionFind1 implements UF {
    private int[] id;   // 使用数组实现
    public UnionFind1(int size){

        id = new int[size];

        for (int i = 0; i < id.length; i ++){
            // id号   集合号
            id[i] = i;   // 初始化每个id分属于不同的集合
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // 查找元素p对应的集合编号
    private int find(int p){
        if (p < 0 || p > id.length)
            throw new IllegalArgumentException("p is out of bound");
        return id[p];
    }


    // 查看元素p和元素q是否属于同一个集合
    @Override
    public boolean isConnected(int p, int q) {   // ----> O(1)
        return find(p) == find(q);
    }


    // 合并元素p和元素q所属的集合
    @Override
    public void unionElements(int p, int q) {   // ----> O(n)
        int pID = find(p);
        int qID = find(q);

        if (pID == qID){
            return;
        }
        for (int i = 0; i < id.length; i ++){
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
