package token;

import visitor.TokenVisitor;

public class NumberToken implements Token {
    private final int number;

    NumberToken(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
