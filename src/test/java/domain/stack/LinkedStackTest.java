package domain.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void infixToPostfixText(){
        try {
            System.out.println(" infix: ((a-b)*(a+c)) to postfix: "+ util.Utility.infixToPostfixConverter("((a-b)*(a+c)) "));
        } catch (StackException e) {
            throw new RuntimeException(e);
        }

    }


    void test() {

        try {
            System.out.println(isBalanced("({[]})"));
            System.out.println(isBalanced("([])"));
            System.out.println(isBalanced("([)]"));
            System.out.println(isBalanced("((()))"));
            System.out.println(isBalanced("{[}"));
            System.out.println(isBalanced("]"));
            System.out.println(isBalanced(""));
        } catch (StackException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isBalanced(String expression) throws StackException{
        return util.Utility.isBalanced(new LinkedStack(),expression);
    }

}