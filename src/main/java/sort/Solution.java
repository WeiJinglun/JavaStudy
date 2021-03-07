package sort;

/**
 * @author WeiJinglun
 * @date 2021.03.05
 */
public class Solution {

    public Node function(Node root, int num){
        if (root == null) {
            return root;
        }
        // 小于头部
        Node head1 = null;
        // 小宇尾部
        Node end1 = null;

        // 大于头部
        Node head2 = null;
        // 大于尾部;
        Node end2 = null;

        Node cur = root;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            if (cur.val < num) {
                if (head1 == null) {
                    head1 = cur;
                    end1 = cur;
                } else {
                    end1.next = cur;
                    end1 = cur;
                }
            } else {
                if (head2 == null) {
                    head2 = cur;
                    end2 = cur;
                } else {
                    end2.next = cur;
                    end2 = cur;
                }
            }

            cur = next;
        }

        // 链表合并
        if (head1 == null) {
            return head2;
        } else {
            if (head2 != null) {
                end1.next = head2;
            }
            return head1;
        }
    }





    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        // 1 4 3 2 5 2
        Node root = new Node(1);
        Node node4 = new Node(4);
        Node node3 = new Node(3);
        Node node2_1 = new Node(2);
        Node node5 = new Node(5);
        Node node2_2 = new Node(2);

        root.next = node4;
        node4.next = node3;
        node3.next = node2_1;
        node2_1.next = node5;
        node5.next = node2_2;

        Node p = new Solution().function(root, 3);
        while (p != null) {
            System.out.print(p.val+" -> ");
            p = p.next;
        }

    }
}
