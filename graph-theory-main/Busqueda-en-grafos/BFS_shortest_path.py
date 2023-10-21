"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 3 de marzo de 2023
"""

########################################################
###                BFS_shortest_path                 ###
########################################################

"""
¿Cuál es el camino de menor longitud que comunica a Node1,Node2 en el grafo G (representado por AdjList)?

lista de adyacencia = {1:[2,3,4],2:[1],3:[1,4],4:[3,1]}
Node1 = 2
Node2 = 4
Resultado: [2,1,4]

lista de adyacencia = {1:[2,3,4],2:[1],3:[1,4],4:[3,1]}
Node1: 3
Node2: 4
Resultado: [3,4]

lista de adyacencia = {1:[3,4],2:[4],3:[1,4],4:[3,1]}
Node1 = 1
Node2 = 2
Resultado: No hay camino
"""

def BFS_shortest_path(AdjList:dict, Node1:int, Node2:int):
    #mismo proceso que BFS_Connect a excepcion de que se inicializa una lista 'camino' para indicar el camino más corto
    camino = []
    camino.append(Node1)
    llaves = list(AdjList.keys())
    visitado = {}
    for element in llaves:
        visitado[element] = False
    #paso 1: la cola recibe tanto el nodo inicial como la lista con el camino más corto
    queue = [(Node1,camino)]
    while queue:
        (nodo, camino) = queue.pop(0)
        for element in AdjList[nodo]:
            if visitado[element] == False:
                visitado[element] = True
                if element == Node2:
                    #si el nodo vecino es igual al nodo de llegada se debe cortar el ciclo y se retorna el camino
                    return camino + [element]
                #en caso de no ser el mismo se agrega a la cola y se repite el proceso
                else:
                    queue.append((element, camino + [element]))
    #si nunca se alcanza el nodo de llegada se asume que no hay un camino existente entre Node 1 y Node 2
    return "No hay camino"

print(BFS_shortest_path({1:[2,3,4],2:[1],3:[1,4],4:[3,1]},3,4))