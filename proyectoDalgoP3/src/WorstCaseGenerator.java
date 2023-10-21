import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WorstCaseGenerator {
    public static void main(String[] args) {
        int n_cases = 1;
        int n_citizens = (int) Math.pow(10, 3);
        int m_politicians = (int) Math.pow(10, 2);

        try (PrintWriter writer = new PrintWriter(new FileWriter("worst_case.in"))) {
            writer.println(n_cases);
            for (int i = 0; i < n_cases; i++) {
                writer.println(n_citizens + " " + m_politicians);
                for (int j = 0; j < n_citizens; j++) {
                    for (int k = 1; k <= m_politicians; k++) {
                        writer.print(k + " ");
                    }
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}