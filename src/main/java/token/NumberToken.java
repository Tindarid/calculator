package token;

import visitor.TokenVisitor;

public class NumberToken implements Token {
    private final int number;

    NumberToken(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
