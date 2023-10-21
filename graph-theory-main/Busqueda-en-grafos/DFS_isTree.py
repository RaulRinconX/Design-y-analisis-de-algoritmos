"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 3 de marzo de 2023
"""

########################################################
###                   DFS_isTree                     ###
########################################################

"""
dado un grafo retorne True si el grafo es un arbol, en otro caso False.

Recordar: Un grafo es un árbol si es un grafo conexo y acíclico.
Es decir, todos los vértices del grafo están conectados entre sí y no existen ciclos en el grafo.

lista de adyacencia = {1:[2,3,4],2:[1],3:[1,4],4:[3,1]}
Resultado: False (ciclos)

lista de adyacencia = {1:[2, 3, 4],2:[5, 6],3:[7],4:[],5:[],6:[],7:[8, 9],8:[],9:[],10:[]}
Resultado: False (10 no está conectado)

lista de adyacencia = {1:[2, 3, 4],2:[5, 6],3:[7],4:[],5:[],6:[],7:[8, 9],8:[],9:[]}
Resultado: True
"""
def DFS_isTree(AdjList:dict) -> bool:
    llaves = list(AdjList.keys())
    Nodo_inicial = llaves[0]
    visitado = {}
    for element in llaves:
        visitado[element] = False
    DFS_isTree_recursion(AdjList,Nodo_inicial,visitado)
    for nodo in llaves:
        if visitado[nodo] == False:
            # Si hay un vértice no visitado, entonces el grafo no es un árbol
            return False
    # Si se han visitado todos los vértices, entonces el grafo es un árbol
    return True

def DFS_isTree_recursion(AdjList,Nodo_inicial,visitado,padre=None):
    visitado[Nodo_inicial] = True
    for vecino in AdjList[Nodo_inicial]:
        if visitado[vecino] == False:
            DFS_isTree_recursion(AdjList,vecino,visitado, Nodo_inicial)
        #la unica forma en la que no se pueda considerar un ciclo siendo el nodo visitado es que no sea padre del vertice actual
        elif vecino != padre:
            # Si se encuentra un ciclo, el grafo no es un árbol
            visitado[vecino] = False

print(DFS_isTree({1: [2, 3, 4],2: [5, 6],3: [7],4: [],5: [],6: [],7: [8, 9],8: [],9: []}))