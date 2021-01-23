package token;

import visitor.TokenVisitor;

public class OperationToken implements Token {
    private enum Operation {
        ADD,
        SUB,
        MUL,
        DIV
    }

    private final Operation operation;

    OperationToken(char operation) {
        switch (operation) {
            case '+':
                this.operation = Operation.ADD;
                break;
            case '-':
                this.operation = Operation.SUB;
                break;
            case '*':
                this.operation = Operation.MUL;
                break;
            case '/':
                this.operation = Operation.DIV;
                break;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public int execute(int a, int b) {
        switch (operation) {
            case ADD:
                return a + b;
            case SUB:
                return a - b;
            case MUL:
                return a * b;
            case DIV:
                return a / b;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

    public static String getSupportedOperations() {
        return "+-*/";
    }
}
