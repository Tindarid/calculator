package visitor;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;

public class PrintVisitor implements TokenVisitor {
    private final StringBuilder buffer = new StringBuilder();

    public String result() {
        return buffer.toString();
    }

    public void visit(NumberToken token) {
        buffer.append(token.get());
        buffer.append(" ");
    }

    public void visit(OperationToken token) {
        buffer.append(token.get());
        buffer.append(" ");
    }

    public void visit(BracketToken token) {
        buffer.append(token.get());
        buffer.append(" ");
    }
}
