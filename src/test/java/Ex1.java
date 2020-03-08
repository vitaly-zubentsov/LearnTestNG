import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ex1 {


    private Path path;

    @BeforeClass(alwaysRun = true)
    public void createTempDirectory() throws IOException {
        path = Files.createTempDirectory("TempDirectory");
    }

    @AfterClass(alwaysRun = true)
    public void deleteTempDirectory() throws IOException {
        Files.delete(path);
    }

    @Test(groups = {"positive"})
    public void createFileWithNumbersTest() throws IOException {
        File file = new File(path + "123");
        Assert.assertTrue(file.createNewFile());
    }

    @Test(groups = {"positive"})
    public void createFileWithLettersTest() throws IOException {
        File file = new File(path + "HelloWorld");
        Assert.assertTrue(file.createNewFile());
    }

    @Test(groups = {"positive"})
    public void createFileWithExpansion() throws IOException {
        File file = new File(path + "123HelloWorld.txt");
        Assert.assertTrue(file.createNewFile());
    }

    @Test(groups = {"negative"})
    public void createFileWithBadName() throws IOException {
        File file = new File(path + "");
        Assert.assertFalse(file.createNewFile());

    }
}