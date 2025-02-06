import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    // Equivalente a C_ncacheparentesis en Python
    private static Map<Integer, List<String>> C_ncacheparentesis = new HashMap<>();
    
    static {
        // Inicializamos con los mismos valores base
        C_ncacheparentesis.put(0, Collections.singletonList(""));
        C_ncacheparentesis.put(1, Collections.singletonList("()"));
        C_ncacheparentesis.put(2, Arrays.asList("()()", "(())"));
    }

    public static List<String> recursiva(int n) {
        if(n == 0) {
            return C_ncacheparentesis.get(0);
        } else if(n == 1) {
            return C_ncacheparentesis.get(1);
        } else if(n == 2) {
            return C_ncacheparentesis.get(2);
        } else {
            // Si no está cacheado, lo calculamos
            if(!C_ncacheparentesis.containsKey(n)) {
                List<String> acumulados = new ArrayList<>();
                
                // Mantenemos la estructura original,
                // pero saltamos m=0 para evitar la recursión infinita
                for(int m = 0; m < n; m++) {
                    // Salta el caso conflictivo m=0 => recursiva(n-0) = recursiva(n)
                    if(m == 0) {
                        continue;
                    }
                    
                    for(String p : recursiva(m)) {
                        for(String q : recursiva(n - m)) {
                            // p + q
                            acumulados.add(p + q);
                            // q + p
                            acumulados.add(q + p);
                            // p hasta la mitad + q + resto de p
                            int half = p.length() / 2;
                            acumulados.add(p.substring(0, half) + q + p.substring(half));
                        }
                    }
                }
                
                // Eliminamos duplicados con un set
                Set<String> conjunto = new HashSet<>(acumulados);
                // Guardamos en el mapa
                C_ncacheparentesis.put(n, new ArrayList<>(conjunto));
            }
            return C_ncacheparentesis.get(n);
        }
    }

    public static void main(String[] args) {
        long inicio = System.nanoTime();
        List<String> result = recursiva(9);
        double fin = (System.nanoTime() - inicio) / 1_000_000_000.0;
        
        System.out.println("Tiempo de ejecución: " + fin + " segundos");
        
        try(FileWriter file = new FileWriter("./data/output.csv", true)) {
            file.write("java," + fin + "\n");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
