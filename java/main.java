import java.util.*;
import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class main {
    private static Map<Integer, List<String>> C_ncacheparentesis = new HashMap<>();

    static {
        C_ncacheparentesis.put(0, Arrays.asList(""));
        C_ncacheparentesis.put(1, Arrays.asList("()"));
        C_ncacheparentesis.put(2, Arrays.asList("()()", "(())"));
    }

    public static List<String> recursiva(int n) {
        if (n == 0) {
            return Arrays.asList("");
        } else if (n == 1) {
            return Arrays.asList("()");
        } else if (n == 2) {
            return Arrays.asList("()()", "(())");
        } else {
            if (!C_ncacheparentesis.containsKey(n)) {
                List<String> result = new ArrayList<>();
                for (int m = 0; m < n; m++) {
                    for (String p : recursiva(m)) {
                        for (String q : recursiva(n - m)) {
                            result.add(p + q);
                            result.add(q + p);
                            result.add(p.substring(0, p.length() / 2) + q + p.substring(p.length() / 2));
                        }
                    }
                }
                C_ncacheparentesis.put(n, new ArrayList<>(new HashSet<>(result)));
            }
            return C_ncacheparentesis.get(n);
        }
    }

    public static void main(String[] args) throws IOException {
        Instant inicio = Instant.now();
        List<String> result = recursiva(12);
        Instant fin = Instant.now();
        double tiempo = Duration.between(inicio, fin).toMillis() / 1000.0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))) {
            writer.write("java," + tiempo + "\n");
        }
    }
}