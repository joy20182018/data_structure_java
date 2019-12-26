import random


class Node:
    def __init__(self, data = None):
        if data == None:
            self.data = 0
        else:
            self.data = data
        self.height = 0

        self.left = None
        self.right = None


class AVLTree:
    def __init__(self):
        self.root = None
        self.size = 0


    def __getNode(self, data = None):
        if data is None:
            return Node()
        else:
            return Node(data)


    def inOrder(self, Node, keys):
        """
        中序遍历
        :param Node:
        :return:
        """
        if Node is None:
            return
        self.inOrder(Node.left, keys)
        keys.append(Node.data)
        print(Node.data)
        self.inOrder(Node.right, keys)

    def isBST(self):
        """
        判断是不是二分搜索树
        :return:
        """
        keys = []
        self.inOrder(self.root, keys)

        for i in range(1, len(keys)):
            if (keys[i] < keys[i - 1]):
                return False

        # print(keys)
        return True

    def isBalanced(self):
        return self.__isBalanced(self.root)

    def __isBalanced(self, node):
        if node == None:
            return True

        balanceFactor = self.getBalanceFactor(node)
        if balanceFactor > 1:
            return False

        return self.__isBalanced(node.left) and self.__isBalanced(node.right)


    def getHeigt(self, Node):
        """
        获得节点的高度
        :param Node: 节点
        :return: 该节点的高度
        """
        if Node is None:
            return 0

        return Node.height

    def getBalanceFactor(self, node):
        """
        计算这个节点的平衡因子
        :param Node:
        :return: 平衡因子
        """
        if node is None:
            return 0

        return self.getHeigt(node.left) - self.getHeigt(node.right)

    def getNode(self, node, data):

        if node == None:
            return None

        if data == node.data:
            return node
        elif data < node.data:
            return self.getNode(node.left, data)
        else:
            return self.getNode(node.right, data)


    def rightRotate(self, node):
        """
        右旋转
        :param Node:
        :return: 此时的根节点
        """
        x = node.left
        node.left = x.right
        x.right = node

        # 更新height
        node.height = max(self.getHeigt(node.right), self.getHeigt(node.left)) + 1
        x.height = max(self.getHeigt(x.right), self.getHeigt(x.left)) + 1

        return x

    def leftRotate(self, node):
        """
        左旋转
        :param node:
        :return:
        """
        x = node.right
        node.left = x.left
        x.left = node


        # 更新height
        node.height = max(self.getHeigt(node.right), self.getHeigt(node.left)) + 1
        x.height = max(self.getHeigt(x.right), self.getHeigt(x.left)) + 1

    def add(self, data):
        """
        向AVL中添加数据
        :param data:
        :return:
        """
        self.root = self.__add(self.root, data)
        print('------------------------------------------')
        print("data = ", data)
        print('self.root.data = :', self.root.data)
        print('------------------------------------------')

    def __add(self, node, data):

        if node == None:
            self.size += 1
            node = self.__getNode(data)
            return node

        # print(node.left is None)

        if data < node.data:
            print("L <---")
            node.left = self.__add(node.left, data)
        elif data > node.data:
            print("---> R")
            node.right = self.__add(node.right, data)
        else:
            node.data = data

        # 更新height
        node.height = 1 + max(self.getHeigt(node.left), self.getHeigt(node.right))
        # 计算平衡因子
        balanceFactor = self.getBalanceFactor(node)

        # 维护平衡
        # 不平衡节点的左孩子的左孩子处添加了节点
        # LL型
        #         y
        #        /
        #       x
        #      /
        #     z
        if balanceFactor > 1 and self.getBalanceFactor(node.left) >= 0:
            print("LL")
            return self.rightRotate(node)

        # RR型
        # y
        #   \
        #     x
        #       \
        #         z
        if balanceFactor < -1 and self.getBalanceFactor(node.right) <= 0:
            print("RR")
            return self.leftRotate(node)

        # LR 型
        #       y
        #      /
        #     x
        #       \
        #         z
        if balanceFactor > 1 and self.getBalanceFactor(node.left) < 0:
            print("LR")
            node.left = self.leftRotate(node.left)
            return self.rightRotate(node)

        # RL型
        # y
        #   \
        #     x
        #    /
        #   z
        if balanceFactor < -1 and self.getBalanceFactor(node.right) > 0:
            print("RL")
            node.right = self.rightRotate(node.right)
            return self.leftRotate(node)

        return node

    def minium(self, node):
        """
        返回以node为根的AVL的最小值所在的节点
        :param node:
        :return:
        """
        if node.left == None:
            return node

        return self.minium(node.left)

    def maxmium(self, node):
        """
        返回以node为根节点的最大值所在的节点
        :param node:
        :return:
        """
        if node.right == None:
            return node

        return self.maxmium(node.right)


    def remove(self, data):
        node = self.getNode(self.root, data)

        if node != None:
            self.root = self.__remove(self.root, data)
            return node.data

        return None

    def __remove(self, node, data):
        """
         删除以node为根的二分搜索树中键为key的节点，递归算法
         返回删除节点后新的二分搜索树的根
        :param node:
        :param data:
        :return:
        """
        if node == None:
            return None

        retNode = self.__getNode()
        if data < node.data:
            node.left = self.__remove(node.left, data)
            retNode = node
        elif data > node.data:
            node.right = self.__remove(node.right, data)
            retNode = node
        else:
            if node.left == None:
                rightNode = node.right
                node.right = None
                self.size -= 1
                retNode = rightNode

            elif node.right == None:
                leftNode = node.left
                node.left = None
                self.size -= 1
                retNode = leftNode

            else:
                successor = self.minium(node.right)
                successor.right = self.__remove(node.right, successor.data)
                successor.left = node.left


        if retNode == None:
            return None

        retNode.height = 1 + max(self.getHeigt(retNode.left), self.getHeigt(retNode.right))

        balanceFactor = self.getBalanceFactor(retNode)

        if balanceFactor > 1 and self.getBalanceFactor(node.left) >= 0:
            print("LL")
            return self.rightRotate(retNode)

        if balanceFactor < -1 and self.getBalanceFactor(node.right) <= 0:
            print("RR")
            return self.leftRotate(retNode)

        if balanceFactor > 1 and self.getBalanceFactor(node.left) < 0:
            print("LR")
            retNode.left = self.leftRotate(retNode.left)
            return self.rightRotate(retNode)

        if balanceFactor < -1 and self.getBalanceFactor(retNode.right) > 0:
            print("RL")
            retNode.right = self.rightRotate(retNode.right)
            return self.leftRotate(retNode)

        return retNode




if __name__ == "__main__":
    avlTree = AVLTree()
    avlTree.add(1)
    avlTree.add(10)
    avlTree.add(4)
    avlTree.add(5)
    avlTree.add(11)

    avlTree.remove(10)

    print(f"avlTree.isBST = {avlTree.isBST()}")
    print(f"avlTree.isBalanced = {avlTree.isBalanced()}")
