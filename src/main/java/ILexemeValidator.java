public interface ILexemeValidator {
    boolean isValidSymbolChar(char charToEvaluate, String currentLexemeContents);

    boolean isValidKeywordOrIDChar(char charToEvaluate);

    boolean isValidDigitChar(char charToEvaluate, String currentLexemeContents);
}
