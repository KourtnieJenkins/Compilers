import java.util.ArrayList;

public class LexemeHandler {

    private ArrayList<ILexeme> listOfLexemes = new ArrayList<>();
    private LexemeFlag lexemeFlag;
    private ILexeme lexeme;
    private ILexemeValidator lexemeValidator;
    private ITokenValidator tokenValidator;

    public LexemeHandler(ILexemeValidator lexemeValidator,
                         ITokenValidator tokenValidator){

        this.lexemeValidator = lexemeValidator;
        this.tokenValidator = tokenValidator;
        lexemeFlag = LexemeFlag.None;
        lexeme = new Lexeme();

    }

    public void setListOfLexemes(ArrayList<ILexeme> listOfLexemes){
        this.listOfLexemes = listOfLexemes;
    }

    public void scanForLexemes(String lineOfInput){

        char charToEvaluate;
        String lexemeIndentitiy = "";

        for(int i = 0; i < lineOfInput.length(); i++){

            charToEvaluate = lineOfInput.charAt(i);

            if(lexemeValidator.isValidKeywordOrIDChar(charToEvaluate)){

                if(lexemeFlag == LexemeFlag.None){

                    lexemeFlag = LexemeFlag.Alphanumeric;
                    lexeme.setTokenType(TokenTypes.ID);

                }else if(lexemeFlag == LexemeFlag.Digit || lexemeFlag == LexemeFlag.Symbol){

                    lexemeFlag = LexemeFlag.Alphanumeric;
                    lexeme.setIdentifier(lexemeIndentitiy); //take what ever is in the lexemeIdentity & start a new lexeme
                    startNewLexeme();                       //reset lexeme identity to ""
                    lexeme.setTokenType(TokenTypes.ID);
                    lexemeIndentitiy = "";                  //set lexemeFlag to LexemeFlag.Alphanumeric
                }

                lexemeIndentitiy += charToEvaluate;
                continue;

            }else if(Character.isDigit(charToEvaluate) || lexemeValidator.isValidDigitChar(charToEvaluate, lexemeIndentitiy)){

                if(lexemeFlag == LexemeFlag.None){

                    lexemeFlag = LexemeFlag.Digit;
                    lexeme.setTokenType(TokenTypes.Num);


                }else if(lexemeFlag == LexemeFlag.Alphanumeric || lexemeFlag == LexemeFlag.Symbol){

                    lexemeFlag = LexemeFlag.Digit;
                    lexeme.setIdentifier(lexemeIndentitiy); //take what ever is in the lexemeIdentity & start a new lexeme
                    startNewLexeme();                       //reset lexeme identity to ""
                    lexeme.setTokenType(TokenTypes.Num);
                    lexemeIndentitiy = "";                  //set lexemeFlag to LexemeFlag.Digit
                }

                lexemeIndentitiy += charToEvaluate;
                continue;

            }else if(lexemeValidator.isValidSymbolChar(charToEvaluate, lexemeIndentitiy)){

                if(lexemeFlag == LexemeFlag.None){

                    lexemeFlag = LexemeFlag.Symbol;
                    lexeme.setTokenType(TokenTypes.SpecialSymbol);

                }else if(lexemeFlag == LexemeFlag.Alphanumeric || lexemeFlag == LexemeFlag.Digit ||
                        (lexemeFlag == LexemeFlag.Symbol && lexemeIndentitiy.length() > 0 && charToEvaluate != '=')){

                    lexemeFlag = LexemeFlag.Symbol;
                    lexeme.setIdentifier(lexemeIndentitiy); //take what ever is in the lexemeIdentity & start a new lexeme
                    startNewLexeme();                       //reset lexeme identity to ""
                    lexeme.setTokenType(TokenTypes.SpecialSymbol);
                    lexemeIndentitiy = "";                  //set lexemeFlag to LexemeFlag.Symbol

                }

                lexemeIndentitiy += charToEvaluate;
                continue;

            }else{
                if(lexemeFlag != LexemeFlag.None){

                    lexemeFlag = LexemeFlag.None;
                    lexeme.setIdentifier(lexemeIndentitiy); //take what ever is in the lexemeIdentity & start a new lexeme
                    startNewLexeme();                       //reset lexeme identity to ""
                    lexemeIndentitiy = "";


                }

                if(!isGenericDelimter(charToEvaluate)){
                    System.out.println("Error: " + charToEvaluate);
                }

            }

        }
    }

    public void startNewLexeme(){
        if(!lexeme.getIdentifier().isEmpty()){

            upDateTokenType(lexeme);
            listOfLexemes.add(lexeme);
            printLexeme(lexeme);
            lexeme = new Lexeme();

        }
    }

    public void upDateTokenType(ILexeme lexeme){
        if(lexeme.getTokenType() == TokenTypes.ID && tokenValidator.isKeyword(lexeme)){
            lexeme.setTokenType(TokenTypes.Keyword);
        }
    }

    public void printLexeme(ILexeme lexemeToPrint){


        switch(lexemeToPrint.getTokenType()){
            case ID:
                System.out.print("ID: ");
                break;
            case Keyword:
                System.out.print("Keyword: " );
                break;
            case Num:
                System.out.print("Num: ");
                break;
            default:
                break;
        }

        System.out.println(lexemeToPrint.getIdentifier());

    }

    public boolean isGenericDelimter(char charToEvaluate){

        if(charToEvaluate == ' '){
            return true;
        }else if(charToEvaluate == '\n'){
            return true;
        }

        return false;

    }

}
