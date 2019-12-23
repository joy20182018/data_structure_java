

class TrieNode:
    def __init__(self, isWord=None):
        if isWord == None:
            self.isWord = False
        else:
            self.isWord = isWord

        self.Node = TrieNode
        self.next = {}   # key: 字符， value：下一个节点


class Trie:
    def __init__(self):
        self.__root = self.__getNode()

    def __getNode(self):
        return TrieNode()


    # 非递归实现trie添加元素操作
    def add(self, word):
        cur = self.__root
        for i in range(len(word)):
            c = word[i]
            if cur.next.get(c) == None:
                cur.next[c] = self.__getNode()
            cur = cur.next.get(c)

        if cur.isWord == False:
            cur.isWord = True

    # 使用循环进行trie添加元素操作
    def add_recursion(self, word):
        cur = self.__root
        self.__addR(cur, word, 1)

    def __addR(self, TrieNode, word, index):
        c = word[index - 1]
        if index == len(word):
            TrieNode.isWord = True
            return
        if TrieNode.next.get(c) == None:
            TrieNode.next[c] = self.__getNode()

        self.__addR(TrieNode.next.get(c), word, index + 1)

    # 在trie中查询单词
    def contains(self, word):
        cur = self.__root

        for i in range(len(word)):
            c = word[i]
            if cur.next.get(c) == None:
                return False
            cur = cur.next.get(c)

        return cur.isWord


    # 是否有以word为前缀的单词
    def isPrefix(self, word):
        cur = self.__root

        for i in range(len(word)):
            c = word[i]

            if cur.next.get(c) == None:
                return False
            cur = cur.next.get(c)

        return True

    # 打印整个trie树，像git log中打印出的表相同
    def PrintTrie(self):
        pass

if __name__ == "__main__":
    Trie = Trie()
    Trie.add("worgggggggggggggggggd")
    Trie.add("Trie")
    Trie.add("Tree")
    Trie.add("Train")
    Trie.add("fish")
    print(Trie.contains("Trie"))
