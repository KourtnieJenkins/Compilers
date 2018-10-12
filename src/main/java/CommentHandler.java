public class CommentHandler implements ICommentHandler {

    private ScannerModeType scannerMode;
    private int nestedComment = 0;

    public CommentHandler(){
        scannerMode = ScannerModeType.TokenMode;
    }

    @Override
    public String replaceComment(String lineOfInput, char replacementCharacter){

        char currentChar;
        char nextChar;
        String lineWithOutComment = "";

        for(int i = 0; i < lineOfInput.length()-1; i++){

            currentChar = lineOfInput.charAt(i);
            nextChar = lineOfInput.charAt(i+1);

            if(isStartOfBlockComment(currentChar, nextChar)){

                nestedComment++;
                i++;

                if(nestedComment > 0){
                    scannerMode = ScannerModeType.CommentMode;
                }

            }else if(isEndOfBlockComment(currentChar, nextChar)){

                if(nestedComment > 0){

                    nestedComment--;
                    i++;

                }

                if(nestedComment == 0 && scannerMode == ScannerModeType.CommentMode){

                    scannerMode = ScannerModeType.TokenMode;
                    lineWithOutComment += replacementCharacter;
                    continue;

                }

            }else if(isLineComment(currentChar, nextChar) == true && scannerMode != ScannerModeType.CommentMode){

                break;

            }


            if(scannerMode == ScannerModeType.TokenMode){

                lineWithOutComment += currentChar;

                if(i == lineOfInput.length() - 2){

                    lineWithOutComment += nextChar;

                }
            }
        }

        return lineWithOutComment;
    }

    @Override
    public boolean isStartOfBlockComment(char currentChar, char nextChar){

        if(currentChar == '/' && nextChar == '*'){

            return true;

        }

        return false;
    }

    @Override
    public boolean isEndOfBlockComment(char currentChar, char nextChar){

        if(currentChar == '*' && nextChar == '/'){

            return true;

        }

        return false;

    }

    @Override
    public boolean isLineComment(char currentChar, char nextChar){

        if(currentChar == '/' && nextChar == '/'){

            return true;

        }

        return false;

    }

}
