# Usa la imagen oficial de Rust
FROM rust:1.56

# Establece el directorio de trabajo
WORKDIR /rust

# Copia el archivo main.rs al contenedor
COPY main.rs .

# Compila el archivo main.rs
RUN rustc main.rs

# Ejecuta el binario compilado
CMD ["./main"]