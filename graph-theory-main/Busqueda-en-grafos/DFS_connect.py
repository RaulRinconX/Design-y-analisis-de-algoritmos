"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 3 de marzo de 2023
"""

########################################################
###                   DFS_CONNECT                    ###
########################################################

"""
¿Existe algún camino que conecte a Node1 y Node2 en el grafo G (representado por AdjList)?

lista de adyacencia = {1:[2,3,4],2:[1],3:[1,4],4:[3,1]}
Node1 = 2
Node2 = 4
Resultado: True

lista de adyacencia = {1:[2],2:[1],3:[1,4],4:[3,1]}
Node1 = 2
Node2 = 4
Resultado: False
"""

def DFS_connect(AdjList:dict, Node1:int,Node2:int)-> bool:
    llaves = list(AdjList.keys())
    visitado = {}
    #Inicializar un diccionario que tiene como llaves los nodos y el valor False (el boolean indicará si está conectado o no) 
    for element in llaves:
        visitado[element] = False
    #llama a la recursión del DFS_connect
    DFS_recursion(AdjList,Node1,visitado)
    return visitado[Node2]

def DFS_recursion(AdjList,Node1,visitado):
    #marca el nodo como visitado
    visitado[Node1] = True
    for vecino in AdjList[Node1]:
        #si el nodo adyacente no está marcado hace la recursión con dicho nodo
        if visitado[vecino] == False:
            DFS_recursion(AdjList,vecino,visitado)

print(DFS_connect({1:[2,3,4],2:[1],3:[1,4],4:[3,1]},2,4))