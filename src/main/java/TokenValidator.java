public class TokenValidator implements ITokenValidator {

    private final String[] keywords = {"else", "if", "int", "return", "void", "while", "float"};
    //private String[] symbols = {"+", "-", "*", "/", "<", "=", ">", "!", ";", ",", "(", ")", "[", "]", "{", "}"};

    @Override
    public boolean isKeyword(ILexeme lexeme){

        for (String keyword: keywords) {
            if(lexeme.getIdentifier().equals(keyword)){
                return true;
            }
        }

        return false;

    }


}
