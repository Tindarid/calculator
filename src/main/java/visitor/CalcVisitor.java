package visitor;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private final Stack<Integer> tokens = new Stack<>();

    public int result() {
        if (tokens.size() != 1) {
            throw new IllegalStateException("Must be one number after executing Postfix notation");
        }
        return tokens.pop();
    }

    public void visit(NumberToken token) {
        tokens.add(token.get());
    }

    public void visit(OperationToken token) {
        if (tokens.size() < 2) {
            throw new IllegalStateException("Not enough numbers for binary operation");
        }

        tokens.push(token.execute(tokens.pop(), tokens.pop()));
    }

    public void visit(BracketToken token) {
        throw new IllegalStateException("Cannot have BracketToken after ParserVisitor");
    }
}
