import org.junit.Assert;
import org.junit.Test;

public class CommentHandlerTests {

    private final CommentHandler systemUnderTest = new CommentHandler();
    private String expected;

    @Test
    public void TestScanLine_InvokedWithSingleBlockComment_ReturnsExpectedVariable(){

        expected = "Hello World";
        String actual = systemUnderTest.replaceComment("Hello/*This is a comment*/World", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestReplaceComment_InvokedWithSingleNestedBlockComment_ReturnsExpectedVariable(){

        expected = "Hello World";
        String actual = systemUnderTest.replaceComment("Hello/*This /*is a*/ comment*/World", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestReplaceComment_InvokedWithExtraStartOfBlockComment_ReturnsExpectedVariable(){

        expected = "Hello";
        String actual = systemUnderTest.replaceComment("Hello/*This /*is a comment*/ World", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestReplaceComment_InvokedWithExtraEndOfBlockComment_ReturnsExpectedVariable(){

        expected = "Hello World*/";
        String actual = systemUnderTest.replaceComment("Hello/*greg*/World*/", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestReplaceComment_InvokedWithLineCommentInBlockComment_ReturnsExpectedVariable(){

        expected = "Hello World";
        String actual = systemUnderTest.replaceComment("Hello/*//greg*/World", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestReplaceComment_InvokedWithLineComment_ReturnsExpectedVariable(){

        expected = "Hello";
        String actual = systemUnderTest.replaceComment("Hello//*greg*/ World", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestReplaceComment_InvokedWithLineContainingOtherSymbols_ReturnsExpectedVariable(){

        expected = "Hey87& there  World";
        String actual = systemUnderTest.replaceComment("Hey87& there /*jj*/World//bob", ' ');

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void TestIsLineComment_WhenInvokedWithTwoForwardSlashes_ReturnTrue(){

        boolean actual = systemUnderTest.isLineComment('/', '/');

        Assert.assertTrue(actual);

    }

    @Test
    public void TestIsLineComment_WhenInvokedWithAnythingOtherThanTwoForwardSlashes_ReturnFalse(){

        boolean actual = systemUnderTest.isLineComment('/', '.');

        Assert.assertFalse(actual);

    }

    @Test
    public void TestIsStartOfBlockComment_WhenInvokedWithForwardSlashAndAsterix_ReturnTrue(){

        boolean actual = systemUnderTest.isStartOfBlockComment('/', '*');

        Assert.assertTrue(actual);

    }

    @Test
    public void TestIsStartOfBlockComment_WhenNotInvokedWithForwardSlashAndAsterix_ReturnFalse(){

        boolean actual = systemUnderTest.isStartOfBlockComment('/', '.');

        Assert.assertFalse(actual);

    }

    @Test
    public void TestIsEndOfBlockComment_WhenInvokedWithAsterixAndForwardSlash_ReturnTrue(){

        boolean actual = systemUnderTest.isEndOfBlockComment('*', '/');

        Assert.assertTrue(actual);

    }

    @Test
    public void TestIsEndOfBlockComment_WhenNotInvokedWithForwardSlashAndAsterix_ReturnFalse(){

        boolean actual = systemUnderTest.isEndOfBlockComment('.', '/');

        Assert.assertFalse(actual);

    }


}
