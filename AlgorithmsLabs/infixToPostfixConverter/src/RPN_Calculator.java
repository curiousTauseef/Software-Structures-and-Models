/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class RPN_Calculator {

    public int evaluatepostfix(String postfix) {
        SimpleStack<Integer> operandStack = new SimpleStack();

        for (int index = 0; index < postfix.length(); index++) {
            // Convert char to string
            String aChar = "" + postfix.charAt(index);
            if (aChar.equals("+")) {
                operandStack.push(operandStack.pop() + operandStack.pop());
            } else if (aChar.equals("-")) {
                operandStack.push(- operandStack.pop() + operandStack.pop()); 
// extra care for the operation order, top one in the stack subtracts the second one in the stack
            } else if (aChar.equals("*")) {
                operandStack.push(operandStack.pop() * operandStack.pop());
            } else if (aChar.equals("/")) {
                operandStack.push(operandStack.pop() / operandStack.pop());
            } else if (aChar.equals("=")) {
                int result = operandStack.pop();
                System.out.println("top value is " + result);
                operandStack.push(result);
            } else if (aChar.equals("\n")) {
            } else {
                operandStack.push(Integer.parseInt(aChar));
            }
        }
        return operandStack.pop();
    }
}
