public interface ILexeme {

    String getIdentifier();

    void setIdentifier(String identifier);

    TokenTypes getTokenType();

    void setTokenType(TokenTypes tokenType);
}
