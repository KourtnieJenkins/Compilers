public class Lexeme implements ILexeme {

    private String identifier;
    private TokenTypes tokenType;

    public Lexeme(){
        identifier = "";
        tokenType = null;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public TokenTypes getTokenType(){
        return tokenType;
    }

    @Override
    public void setTokenType(TokenTypes tokenType) {
        this.tokenType = tokenType;
    }
}
