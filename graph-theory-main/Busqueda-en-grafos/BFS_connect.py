"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 3 de marzo de 2023
"""

########################################################
###                   BFS_CONNECT                    ###
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

def BFS_connect(AdjList:dict, Node1:int,Node2:int)-> bool:
    #paso 1: crear una lista con los nodos del grafo
    llaves = list(AdjList.keys())
    #paso 2: Inicializar un diccionario que tiene como llaves los nodos y el valor False (el boolean indicará si está conectado o no) 
    visitado = {}
    for element in llaves:
        visitado[element] = False
    #paso 3: Iniciar la cola con el nodo de inicio
    queue = [Node1]
    #repetir hasta que se acabe la cola
    while queue:
        #obtiene el elemento en la posición cero de la cola
        nodo_actual = queue.pop(0)
        #paso 4: actualizar el boolean del nodo actual a True (es visitado)
        visitado[nodo_actual] = True
        #paso 5: revisar los nodos a los cuales está conectado el nodo actual y añadirlos a la cola
        for element in AdjList[nodo_actual]:
            if visitado[element] == False:
                queue.append(element)
    #paso 6: retornar el valor del diccionario 'visitado' del Nodo 2
    return visitado[Node2]

print(BFS_connect({1:[2,3,4],2:[1],3:[1,4],4:[3,1]},2,4))