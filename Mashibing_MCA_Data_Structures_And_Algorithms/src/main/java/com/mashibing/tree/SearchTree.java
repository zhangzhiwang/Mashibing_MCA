package com.mashibing.tree;

/**
 * 判断一棵树是不是搜索树
 * 课程：新手班课时49、50
 * 思路：见com.mashibing.preInEclipse.junior.tree.BinarySearchTree注释
 */
public class SearchTree {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(5);
        n1.left = new TreeNode<>(3);
        n1.right = new TreeNode<>(7);

        n1.left.left = new TreeNode<>(1);
        n1.left.right = new TreeNode<>(4);
        n1.left.left.right = new TreeNode<>(2);

        n1.right.left = new TreeNode<>(6);
        n1.right.right = new TreeNode<>(9);
        n1.right.right.left = new TreeNode<>(8);

        SearchTreeInfo searchTreeInfo = isSearchTree(n1);
        System.out.println(searchTreeInfo.isSearchTree);
    }

    static class SearchTreeInfo {
        public boolean isSearchTree;
        public int max;
        public int min;

        public SearchTreeInfo(boolean isSearchTree, int max, int min) {
            this.isSearchTree = isSearchTree;
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isSearchTree=" + isSearchTree +
                    ", max=" + max +
                    ", min=" + min +
                    '}';
        }
    }

    public static SearchTreeInfo isSearchTree(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        SearchTreeInfo leftInfo = isSearchTree(head.left);// true 1 1
        SearchTreeInfo rightInfo = isSearchTree(head.right);// true 3 3

        boolean isLeftValid = leftInfo == null ? true : leftInfo.isSearchTree && leftInfo.max < head.data;
        if(!isLeftValid) {
            return new SearchTreeInfo(false, 0, 0);
        }
        boolean isRightValid = rightInfo == null ? true : rightInfo.isSearchTree && rightInfo.min > head.data;
        if(!isRightValid) {
            return new SearchTreeInfo(false, 0, 0);
        }

        return new SearchTreeInfo(true,
                rightInfo == null ? head.data : rightInfo.max,
                leftInfo == null ? head.data : leftInfo.min);
    }
}
