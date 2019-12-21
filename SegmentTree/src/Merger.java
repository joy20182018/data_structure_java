public interface Merger<E> {
    E merge(E a, E b);   // 将两个元素融合为一个元素
}
