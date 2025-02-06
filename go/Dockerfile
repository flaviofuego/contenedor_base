#Usa la imagen oficial de Golang
FROM golang:1.16.3

#Crea el directorio de trabajo
WORKDIR /go/src/app

#Copia el archivo go.mod y go.sum
COPY go.mod go.sum ./

#Descarga las dependencias
RUN go mod download

#Copia el resto de los archivos
COPY . .

#Compila el proyecto
RUN go build -o /go/bin/app

RUN mkdir data
#Ejecuta el proyecto
CMD ["/go/bin/app"]


