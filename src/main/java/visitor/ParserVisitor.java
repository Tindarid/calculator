package visitor;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private final List<Token> tokens;
    private final Stack<Token> ops;

    public ParserVisitor() {
        tokens = new ArrayList<>();
        ops = new Stack<>();
    }

    public List<Token> getTokens() {
        while (!ops.empty()) {
            Token op = ops.pop();
            if (op instanceof BracketToken) {
                throw new IllegalArgumentException("Not right bracket is found for left bracket");
            }
            if (op instanceof OperationToken) {
                throw new IllegalArgumentException("Not enough operands for binary operation");
            }
            tokens.add(op);
        }
        return tokens;
    }

    public void visit(NumberToken token) {
        tokens.add(token);
    }

    public void visit(OperationToken token) {
        while (!ops.empty()) {
            Token op = ops.peek();
            if (!(op instanceof OperationToken) || !((OperationToken) op).hasNotLessPriorityThan(token)) {
                break;
            }
            tokens.add(ops.pop());
        }
        ops.push(token);
    }

    public void visit(BracketToken token) {
        if (token.isOpen()) {
            ops.push(token);
            return;
        }

        while (true) {
            if (ops.empty()) {
                throw new IllegalArgumentException("No left bracket for right bracket is found");
            }

            Token op = ops.pop();
            if (op instanceof BracketToken && ((BracketToken) op).isOpen()) {
                break;
            }
            tokens.add(op);
        }
    }
}
