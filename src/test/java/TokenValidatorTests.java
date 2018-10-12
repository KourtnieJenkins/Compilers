import org.junit.Test;

public class TokenValidatorTests {

    private final TokenValidator tokenValidator = new TokenValidator();

    @Test
    public void merp(){

        String output = "\"+\", \"-\", \"*\", \"/\", \"<\", '=', '>', '!', ';', ',', '(', ')', '[', ']', '{', '}'";

        char tmp;

        for(int i = 0; i < output.length(); i++){

            tmp = output.charAt(i);

            if(tmp == '\''){
                tmp = '"';
            }

            System.out.print(tmp);
        }
    }

}
