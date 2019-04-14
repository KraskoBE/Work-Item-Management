package Tests;

import com.company.engine.EngineImpl;
import com.company.engine.contracts.Engine;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class Tests_With_Files {

    @Test
    public void test_001() throws Exception {
        executeIOTest("001");
    }

    @Test
    public void test_003() throws Exception {
        executeIOTest("003");
    }

    private void executeIOTest(String testNumber) throws Exception {
        String testInputFilePath = "./src/Tests/TestData/test." + testNumber + ".in.txt";
        InputStream testInput = new FileInputStream(testInputFilePath);
        System.setIn(testInput);

        ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(outputByteStream, true);
        System.setOut(output);

        Engine engine = new EngineImpl();
        engine.start();

        String testOutputFilePath = "./src/Tests/TestData/test." + testNumber + ".out.txt";
        FileInputStream fstream = new FileInputStream(testOutputFilePath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder expected = new StringBuilder();
        String strLine;
        while ((strLine = br.readLine()) != null) {
            expected.append(strLine).append(System.lineSeparator());
        }
        in.close();


        fstream = new FileInputStream(testOutputFilePath);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        StringBuilder actual = new StringBuilder();
        String strLine1;
        while ((strLine1 = br.readLine()) != null) {
            actual.append(strLine1).append(System.lineSeparator());
        }
        in.close();

        Assert.assertEquals(expected.toString().trim(), actual.toString().trim());
    }
}
