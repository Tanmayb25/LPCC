
import java.util.Scanner;
import java.util.Stack;

public class Main{

    static int tempCount = 1;

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    private static String applyOp(char op, String a, String b) {
        String result = "t" + tempCount++;
        System.out.println(result + " = " + a + " " + op + " " + b);
        return result;
    }

    public static void infixToTAC(String exp) {

        Stack<Character> operators = new Stack<>();

        Stack<String> values = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == ' ')
                continue;

            if (Character.isLetterOrDigit(c)) {
                StringBuilder sbuf = new StringBuilder();
                while (i < exp.length() && (Character.isLetterOrDigit(exp.charAt(i)) || exp.charAt(i) == '_')) {
                    sbuf.append(exp.charAt(i++));
                }
                i--;
                values.push(sbuf.toString());
            }

            else if (c == '(')
                operators.push(c);

            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    String val2 = values.pop();
                    String val1 = values.pop();
                    char op = operators.pop();
                    values.push(applyOp(op, val1, val2));
                }
                operators.pop();  //remove '('
            }

            else {
                while (!operators.empty() && precedence(operators.peek()) >= precedence(c)) {
                    String val2 = values.pop();
                    String val1 = values.pop();
                    char op = operators.pop();
                    values.push(applyOp(op, val1, val2));
                }
                operators.push(c);
            }
        }

        while (!operators.empty()) {
            String val2 = values.pop();
            String val1 = values.pop();
            char op = operators.pop();
            values.push(applyOp(op, val1, val2));
        }

        System.out.println("Result = " + values.pop());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String exp = scanner.nextLine(); 
        System.out.println("Infix Expression: " + exp);
        System.out.println("Generated Three Address Code:");
        infixToTAC(exp);
        scanner.close();
    }
}
