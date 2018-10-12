import java.util.ArrayList;

public class LexicalAnalyzer {

    private ICommentHandler commentHandler;
    private ScannerModeType scannerMode;
    private ArrayList<String> linesOfSourceCode = new ArrayList<>();
    private final ArrayList<ILexeme> listOfLexemes = new ArrayList<>();
    private LexemeHandler lexemeHandler;

    public LexicalAnalyzer(ICommentHandler commentHandler,
                           LexemeHandler lexemeHandler){

        this.commentHandler = commentHandler;
        this.lexemeHandler = lexemeHandler;
        lexemeHandler.setListOfLexemes(listOfLexemes);


    }

    public void scan(String sourceCodeFromFile){

        sourceCodeConverter(sourceCodeFromFile);

        for (String line : linesOfSourceCode) {

            System.out.print("INPUT: " + line);
            scanLine(line);


        }

    }

    public void sourceCodeConverter(String sourceCodeFromFile){

        char charFromSourceCode;
        String line = "";

        for(int i = 0; i < sourceCodeFromFile.length(); i++){

            charFromSourceCode = sourceCodeFromFile.charAt(i);

            line += charFromSourceCode;

            if(charFromSourceCode == '\n'){
                linesOfSourceCode.add(line);
                line = "";

            }else if(i == sourceCodeFromFile.length() - 1){
                line += '\n';
                linesOfSourceCode.add(line);
                line = "";
            }

        }
    }


    public void scanLine(String lineOfInput){

        String possibleLexeme;

        if(lineOfInput.length() > 1){

            lineOfInput = commentHandler.replaceComment(lineOfInput, ' ');

            lexemeHandler.scanForLexemes(lineOfInput);

        }else if(lineOfInput.length() > 0 && scannerMode == ScannerModeType.TokenMode){

            lexemeHandler.scanForLexemes(lineOfInput);

        }

    }

}
