package token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final List<Token> tokens;
    private State curState;

    public Tokenizer() {
        tokens = new ArrayList<>();
        curState = new WhitespaceState();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void tokenize(String src) {
        for (char symbol: (src.trim() + "\n").toCharArray()) {
            if (!curState.handle(symbol, this)) {
                nextState(symbol);
            }
        }
    }

    private void nextState(char symbol) {
        if (Character.isWhitespace(symbol)) {
            curState = new WhitespaceState();
        } else if (Character.isDigit(symbol)) {
            curState = new NumberState(symbol);
        } else if (BracketToken.getSupportedBrackets().contains(Character.toString(symbol))) {
            curState = new BracketState(symbol);
        } else if (OperationToken.getSupportedOperations().contains(Character.toString(symbol))){
            curState = new OperationState(symbol);
        } else {
            throw new IllegalArgumentException("Unknown symbol: " + symbol);
        }
    }

    private interface State {
        boolean handle(char symbol, Tokenizer tokenizer);
    }

    private static class WhitespaceState implements State {
        public boolean handle(char symbol, Tokenizer tokenizer) {
            return false;
        }
    }

    private static class BracketState implements State {
        private final char br;

        BracketState(char br) {
            this.br = br;
        }

        public boolean handle(char symbol, Tokenizer tokenizer) {
            tokenizer.tokens.add(new BracketToken(br));
            return false;
        }
    }
    private static class OperationState implements State {
        private final char op;

        OperationState(char op) {
            this.op = op;
        }

        public boolean handle(char symbol, Tokenizer tokenizer) {
            tokenizer.tokens.add(new OperationToken(op));
            return false;
        }
    }

    private static class NumberState implements State {
        private final StringBuilder number;

        NumberState(char symbol) {
            number = new StringBuilder();
            number.append(symbol);
        }

        public boolean handle(char symbol, Tokenizer tokenizer) {
            if (Character.isDigit(symbol)) {
                number.append(symbol);
                return true;
            } else {
                tokenizer.tokens.add(new NumberToken(Integer.parseInt(number.toString())));
                return false;
            }
        }
    }

}
