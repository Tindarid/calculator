package token;

import visitor.TokenVisitor;

public class OperationToken implements Token {
    private enum Operation {
        ADD(1),
        SUB(1),
        MUL(2),
        DIV(2)
        ;

        private final int priority;

        Operation(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    private final Operation operation;

    public boolean hasNotLessPriorityThan(OperationToken rhs) {
        return this.operation.getPriority() >= rhs.operation.getPriority();
    }

    OperationToken(char operation) {
        switch (operation) {
            case '+':
                this.operation = Operation.ADD;
                break;
            case '-':
            case '–': // if you will copy from docx
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
                return b - a;
            case MUL:
                return a * b;
            case DIV:
                return b / a;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

    public char get() {
        switch (operation) {
            case ADD:
                return '+';
            case SUB:
                return '-';
            case MUL:
                return '*';
            case DIV:
                return '/';
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

    public static String getSupportedOperations() {
        return "+-*/–";
    }
}
