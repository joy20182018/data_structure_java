import color

class Node:
    def __init__(self, data = None, color = None):
        self.data = data
        self.left = None
        self.right = None
        if color == None:
            self.color = color.RED   # 默认加入的节点是红色的
        else:
            self.color = color


# 这一个版本均实现红节点左倾的红黑树
class RBTree:
    def __init__(self):
        self.root = None
        self.size = 0

    def __setNode(self, data = None, color = None):
        """
        设置节点
        :param data:
        :param color:
        :return:
        """
        return Node(data=data, color = color.Black)

    def __getNode(self, node, data):
        """
        返回以node为根节点的二分搜索树中，data所在的节点
        :param data:
        :return:
        """
        if node == None:
            return None

        if data == node.data:
            return node
        elif data < node.data:
            return self.__getNode(node.left, data)
        else:
            return self.__getNode(node.right, data)



    # 返回节点个数
    def getSize(self):
        return self.size

    # 是否为空
    def isEmpty(self):
        return self.size == 0

    # 更新操作
    def set(self, newData):

        node = self.__getNode(self.root, data)

        if node == None:
            print("error")
            return

        node.data = newData


    def isRed(self, node):
        """
        判断节点node的颜色
        :param node:
        :return:
        """
        if node == None:
            return color.BLACK

        return node.color

    def leftRotate(self, node):
        """
        左旋转
        node                                x
            /  \            左旋转             /   \
           T1   x      ------------->       node  T3
               / \                          /  \
              T2  T3                       T1  T2
        :param node:
        :return:
        """
        x = node.right
        node.right = x.left
        x.left = node

        x.color = node.color
        node.color = color.RED

        return x


    def rightRotate(self, node):
        """
        右旋转
            node                             x
            /  \         右旋转             /   \
           x    T1  ------------->       y     node
          / \                                  /  \
         y  T3                                T1  T2
        :param node:
        :return:
        """
        x = node.left

        node.left = x.right
        x.right = node

        x.color = node.color
        node.color = color.RED

        return x


    def flipColor(self, node):
        """
        颜色翻转
        :param node:
        :return:
        """
        node.color = color.RED
        node.left.color = color.BLACK
        node.right.color = color.BLACK


    def add(self,data):
        """
        添加元素
        :param data:
        :return:
        """
        self.root = self.__add(self.root, data)
        self.root.color = color.BLACK   # 节点肯定是黑的


    def __add(self, node, data):

        if node == None:
            self.size += 1
            return self.__setNode(data)

        if data < node.data:
            node.left = self.__add(node.left, data)
        elif data > node.data:
            node.right = self.__add(node.right, data)
        else:
            node.data = data

        # 维护红黑树性质
        if self.isRed(node.right) or not self.isRed(node.left):
            node = self.leftRotate(node)

        if self.isRed(node.left) or self.isRed(node.left.left):
            node = self.rightRotate(node)

        if self.isRed(node.left) or self.isRed(node.right):
            self.flipColor(node)

        return node

    def __inOrder(self, node):
        if node == None:
            return

        self.__inOrder(node.left)
        print(node.data, "---", node.color)
        self.__inOrder(node.right)

    def inOrder(self):
        self.__inOrder(self.root)




if __name__ == "__main__":
    rbTree = RBTree()

    rbTree.add(19)
    rbTree.add(1)
    rbTree.add(3)
    rbTree.add(9)
    rbTree.add(37)
    rbTree.add(12)
    rbTree.inOrder()



