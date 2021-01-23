package token;

import visitor.TokenVisitor;

public class BracketToken implements Token {
    enum Bracket {
        LBR,
        RBR
    }

    private final Bracket bracket;

    BracketToken(char bracket) {
        switch (bracket) {
            case '(':
                this.bracket = Bracket.LBR;
                break;
            case ')':
                this.bracket = Bracket.RBR;
                break;
            default:
                throw new IllegalArgumentException("Unknown bracket: " + bracket);
        }
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public static String getSupportedBrackets() {
        return "()";
    }

    public boolean isOpen() {
        return this.bracket == Bracket.LBR;
    }
}
