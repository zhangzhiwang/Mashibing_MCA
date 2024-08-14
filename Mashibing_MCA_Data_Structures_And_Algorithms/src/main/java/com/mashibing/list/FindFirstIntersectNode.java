package com.mashibing.list;

/**
 * 题目：给定两个可能有环也可能无环的单链表，头节点head1和head2，请实现一个函数，如果两个链表相交就返回相交的第一个节点，如果不相交就返回null
 *
 * 思路：
 * 1、设置一个函数：返回成环链表的入环节点，如果链表没有环则返回null
 * （1）设置一个快指针和一个慢指针，首先先让快指针一次走两步，慢指针一次走一步，如果快指针达到null了说明链表没有环，返回null
 * （2）如果链表有环，那么快慢指针肯定会相遇，当快慢指针相遇时慢指针不动，快指针回到head。
 * 这里要说明的是：快走二慢走一算作一次循环，要么就不循环要么一旦循环快慢指针都要走，所以这里的"相遇"指的是慢走完之后追上了快（如果有环的话），因为慢走完之后本次循环结束，
 * 下一次循环开始前发现快慢相遇了就结束了，就没有下一轮循环了，可不是指快走了两步之后和慢重合叫相遇，因为慢此时还没有走呢，慢还得往前走一步本次循环才结束，
 * 所以，这里的相遇指的是慢追上了快。
 * （3）快慢指针重新走，这回快慢指针都是一次只走一步，当快慢指针再次相遇时，相遇的节点就是入环的节点
 * 2、通过上面的函数就可以判断出两个链表是否成环以及各自入环的节点。然后分三种情况：
 * （1）两个链表都没有成环
 *  1）分别从头到尾遍历两条链表，算出两个链表各自的长度l1和l2，如果两个链表的尾结点是同一个，说明两个链表相交了，如果不是同一个说明没有相交，返回null
 *  2）如果两个链表相交了，那么重新遍历两个链表，每步都各走一个节点，但是较长的链表要先走出|l1-l2|这么多步，然后两个链表再同时走，两个指针相遇时就是相交的节点
 * （2）一个链表有环一个链表无环
 * 由于是单链表，所以这种情况两个链表不可能相交，直接返回null
 * （3）两个链表都有环
 * 这种情况又可以分为三种情况：
 * 通过最开始的第一步已经找出了两个链表各自的入环节点loop1和loop2，那么判断一下loop1的内存地址是不是和loop2相等，如果相等就是下面的情况1，如果不相等就是下面的情况2和情况3：
 *  1）情况1：两个链表的相交位置在入环节点的前面
 *  这种情况就是两个链表相交的时候还没有成环呢，由于本题目是找出相交的节点而不是入环的节点，所以后面的环和找相交节点没有关系，就把链表截断，尾结点就是入环节点，
 *  然后问题就简化成了上面第2.(1)这种情况——两个链表都无环，找出相交节点
 *  2）情况2：两个链表不相交
 *  当loop1!=loop2时，如何判断是情况2还是下面的情况3？答案就是从loop1开始走，如果转一圈之后又回到了loop1，中途没有遇到loop2，就是情况2，如果遇到了就是情况3。
 *  如果是情况2，就说明两个链表不相交，返回null即可。
 *  3）情况3：两个链表相交的位置有两个，这两个相交节点是同一个环上的两个节点
 *  如果是情况3，那么返回loop1或者loop2都可以，都是相交节点。
 *  小结：
 *  整体来说：先看看两个链表是不是都有环，然后分三种情况：
 *  1、两个链表都无环
 *  2、一个有环一个无环
 *  3、两个都有环，又分三种情况：
 *  （1）不相交
 *  （2）相交节点在成环之前
 *  （3）相交节点在环内
 *
 *  注意：
 *  1、这道题只有以上列出来的情况才是真实存在的，任何没有列出来的自己脑补出来的情况都是不存在的，根本原因就有一个：两条链表都是单链表，每一个节点只有一个指针指向其它节点，
 *  即每个节点只有一个出度，不可能出现一个节点同时指向两个节点的情况，如果发现自己分析的情况是：从一个节点出来分叉了，同时指向两个节点，那一定是错的，这种情况一定不存在。
 *  2、通过看整个过程可以发现，本题不关注节点的值是什么，只关注节点本身的内存地址
 *
 *  课程：体系班课时82-83
 */
