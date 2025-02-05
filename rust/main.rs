use std::collections::{HashMap, HashSet};
use std::fs::OpenOptions;
use std::io::Write;
use std::time::Instant;

fn recursiva(n: usize, cache: &mut HashMap<usize, Vec<String>>) -> Vec<String> {
    if n == 0 {
        return vec!["".to_string()];
    } else if n == 1 {
        return vec!["()".to_string()];
    } else if n == 2 {
        return vec!["()()".to_string(), "(())".to_string()];
    } else {
        if !cache.contains_key(&n) {
            let mut result = Vec::new();
            for m in 0..n {
                for p in recursiva(m, cache) {
                    for q in recursiva(n - m, cache) {
                        result.push(format!("{}{}", p, q));
                        result.push(format!("{}{}", q, p));
                        result.push(format!("{}{}{}", &p[0..p.len() / 2], q, &p[p.len() / 2..]));
                    }
                }
            }
            let unique: HashSet<String> = result.into_iter().collect();
            cache.insert(n, unique.into_iter().collect());
        }
        return cache.get(&n).unwrap().clone();
    }
}

fn main() {
    let mut cache = HashMap::new();
    let inicio = Instant::now();
    let _result = recursiva(12, &mut cache);
    let fin = inicio.elapsed().as_secs_f64();

    let mut file = OpenOptions::new()
    .append(true)  // Abrir en modo apéndice para no sobrescribir
    .open("data/output.csv")
    .unwrap();

    writeln!(file, "rust,{}", fin).unwrap();
}