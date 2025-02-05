import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    // Cache estática para los resultados
    private static Map<Integer, Set<String>> C_ncacheparentesis = new HashMap<>();

    static {
        // Inicializamos los casos base
        C_ncacheparentesis.put(0, new HashSet<>(Collections.singletonList("")));
        C_ncacheparentesis.put(1, new HashSet<>(Collections.singletonList("()")));
        Set<String> base2 = new HashSet<>();
        base2.add("()()");
        base2.add("(())");
        C_ncacheparentesis.put(2, base2);
    }

    public static Set<String> recursiva(int n) {
        if (!C_ncacheparentesis.containsKey(n)) {
            Set<String> resultSet = new HashSet<>();
            for (int m = 0; m < n; m++) {
                for (String p : recursiva(m)) {
                    for (String q : recursiva(n - m)) {
                        resultSet.add(p + q);
                        resultSet.add(q + p);
                        int mitad = p.length() / 2;
                        resultSet.add(p.substring(0, mitad) + q + p.substring(mitad));
                    }
                }
            }
            C_ncacheparentesis.put(n, resultSet);
        }
        return C_ncacheparentesis.get(n);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        recursiva(12);
        long end = System.currentTimeMillis() - start;

        // Guardar en output.csv
        try (FileWriter writer = new FileWriter("data/output.csv", true)) {
            writer.write("java," + end/1000.0 + "\n"); // en segundos, por consistencia con Python
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
