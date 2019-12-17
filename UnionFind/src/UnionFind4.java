public class UnionFind4 implements UF{   // 第四版并查集：基于每颗树的深度对树进行合并

    private int[] parent;   // 表示第i个元素指向哪个节点

    private int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i ++)  {
            parent[i] = i;
            rank[i] = i;
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


        // 根据两个元素所在树的rank个数不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if (rank[pROOT] < rank[qROOT]) {
            parent[pROOT] = qROOT;
        }
        else if (rank[qROOT] < rank[pROOT]){
            parent[qROOT] = pROOT;
        }
        else{   // rank[qROOT] == rank[pROOT]
            parent[qROOT] = pROOT;
            rank[pROOT] += 1;
        }
    }
}
