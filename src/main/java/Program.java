import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args){

        ICommentHandler commentHandler = new CommentHandler();
        ITokenValidator tokenValidator = new TokenValidator();
        ILexemeValidator lexemeValidator = new LexemeValidator();
        LexemeHandler lexemeHandler = new LexemeHandler(lexemeValidator, tokenValidator);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(commentHandler, lexemeHandler);//new LexemeValidator());

        String sourceCode = grabAllDataFromFile(args[0]);

        lexicalAnalyzer.scan(sourceCode);

    }

    public static String grabAllDataFromFile(String fileName){

        String sourceCode = "";
        String line = null;

        try {

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                sourceCode += (line + '\n');
                //System.out.println(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {

            System.out.println("Unable to open file '" + fileName + "'");

        }
        catch(IOException ex) {

            System.out.println("Error reading file '" + fileName + "'");
        }

        return sourceCode;

    }

}
