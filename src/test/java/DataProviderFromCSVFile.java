import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderFromCSVFile {

    @DataProvider
    public static Iterator<Object> dataProviderFromCSVFile() {
        List<Object> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/test_data.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names.iterator();
    }
}
