package ex5.p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jin on 2016. 2. 3..
 */
public class Calculator {

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callback.doSomethingWithReaderCallback(br);
            return ret;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer calcSum(String filepath) throws IOException {
        BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
            public Integer doSomethingWithReaderCallback(BufferedReader br) throws IOException {
                Integer sum = 0;
                String line = null;

                while ((line = br.readLine()) != null) {
                    sum += Integer.valueOf(line);
                }

                return sum;
            }
        };

        return fileReadTemplate(filepath, sumCallback);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
            public Integer doSomethingWithReaderCallback(BufferedReader br) throws IOException {
                Integer multiply = 1;
                String line = null;

                while ((line = br.readLine()) != null) {
                    multiply *= Integer.valueOf(line);
                }

                return multiply;
            }
        };

        return fileReadTemplate(filepath, sumCallback);
    }
}
