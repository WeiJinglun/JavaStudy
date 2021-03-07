package leetcode.recursive;

import java.util.Stack;

/**
 * 使用递归,逆序栈
 *
 * @author WeiJinglun
 * @date 2021.01.28
 */
public class Solution_ReverseStackUsingRecursive {

    public void reverse(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }

        // 获取栈底元素
        int stackEnd = process(stack);
        reverse(stack);
        stack.push(stackEnd);
    }

    /**
     * 返回栈底元素
     */
    private int process(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.empty()) {
            return pop;
        } else {
            int process = process(stack);
            stack.push(pop);
            return process;
        }
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        new Solution_ReverseStackUsingRecursive().reverse(stack);
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
