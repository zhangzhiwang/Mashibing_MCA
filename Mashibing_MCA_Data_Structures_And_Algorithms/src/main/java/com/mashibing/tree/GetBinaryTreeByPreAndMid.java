package com.mashibing.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树，两种遍历的结果会以数组的形式给出来，数组的元素有可能就是树的节点，也有可能是每个节点所代表的的值，
 * 如果是第二种情况的话，构造二叉树的时候要把每一个值包装成节点。
 * 在com.mashibing.preInEclipse.junior.tree.GetBinaryTreeByPreAndMid中练习的是第一种情况，这里练习第二种情况。
 * 课程：新手班课时47
 * 思路：见com.mashibing.preInEclipse.junior.tree.GetBinaryTreeByPreAndMid注释
 */
public class GetBinaryTreeByPreAndMid {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] mid = {4, 2, 5, 1, 6, 3, 7};
        TreeNode<Integer> head = getBinaryTreeByPreAndMid2(pre, mid);

        System.out.println(head.value);
        System.out.println(head.left.value);
        System.out.println(head.right.value);

        System.out.println("-------");
        System.out.println(head.left.left.value);
        System.out.println(head.left.right.value);

        System.out.println("-------");
        System.out.println(head.left.left.left);
        System.out.println(head.left.left.right);
        System.out.println(head.left.right.left);
        System.out.println(head.left.right.right);

        System.out.println("===========");
        System.out.println(head.right.left.value);
        System.out.println(head.right.right.value);

        System.out.println("-------");
        System.out.println(head.right.left.left);
        System.out.println(head.right.left.right);
        System.out.println(head.right.right.left);
        System.out.println(head.right.right.right);
    }

    public static TreeNode<Integer> getBinaryTreeByPreAndMid(int[] pre, int[] mid) {
        if (!(pre != null && mid != null)) {
            return null;
        }

        if (pre.length != mid.length) {
            return null;
        }

        return f(pre, 0, pre.length - 1, mid, 0, mid.length - 1);
    }

    /**
     * getBinaryTreeByPreAndMid()方法的优化版
     *
     * @param pre
     * @param mid
     * @return
     */
    public static TreeNode<Integer> getBinaryTreeByPreAndMid2(int[] pre, int[] mid) {
        if (!(pre != null && mid != null)) {
            return null;
        }

        if (pre.length != mid.length) {
            return null;
        }

        // 将中序遍历的数组mid转换为map，key为数组元素，value为元素所对应的索引
        Map<Integer, Integer> midArrToMap = new HashMap<>();
        for (int i = 0; i < mid.length; i++) {
            midArrToMap.put(mid[i], i);
        }

        return f2(pre, 0, pre.length - 1, mid, 0, mid.length - 1, midArrToMap);
    }


    /**
     * @param pre           先序遍历结果数组
     * @param preStartIndex 先序数组的起始位置
     * @param preEndIndex   先序数组的结束位置
     * @param mid           中序遍历结果数组
     * @param midStartIndex 中序数组的起始位置
     * @param midEndIndex   中序数组的结束位置
     * @return
     */
    public static TreeNode<Integer> f(int[] pre, int preStartIndex, int preEndIndex, int[] mid, int midStartIndex, int midEndIndex) {
        if (preStartIndex > preEndIndex) {
            return null;
        }

        // 1、先序遍历的第一个节点一定是整棵树的头部，所以直接返回即可
        TreeNode<Integer> head = new TreeNode<>(pre[preStartIndex]);

        // 2、找到头结点在中序遍历结果中的位置
        // 方式一：
        int findIndex = 0;
//        for(; mid[findIndex] != pre[0]; findIndex++);// 如果对for循环的本质了解的话，循环退出来之后findIndex就是中序遍历结果中头结点所在的索引

        // 方式二：
        // 如果上面你的for循环不太好理解的话可以改成while循环
        while (mid[findIndex] != pre[preStartIndex]) {
            findIndex++;
        }

        // 方式三：
        /*
         上面的方式一和方式二都是循环，时间复杂度是O(N)，更何况本方法在下面会递归调用，每一次调用运行到这一步都是O(N)，数据量大的时候惠更明显，
         所以可以考虑用一个哈希表将中序遍历结果给记下来，然后将哈希表传进来，这一步直接get就可以，时间复杂度为O(1)，见优化方法f2()。
         */

        /*
         3、确定数的根节点的左子树和右子树的范围，以及左子树和右子树分别包含的节点个数
         在中序遍历结果中确定了整棵树的头结点pre[0]的位置之后，就可以确定头结点的左子树的范围为：mid[midStartIndex] - mid[findIndex - 1]。
         注意mid数组左子树的索引范围[midStartIndex, findIndex - 1]是根节点的左子树，确定了范围就知道根节点整体的左子树一共有过少个节点：一共有（findIndex - 1 - midStartIndex + 1）个，化简后为(findIndex - midStartIndex)个。
         同理中序遍历结果中右子树的范围：mid[findIndex + 1, midEndIndex]，包含的元素个数为：(midEndIndex - (findIndex + 1) + 1)个，化简后为(midEndIndex - findIndex + 2)个。
         */

        /*
         4、知道了根节点整体的左子树和整体的右子树的个数之后，就可以在先序遍历的数组中找到整体左子树和右子树的位置了。
         先序数组中左子树的索引范围：[1, findIndex - midStartIndex - 1]，右子树的索引范围：[findIndex - midStartIndex - 1 + 1,preEndIndex]。
         */

        // 5、递归找到每一棵左子树和右子树的头结点，并挂在head节点上，整个过程结束
        head.left = f(pre, preStartIndex + 1, preStartIndex + (findIndex - midStartIndex), mid, midStartIndex, findIndex - 1);
        head.right = f(pre, preStartIndex + (findIndex - midStartIndex) + 1, preEndIndex, mid, findIndex + 1, midEndIndex);

        return head;
    }

    private static TreeNode<Integer> f2(int[] pre, int preStartIndex, int preEndIndex, int[] mid, int midStartIndex, int midEndIndex, Map<Integer, Integer> midMap) {
        if (preStartIndex > preEndIndex) {
            return null;
        }

        // 1、先序遍历的第一个节点一定是整棵树的头部，所以直接返回即可
        TreeNode<Integer> head = new TreeNode<>(pre[preStartIndex]);

        // 2、找到头结点在中序遍历结果中的位置

        // 方式三：
        /*
         上面的方式一和方式二都是循环，时间复杂度是O(N)，更何况本方法在下面会递归调用，每一次调用运行到这一步都是O(N)，数据量大的时候惠更明显，
         所以可以考虑用一个哈希表将中序遍历结果给记下来，然后将哈希表传进来，这一步直接get就可以，时间复杂度为O(1)。
         */
        int findIndex = midMap.get(pre[preStartIndex]);

        /*
         3、确定数的根节点的左子树和右子树的范围，以及左子树和右子树分别包含的节点个数
         在中序遍历结果中确定了整棵树的头结点pre[0]的位置之后，就可以确定头结点的左子树的范围为：mid[midStartIndex] - mid[findIndex - 1]。
         注意mid数组左子树的索引范围[midStartIndex, findIndex - 1]是根节点的左子树，确定了范围就知道根节点整体的左子树一共有过少个节点：一共有（findIndex - 1 - midStartIndex + 1）个，化简后为(findIndex - midStartIndex)个。
         同理中序遍历结果中右子树的范围：mid[findIndex + 1, midEndIndex]，包含的元素个数为：(midEndIndex - (findIndex + 1) + 1)个，化简后为(midEndIndex - findIndex + 2)个。
         */

        /*
         4、知道了根节点整体的左子树和整体的右子树的个数之后，就可以在先序遍历的数组中找到整体左子树和右子树的位置了。
         先序数组中左子树的索引范围：[1, findIndex - midStartIndex - 1]，右子树的索引范围：[findIndex - midStartIndex - 1 + 1,preEndIndex]。
         */

        // 5、递归找到每一棵左子树和右子树的头结点，并挂在head节点上，整个过程结束
        head.left = f(pre, preStartIndex + 1, preStartIndex + (findIndex - midStartIndex), mid, midStartIndex, findIndex - 1);
        head.right = f(pre, preStartIndex + (findIndex - midStartIndex) + 1, preEndIndex, mid, findIndex + 1, midEndIndex);

        return head;
    }
}
