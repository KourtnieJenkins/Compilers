import org.junit.Assert;
import org.junit.Test;


public class LexemeValidatorTests {

    private LexemeValidator systemUnderTest = new LexemeValidator();
    private Lexeme inProgressLexeme;

    @Test
    public void TestIsisValidKeywordOrIDChar_InvokedWithLowerCaseAlphanumeric_ReturnTrue(){
        boolean actual = systemUnderTest.isValidKeywordOrIDChar('h');

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsisValidKeywordOrIDChar_InvokedWithUppercaseChar_ReturnFalse(){
        boolean actual = systemUnderTest.isValidKeywordOrIDChar('H');

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsisValidKeywordOrIDChar_InvokedWithDigit_ReturnFalse(){
        boolean actual = systemUnderTest.isValidKeywordOrIDChar('5');

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsisValidKeywordOrIDChar_InvokedWithSymbol_ReturnFalse(){
        boolean actual = systemUnderTest.isValidKeywordOrIDChar('.');

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsisValidKeywordOrIDChar_InvokedWithSpace_ReturnFalse(){
        boolean actual = systemUnderTest.isValidKeywordOrIDChar(' ');

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithDigit_ReturnTrue(){

        inProgressLexeme = new Lexeme();
        inProgressLexeme.setIdentifier("4");

        boolean actual = systemUnderTest.isValidDigitChar('5', inProgressLexeme.getIdentifier());

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithValidE_ReturnTrue(){

        boolean actual = systemUnderTest.isValidDigitChar('E', "3");

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithValidEAfterDecimal_ReturnTrue(){

        boolean actual = systemUnderTest.isValidDigitChar('E', "3.9");

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithInvalidE_ReturnFalse(){

        boolean actual = systemUnderTest.isValidDigitChar('E', "3.3E");

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithValidLowerCaseE_ReturnFalse(){

        boolean actual = systemUnderTest.isValidDigitChar('e', "3");

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithValidPlusSign_ReturnTrue(){

        boolean actual = systemUnderTest.isValidDigitChar('+', "3.9E");

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithInvalidPlusSignNoDecimal_ReturnFalse(){

        boolean actual = systemUnderTest.isValidDigitChar('+', "39E");

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithValidMinusSign_ReturnTrue(){

        boolean actual = systemUnderTest.isValidDigitChar('-', "3.9E");

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithInvalidMinusSignNoDecimal_ReturnFalse(){

        boolean actual = systemUnderTest.isValidDigitChar('-', "39E");

        Assert.assertFalse(actual);
    }

    @Test
    public void TestIsValidDigitChar_InvokedWithValidDigitAndSymbols_ReturnTrue(){

        boolean actual = systemUnderTest.isValidDigitChar('4', "3.9E+");

        Assert.assertTrue(actual);
    }

    @Test
    public void TestIsValidSymbolChar_InvokedWithValidSymbol_ReturnTrue(){

        boolean actual = systemUnderTest.isValidSymbolChar('/', "");

        Assert.assertTrue(actual);

    }

    @Test
    public void TestIsValidSymbolChar_InvokedWithInvalidSymbol_ReturnFalse(){

        boolean actual = systemUnderTest.isValidSymbolChar('@', "");

        Assert.assertFalse(actual);

    }

}
