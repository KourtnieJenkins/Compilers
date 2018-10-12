import org.junit.Assert;
import org.junit.Test;

public class LexemeHandlerTests {

    private final LexemeHandler systemUnderTest = new LexemeHandler(new LexemeValidator(),
                                                                    new TokenValidator());

    @Test
    public void TestIsGenericDelimter_WhenInvokedWithNewLine_ReturnTrue(){

        boolean actual = systemUnderTest.isGenericDelimter('\n');
        Assert.assertTrue(actual);

    }

    @Test
    public void TestIsGenericDelimter_WhenInvokedWithSpace_ReturnTrue(){

        boolean actual = systemUnderTest.isGenericDelimter(' ');
        Assert.assertTrue(actual);

    }

    @Test
    public void TestIsGenericDelimter_WhenInvokedAnythingBesidesSpaceOrNewLine_ReturnFalse(){

        boolean actual = systemUnderTest.isGenericDelimter('k');
        Assert.assertFalse(actual);

    }

    @Test
    public void TestScanForLexemes_WhenInvokedWithInvalidSymbol_PrintError(){
        systemUnderTest.scanForLexemes("@");
    }

    @Test
    public void TestScanForLexemes_WhenInvokedWithValidAndInvalidSymbol_PrintError(){
        systemUnderTest.scanForLexemes("int jk3i8jlk@&==-&(jk\n");
    }

    @Test
    public void TestScanForLexemes_WhenInvokedWithValidAndInvalidSymbol_PrintProperly(){
        systemUnderTest.scanForLexemes("1.3E+67868768-int\n");
    }


}
