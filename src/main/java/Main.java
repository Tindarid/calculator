import token.Token;
import token.Tokenizer;
import visitor.ParserVisitor;
import visitor.CalcVisitor;
import visitor.PrintVisitor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String src = new Scanner(System.in).nextLine();

        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(src);
        List<Token> tokens = tokenizer.getTokens();

        PrintVisitor printer_inf = new PrintVisitor();
        for (Token t : tokens) {
            t.accept(printer_inf);
        }
        System.out.println("INFIX: " + printer_inf.result());

        ParserVisitor parser = new ParserVisitor();
        for (Token t : tokens) {
            t.accept(parser);
        }
        tokens = parser.getTokens();

        PrintVisitor printer_pol = new PrintVisitor();
        for (Token t : tokens) {
            t.accept(printer_pol);
        }
        System.out.println("POSTFIX: " + printer_pol.result());

        CalcVisitor calculator = new CalcVisitor();
        for (Token t : tokens) {
            t.accept(calculator);
        }
        System.out.println("POSTFIX: " + calculator.result());
    }
}
