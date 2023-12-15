package com.mashibing.tree;

/**
 * 按照中序遍历的顺序，找到一棵二叉树中任意节点的后续节点
 * 思路：
 * 解释：
 * 给定一棵树head，肯定可以得到它中序遍历的结果，比如是[a,b,c,d,e,...]，任意指定一个节点x，它的后续节点就是中序遍历结果中x的下一个节点。
 * 比如x是上面的c，那么它的后续节点就是d。
 * 传统思路：先中序遍历一遍整棵二叉树，得到中序遍历结果，然后在遍历中序遍历结果找到x，返回x的下一个节点。这么做可以达到目的，但是时间复杂度是O(N)。
 * 最优解思路：
 * 不需要进行中序遍历，在一棵树中任意指定一个节点x，在这颗树中直接从x触发找到它的后继节点。
 * 1、首先在数据结构上要求二叉树的节点都有三个指针，其中第三个指针指向它的父节点。
 * 2、如果x有右子树，那么x的后继节点就是它右子树的最左节点
 * 3、如果x没有右子树，就向上找它的父节点，一直向上找它的父节点，直到找到这样一个父节点Y，这个Y是更高级一父节点Z的左子树，那么Z就是X的后继节点；
 * 如果到达了根节点还买有找到这样的Z节点，那么说明x时整棵树的最后（最右）一个节点，在中序遍历结果中它是最后一个节点，它没有后续节点，返回null。
 * <p>
 * 课程：体系班课时97
 */
public class SuccessorNode {
    static class SpecialTreeNode<T> {
        public T data;
        public SpecialTreeNode<T> left;
        public SpecialTreeNode<T> right;
        public SpecialTreeNode<T> father;// 指向它的父节点

        public SpecialTreeNode(T data) {
            this.data = data;
        }
    }

    /**
     * @param head 头结点
     * @param x    要找后继节点的节点
     * @return x的后继节点
     */
    public static SpecialTreeNode<String> findSuccessorNode(SpecialTreeNode<String> head, SpecialTreeNode<String> x) {
        if (head == null || x == null) {
            return null;
        }

        if (x.right != null) {// 如果x有右子树，找右子树的最左节点
            SpecialTreeNode<String> tmp = x.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            // 退出了上面的while循环，tmp就是x右子树的最左节点，也就是x的后继节点，直接返回
            return tmp;
        } else {// x没有右子树，x就顺着它的父节点一致向上爬
            SpecialTreeNode<String> parent = x.father;
            while (parent != null && parent.left != x) {
                x = parent;
                parent = parent.father;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        SpecialTreeNode<String> z = new SpecialTreeNode<>("z");
        SpecialTreeNode<String> a = new SpecialTreeNode<>("a");
        SpecialTreeNode<String> b = new SpecialTreeNode<>("b");
        SpecialTreeNode<String> c = new SpecialTreeNode<>("c");
        SpecialTreeNode<String> d = new SpecialTreeNode<>("d");
        SpecialTreeNode<String> e = new SpecialTreeNode<>("e");
        SpecialTreeNode<String> f = new SpecialTreeNode<>("f");
        SpecialTreeNode<String> g = new SpecialTreeNode<>("g");
        z.left = a;
        a.father = z;
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        b.father = a;
        c.left = f;
        c.right = g;
        c.father = a;
        d.father = b;
        e.father = b;
        f.father = c;
        g.father = c;

        SpecialTreeNode<String> successorNode = findSuccessorNode(a, g);
        System.out.println("successorNode = " + (successorNode == null ? null : successorNode.data));
    }
}
