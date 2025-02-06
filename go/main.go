package main

import (
    "fmt"
    "os"
    "time"
)

// Mapa global para cachear resultados, equivalente al diccionario de Python
var C_ncacheparentesis = map[int][]string{
    0: {""},
    1: {"()"},
    2: {"()()", "(())"},
}

func recursiva(n int) []string {
    // Casos base
    if n == 0 {
        return []string{""}
    } else if n == 1 {
        return []string{"()"}
    } else if n == 2 {
        return []string{"()()", "(())"}
    } else {
        // Verificamos si ya está calculado
        if _, existe := C_ncacheparentesis[n]; !existe {
            var resultados []string
            for m := 0; m < n; m++ {
                for _, p := range recursiva(m) {
                    for _, q := range recursiva(n - m) {
                        // p + q
                        resultados = append(resultados, p+q)
                        // q + p
                        resultados = append(resultados, q+p)
                        // p hasta la mitad + q + resto de p
                        half := len(p) / 2
                        resultados = append(resultados, p[:half]+q+p[half:])
                    }
                }
            }
            // Elimina duplicados usando un map temporal
            setTemp := make(map[string]bool)
            for _, s := range resultados {
                setTemp[s] = true
            }
            uniqueList := make([]string, 0, len(setTemp))
            for s := range setTemp {
                uniqueList = append(uniqueList, s)
            }
            C_ncacheparentesis[n] = uniqueList
        }
        return C_ncacheparentesis[n]
    }
}

func main() {
    inicio := time.Now()
    result := recursiva(9)
    duracion := time.Since(inicio).Seconds()

    fmt.Println("Tiempo de ejecución:", duracion, "segundos")

    // Guardar en archivo CSV
    file, err := os.OpenFile("data/output.csv", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
    if err != nil {
        fmt.Println("Error abriendo archivo:", err)
        return
    }
    defer file.Close()

    _, err = file.WriteString(fmt.Sprintf("go,%.6f\n", duracion))
    if err != nil {
        fmt.Println("Error escribiendo en el archivo:", err)
    }

    // Solo para que no aparezca variable sin usar
    _ = result
}
