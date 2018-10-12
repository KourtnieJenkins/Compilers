public class LexemeValidator implements ILexemeValidator {

    private final char[] symbols = {'+', '-', '*', '/', '<', '=', '>', '!', ';', ',', '(', ')', '[', ']', '{', '}'};


    @Override
    public boolean isValidSymbolChar(char charToEvaluate, String currentLexemeContents){

        int numberOfLexemeChars = currentLexemeContents.length();
        char endOfCurrentLexeme;

        if(charToEvaluate == '=' && numberOfLexemeChars > 0){

            endOfCurrentLexeme = currentLexemeContents.charAt(0);

            switch (endOfCurrentLexeme){
                case '<' :
                case '>' :
                case '=' :
                case '!' :
                    return true;
                default:
                    break;
            }

        }else {

            for(int i = 0; i < symbols.length; i++){

                if(charToEvaluate == symbols[i]){
                    return true;
                }

            }

        }

        return false;

    }

    @Override
    public boolean isValidKeywordOrIDChar(char charToEvaluate){

        if(Character.isLowerCase(charToEvaluate)){
            return true;
        }

        return false;

    }

    @Override
    public boolean isValidDigitChar(char charToEvaluate, String currentLexemeContents){

        int numberOfLexemeChars = currentLexemeContents.length();
        char endOfCurrentLexeme;

        if(Character.isDigit(charToEvaluate)){
            return true;
        }else if(charToEvaluate == '.' && numberOfLexemeChars > 0){

            if(currentLexemeContents.contains(".")){
                return false;
            }

            endOfCurrentLexeme = currentLexemeContents.charAt(numberOfLexemeChars - 1);

            if(!(Character.isDigit(endOfCurrentLexeme))){
                return false;
            }

            return true;

        }else if(charToEvaluate == 'E' && numberOfLexemeChars > 0){

            if(currentLexemeContents.contains("E")){
                return false;
            }

            endOfCurrentLexeme = currentLexemeContents.charAt(numberOfLexemeChars - 1);

            if(!(Character.isDigit(endOfCurrentLexeme))){
                return false;
            }

            return true;

        }else if((charToEvaluate == '+' || charToEvaluate == '-') && numberOfLexemeChars > 0 ){

            if(currentLexemeContents.contains("+") || currentLexemeContents.contains("-") || !currentLexemeContents.contains(".")){
                return false;
            }

            endOfCurrentLexeme = currentLexemeContents.charAt(numberOfLexemeChars - 1);

            if(endOfCurrentLexeme != 'E'){
                return false;
            }

            return true;

        }

        return false;

    }


}