public class FindFirstIntersectNode {
    public static SingleNode<Integer> findFirstIntersectNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if(head1 == null || head2 == null) {
            return null;
        }

        // 分别找到两个链表的入环节点
        SingleNode<Integer> loopNode1 = findLoopNode(head1);
        SingleNode<Integer> loopNode2 = findLoopNode(head2);

        // 分三种情况
        if(loopNode1 == null && loopNode2 == null) {// 两个节点都没有环
            return noLoopInterNode(head1, head2);
        } else if (loopNode1 != null && loopNode2 != null) {// 两个节点都有环
            return loopInterNode(head1, head2, loopNode1, loopNode2);
        } else {// 两个链表一个有环一个没有环，绝对不会相交，直接返回null
            return null;
        }
    }

    /**
     * 在两个链表都有环的情况下找到相交节点，没有相交则返回null
     * 注意：调用本方法的前提是入参的两个链表都必须有环，有一个没环就不能调用本方法
     * @param head1
     * @param head2
     * @param loopNode1 链表head1的入环节点
     * @param loopNode2 链表head2的入环节点
     * @return
     */
    private static SingleNode<Integer> loopInterNode(SingleNode<Integer> head1, SingleNode<Integer> head2, SingleNode<Integer> loopNode1, SingleNode<Integer> loopNode2) {
        if(loopNode1 == loopNode2) {// 当loopNode1和loopNode2是同一个节点时，说明连个链表必相交且在入环前相交，逻辑就是noLoopInterNode方法的逻辑
            SingleNode<Integer> cur1 = head1;
            /*
             这里长度的初始值是0，下面while循环的判断条件可以是cur1 != null，而不需要是cur1.next != null，
             因为在noLoopInterNode方法里，需要在退出while后cur1正好停在链表的最后一个节点上，因为需要判断两个链表的尾结点是不是同一个，
             但是在这里不需要这么做，这是和noLoopInterNode方法唯一不一样的地方。
             链表"截断"后（注意：不能真截断，不能破坏原链表的结构），如果把loopNode1当做尾结点的话，以下计算的链表长度都会比真实的链表长度少1，因为loopNode1没有算进去，
             由于length1和length2都没有把loopNode1算进去，只是看二者的相对大小，所以最终结果不受影响。
             */
            int length1 = 0;
            while(cur1 != loopNode1) {
                cur1 = cur1.next;
                length1++;
            }
            SingleNode<Integer> cur2 = head2;
            int length2 = 0;
            while(cur2 != loopNode2) {
                cur2 = cur2.next;
                length2++;
            }

            SingleNode<Integer> longer = length1 > length2 ? head1 : head2;
            SingleNode<Integer> shorter = longer == head1 ? head2 : head1;
            int lengthTmp = Math.abs(length1 - length2);
            cur1 = longer;
            cur2 = shorter;
            while(lengthTmp != 0) {
                cur1 = cur1.next;
                lengthTmp--;
            }
            while(cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            SingleNode<Integer> cur3 = loopNode1.next;
            if(cur3 == loopNode2) {// 防止loopNode1自己和自己成环，这样的话下面的while就进不去，直接返回null不合适，万一loopNode1就是loopNode2呢？
                return loopNode1;
            }
            while(cur3 != loopNode1) {
                if(cur3 == loopNode2) {
                    return loopNode1;// 返回loopNode1或者loopNode2都可以
                }
                cur3 = cur3.next;
            }
            return null;
        }
    }

    /**
     * 在两个链表都没有成环的情况下找到相交节点，没有相交则返回null
     * 注意：调用本方法的前提是入参的两个链表都不能有环，有一个有环就不能调用本方法
     * @param head1
     * @param head2
     * @return
     */
    private static SingleNode<Integer> noLoopInterNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode<Integer> cur1 = head1;
        // 链表的长度，初始值要是1而不能是0，如果设置成0的话会少统计一个（但其实也无所谓，length1少统计一个，同理length2也会少统计一个，反正最后比的是两个长度的差值）
        int length1 = 1;
        /*
        下面的这个循环有两个目的：一个是计算链表的长度，一个是当退出循环时cur1正好停在最后一个节点上。
        虽然length1的初始长度为0，下面while循环的条件可以是cur1 != null，这样退出循环后确实能够算链表的长度，
        但是退出循环后cur指针已经移出去了，变成了null，没有停在最后一个节点上，没有满足上面的目的2
         */
        while(cur1.next != null) {// 循环的条件是当前节点的下一个节点不为空，不能是当前节点不为空
            cur1 = cur1.next;
            length1++;
        }
        // 退出循环后链表1的长度计算出来了，cur1指针也正好停在了最后一个节点上
        // 同理，算出链表2的长度，也让cur2指针停在链表2的最后一个节点上
        SingleNode<Integer> cur2 = head2;
        /*
         当然在计算链表2的长度时也可以复用上面的length1变量，链表2每走一步length1--，后面通过判断length1是大于0还是小于0来判断哪个链表更长（length1的初始值要设置成0），
         这里为了好理解就多设置一个length2变量。
         这里要注意的是：如果采取用length1和length2分别计算两个链表的长度，那么初始值去0和取1都一样，只要统计长度的标准一致就行；但如果只用一个长度变量length1，
         一个统计用length1++，一个用length1--，那么length1的初始长度必须为0，为1的话后面取绝对值时会出错。
         */
        int length2 = 1;
        while(cur2.next != null) {
            cur2 = cur2.next;
            length2++;
        }

        if(cur1 != cur2) {// 说明两个链表没有相交
            return null;
        }

        // 能够运行到这里说明两个链表相交了
        // 确定谁是更长的链表，谁是更短的链表
        SingleNode<Integer> longer = length1 > length2 ? head1 : head2;// 如果length1比length2大，那么head1就是较长的链表
        SingleNode<Integer> shorter = longer == head1 ? head2 : head1;// 如果较长的链表是head1，那么head2就是较短的链表
        // 长链表先走|length1 - length2|这么多步，然后两个链表再一起走
        int lengthTmp = Math.abs(length1 - length2);// 长度差的绝对值
        // 这里为了复用cur1和cur2变量，那么让cur1指代较长链表的当前节点，cur2指代较短链表的当前节点
        cur1 = longer;
        cur2 = shorter;
        // 先让长链表走lengthTmp这么多步
        while(lengthTmp != 0) {
            cur1 = cur1.next;
            lengthTmp--;
        }
        // 退出上面的循环之后cur1和cur2就在同一个起跑线上了，然后一起走
        while(cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        // 退出上面的循环说明cur1和cur2相遇了，相遇的这个节点就是相交的节点，返回cur1和cur2都行
        return cur1;
    }

    private static SingleNode<Integer> findLoopNode(SingleNode<Integer> head) {
        /*
         只要某一个节点的下一个节点是null，那么这个链表就不可能成环，所以参数校验判断几个节点都可以，需要判断几个就判断几个，
         由于下面快慢指针的起始位置需要从头结点各自先走一步，所以这里需要判断三个节点
         */
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        /*
         快慢指针
         按理说快慢指针的起始位置都应该是head，然后慢指针一次跳一步，快指针一次跳两步，但是对于本题目来说下面的while循环的判断条件是快慢指针没有相遇的时候才进行循环，
         如果快慢指针的起始位置都是head的话，那么一上来就相遇了，while循环压根儿就进不去，所以起始位置先让快慢指针往下跳一步，先错开，进入循环后看会不会相遇
         */
        SingleNode<Integer> slow = head.next;
        SingleNode<Integer> fast = head.next.next;
        while(slow != fast) {
            if(fast.next == null || fast.next.next == null) {// 如果有环的话，链表任何一个节点的next指针都不会为null，如果快指针能探测到null说明链表没有环
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从上面这个while循环出来如果有环的话，那么快慢指针就相遇了，如果没有环那么快指针已经探测到尾结点了，已经return了

        // 快慢指针相遇后，慢指针不动，快指针回到head，之后快慢指针每次都跳一步，当再次相遇时相遇的那个节点就是入环的节点
        fast = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // 跳出上面的循环之后说明fast和slow遇上了，所以返回哪个都可以
        return fast;
    }

    public static void main(String[] args) {
        SingleNode<Integer> head1 = new SingleNode<>(1);// 本题目不关心节点的值，所以节点设置成什么值无所谓
        SingleNode<Integer> head1_n2 = new SingleNode<>(2);
        SingleNode<Integer> head1_n3 = new SingleNode<>(3);
        SingleNode<Integer> head1_n4 = new SingleNode<>(4);
        SingleNode<Integer> head1_n5 = new SingleNode<>(5);
        head1.next = head1_n2;
        head1_n2.next = head1_n3;
        head1_n3.next = head1_n4;
        head1_n4.next = head1_n5;
        System.out.println("链表1：");
        SingleNode<Integer> cur = head1;
        while(cur != null) {
            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println();

        SingleNode<Integer> head2 = new SingleNode<>(10);
        SingleNode<Integer> head2_n2 = new SingleNode<>(20);
        SingleNode<Integer> head2_n3 = new SingleNode<>(30);
        SingleNode<Integer> head2_n4 = new SingleNode<>(40);
        SingleNode<Integer> head2_n5 = new SingleNode<>(50);
        head2.next = head2_n2;
        head2_n2.next = head2_n3;
        head2_n3.next = head2_n4;
        head2_n4.next = head2_n5;
        System.out.println("链表2：");
        cur = head2;
        while(cur != null) {
            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println();
        System.out.println("----------");

        SingleNode<Integer> firstIntersectNode = findFirstIntersectNode(head1, head2);
        System.out.println("firstIntersectNode = " + firstIntersectNode);
        System.out.println("----------");

        SingleNode<Integer> head3 = new SingleNode<>(100);
        SingleNode<Integer> head3_n2 = new SingleNode<>(200);
        SingleNode<Integer> head3_n3 = new SingleNode<>(300);
        SingleNode<Integer> head3_n4 = new SingleNode<>(400);
        SingleNode<Integer> head3_n5 = new SingleNode<>(500);
        head3.next = head3_n2;
        head3_n2.next = head3_n3;
        head3_n3.next = head3_n4;
        head3_n4.next = head3_n5;
        head3_n5.next = head3_n5;
//        System.out.println("链表3：");
//        cur = head3;

//        while(cur != null) {
//            System.out.print(cur.data + " -> ");
//            cur = cur.next;
//        }
//        System.out.println();
//        System.out.println("----------");

        firstIntersectNode = findFirstIntersectNode(head3, head2);
        System.out.println("firstIntersectNode = " + firstIntersectNode);
        System.out.println("----------");

        SingleNode<Integer> head4 = new SingleNode<>(1000);
        SingleNode<Integer> head4_n2 = new SingleNode<>(2000);
        SingleNode<Integer> head4_n3 = new SingleNode<>(3000);
        SingleNode<Integer> head4_n4 = new SingleNode<>(4000);
        SingleNode<Integer> head4_n5 = new SingleNode<>(5000);
        head4.next = head4_n2;
//        head4_n2.next = head3_n2;
        head4_n2.next = head3_n3;
//        head4_n2.next = head4_n3;
//        head4_n3.next = head4_n4;
//        head4_n4.next = head4_n5;
//        head4_n5.next = head4_n5;

        firstIntersectNode = findFirstIntersectNode(head3, head4);
        System.out.println("firstIntersectNode = " + firstIntersectNode.value);
        System.out.println("----------");
    }
}
