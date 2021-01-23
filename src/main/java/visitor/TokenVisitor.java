package visitor;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(OperationToken token);
    void visit(BracketToken token);
}
