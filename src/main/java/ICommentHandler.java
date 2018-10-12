public interface ICommentHandler {
    String replaceComment(String lineOfInput, char replacementCharacter);

    boolean isStartOfBlockComment(char currentChar, char nextChar);

    boolean isEndOfBlockComment(char currentChar, char nextChar);

    boolean isLineComment(char currentChar, char nextChar);
}
