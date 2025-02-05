#include <iostream>
#include <vector>
#include <unordered_map>
#include <set>
#include <chrono>
#include <fstream>

std::unordered_map<int, std::vector<std::string>> C_ncacheparentesis = {
    {0, {""}},
    {1, {"()"}},
    {2, {"()()", "(())"}}
};

std::vector<std::string> recursiva(int n, std::unordered_map<int, std::vector<std::string>>& cache) {
    if (n == 0) {
        return {""};
    } else if (n == 1) {
        return {"()"};
    } else if (n == 2) {
        return {"()()", "(())"};
    } else {
        if (cache.find(n) == cache.end()) {
            cache[n] = {};
            for (int m = 0; m < n; ++m) {
                for (const auto& p : recursiva(m, cache)) {
                    for (const auto& q : recursiva(n - m, cache)) {
                        cache[n].push_back(p + q);
                        cache[n].push_back(q + p);
                        cache[n].push_back(p.substr(0, p.size() / 2) + q + p.substr(p.size() / 2));
                    }
                }
            }
            std::set<std::string> unique(cache[n].begin(), cache[n].end());
            cache[n] = std::vector<std::string>(unique.begin(), unique.end());
        }
        return cache[n];
    }
}

int main() {
    std::unordered_map<int, std::vector<std::string>> cache;
    auto inicio = std::chrono::high_resolution_clock::now();
    auto result = recursiva(12, cache);
    auto fin = std::chrono::duration<double>(std::chrono::high_resolution_clock::now() - inicio).count();

    std::ofstream file("data/output.csv", std::ios::app); // Abre el archivo en modo append
    if (file.is_open()) {
        file << "c++," << fin << "\n"; // Agrega datos al final del archivo
        file.close();
    } else {
        std::cerr << "No se pudo abrir el archivo." << std::endl;
    }

    return 0;
}