import time

C_ncacheparentesis = {
    0: [""],
    1: ["()"],
    2: ["()()", "(())"]
}

def recursiva(n):
    if n == 0:
        return [""]
    elif n == 1:
        return ["()"]
    elif n == 2:
        return ["()()", "(())"]
    else:
        if n not in C_ncacheparentesis:
            C_ncacheparentesis[n] = []        
            for m in range(n):
                for p in recursiva(m):
                    for q in recursiva(n-m):
                        C_ncacheparentesis[n].append(p+q)
                        C_ncacheparentesis[n].append(q+p)
                        C_ncacheparentesis[n].append(p[0:int(len(p)/2)] + q + p[int(len(p)/2):int(len(p))]   )
            C_ncacheparentesis[n] = list(set(C_ncacheparentesis[n]))
        return C_ncacheparentesis[n]

inicio = time.time()
result = recursiva(9)
fin = time.time() - inicio

print("Tiempo de ejecución: ", fin , "segundos")

with open("data/output_python.txt", "w") as file:
    file.write("python," + str(fin) + "\n")
    for r in result:
        file.write(r + "\n")