import org.junit.*;

public class LexicalAnalyzerTests {


    private LexicalAnalyzer systemUnderTest = new LexicalAnalyzer(
            new CommentHandler(),
            new LexemeHandler(new LexemeValidator(), new TokenValidator()));


    @Test
    public void TestSourceCodeConverter_Invoked_CreatesLinesOfSourceCodeArrayList() {


        String input = "/**/          /*/* */   */\n" +
                "/*/*/****This**********/*/    */\n" +
                "/**************/\n" +
                "/*************************\n" +
                "i = 333;        ******************/       */\n" +
                "\n" +
                "iiii = 3@33;\n" +
                "\n" +
                "int g 4 cd (int u, int v)      {\n" +
                "if(v == >= 0) return/*a comment*/ u;\n" +
                "else ret_urn gcd(vxxxxxxvvvvv, u-u/v*v);\n" +
                "       /* u-u/v*v == u mod v*/\n" +
                "!   \n" +
                "}\n" +
                "\n" +
                "return void while       void main()";

        systemUnderTest.scan(input);

        //System.out.print(input);


    }


    /*
    @Test
    public void print(){
        String input = "+-*</=>!;,()[]{}";

        for (int i = 0; i < input.length(); i++) {

            char tmp = input.charAt(i);

            System.out.print("'"+ tmp +"', ");
        }
    }*/

}
