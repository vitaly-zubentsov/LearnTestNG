import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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

    @Test(groups = {"positive"}, dataProvider = "namesProvider")
    public void createFileWithDataProviderNames(String name) throws IOException {
        File file = new File(path + name);
        Assert.assertTrue(file.createNewFile());
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviderFromCSVFile.class, dataProvider = "dataProviderFromCSVFile")
    public void createFileWithDataProviderNamesFromFile(String name) throws IOException {
        File file = new File(path + name);
        Assert.assertTrue(file.createNewFile());
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

    @DataProvider
    public Iterator<Object> namesProvider() {
        List<Object> names = new ArrayList<>();
        for (int i=1; i < 10; i++){
            names.add("name" + i);
        }
        return names.iterator();
    }
}