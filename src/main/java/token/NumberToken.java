package token;

import visitor.TokenVisitor;

public class NumberToken implements Token {
    private int number;

    public int getNumber() {
        return number;
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
