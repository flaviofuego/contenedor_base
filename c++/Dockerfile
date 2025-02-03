FROM gcc:12.2.0

WORKDIR /app
COPY main.cpp .
RUN g++ main.cpp -o main -O2

CMD ["./main"]