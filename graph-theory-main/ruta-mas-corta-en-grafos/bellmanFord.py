#AdjList = {1:[[3,3]], 2:[[1,4],[3,1]], 3:[[4,2]], 4:[[1,-10]]}
AdjList = {1:[[2,2]], 2:[[3,1]], 3:[[4,1]], 4:[[5,1]], 5:[[1,1]]}

def bellmanFord(AdjList):
    llaves = list(AdjList.keys())
    nodo_inicial = llaves[0]
    visitado = {}

    camino = {}
    for element in llaves:
        camino[element] = None
    camino[nodo_inicial] = 0
    actualizacion = True
    for i in range(len(llaves)-1):
        for element in llaves:
            visitado[element] = False
        queue = [(nodo_inicial,camino[nodo_inicial])]
        if actualizacion == False:
            return camino
        while queue:
            nodo_actual, peso_actual = queue.pop(0)
            for element in AdjList[nodo_actual]:
                if visitado[element[0]] == False:
                    if camino[element[0]] == element[1] +  peso_actual:
                        actualizacion = False
                    if camino[element[0]] == None:
                        camino[element[0]] = element[1] +  peso_actual
                        actualizacion = True
                    elif camino[element[0]] > element[1] + peso_actual:
                        camino[element[0]] = element[1] + peso_actual
                        actualizacion = True
                    queue.append((element[0],element[1] + peso_actual))
                    visitado[element[0]] = True
    return camino

print(bellmanFord(AdjList))