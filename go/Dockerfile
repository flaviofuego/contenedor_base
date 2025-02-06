
FROM golang:alpine3.21


# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo main.go al directorio de trabajo
COPY main.go .

# Compilar el programa
RUN go build -o main main.go

# Crear un directorio para el volumen compartido
#RUN mkdir -p /go/data

CMD ["./main"]



