import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestCaseExtractor {

    public static void main(String[] args) throws IOException {

        String inputFileName = System.getProperty("user.dir") + "/TestCaseExtractor/TestCase"; // Input file name in the same folder

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line = br.readLine();
            int T = Integer.parseInt(line.trim()); // Extract number of test cases

            for (int t = 1; t <= T; t++) {
                line = br.readLine();
                if (line == null) break;

                String[] nm = line.split(" ");
                int N = Integer.parseInt(nm[0]);
                int M = Integer.parseInt(nm[1]);

                List<String> inputs = new ArrayList<>();
                for (int i = 0; i < M; i++) {
                    line = br.readLine();
                    if (line == null) break;
                    inputs.add(line);
                }

                // Write to output file
                String outputFileName = "testcase_" + t + ".txt";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
                    bw.write("N=" + N + " M=" + M);
                    bw.newLine();
                    for (String input : inputs) {
                        bw.write(input);
                        bw.newLine();
                    }
                }

                System.out.println("Test case " + t + " written to " + outputFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
