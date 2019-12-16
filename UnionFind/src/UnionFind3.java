public class UnionFind3 implements UF{   // 第三版并查集：基于每颗树的大小对树进行合并

    private int[] parent;   // 表示第i个元素指向哪个节点
    private int[] sz;   // sz[i]表示以i为根的集合中元素的个数

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i ++) {
            parent[i] = i;
            sz[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程， 查找元素p所对应的集合编号
    // O(h)复杂度， h为树的高度
    private int find(int p){

        if (p < 0 || p > parent.length)
            throw new IllegalArgumentException("p is out of bound");

        while (p != parent[p])   // 从这个元素所在的节点开始向上查找，直至找见这个元素所在树的根节点
            p = parent[p];
        return p;
    }


    // 查看元素p和元素q是否属于同一个集合
    // O(h)复杂度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)
    @Override
    public void unionElements(int p, int q) {

        int pROOT = find(p);
        int qROOT = find(q);

        if (pROOT == qROOT){
            return;
        }


        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if (sz[pROOT] < sz[qROOT]) {
            parent[pROOT] = qROOT;
            sz[qROOT] += sz[pROOT];
        }
        else{
            parent[qROOT] = pROOT;
            sz[pROOT] += qROOT;
        }
    }
}
