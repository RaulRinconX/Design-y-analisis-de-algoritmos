"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 4 de marzo de 2023
"""

########################################################
###                    dijkstra                      ###
########################################################

"""
este algoritmo implementa "dijkstra" para saber el camino más corto a cada uno de los nodos 
lista de adyacencia = {1:[[2,7],[3,3]], 2:[[4,2]], 3:[[2,2],[4,8]], 4:[]}
Node1 = 1
Node2 = 2
Resultado: [1, 3, 2]
lista de adyacencia = {1:[[2,7],[3,3]], 2:[[4,2]], 3:[[2,2],[4,8]], 4:[], 5:[]}
Node1 = 1
Node2 = 5
Resultado: []
lista de adyacencia = {1:[[2,4],[3,2]], 2:[[4,5]], 3:[[2,1],[4,8],[5,10]], 4:[[5,2],[6,6]],5:[[6,2]],6:[]}
Node1 = 1
Node2 = 6
Resultado: [1, 3, 2, 4, 5, 6]
Como se puede evidenciar si no existe un camino al nodo se retorna una lista vacia. Acá solo se vé el camino. 
(ver min_cost_dijkstra para saber el costo minimo para llegar a cada nodo)
"""

def dijkstra(AdjList,Node1:int, Node2:int):
    llaves = list(AdjList.keys())
    camino = {}
    #escoger arbitrariamente un nodo para la implementación
    nodo_inicial = Node1
    camino[nodo_inicial] = (nodo_inicial,0)
    #se agrega a la cola una "marca" que representa el costo minimo para llegar al nodo
    queue = [(nodo_inicial,0)]
    visitado = {}
    for element in llaves:
        visitado[element] = False
    while queue:
        #se retira el nodo actual con su respectivo peso para poder obtener los nodos adyacentes
        nodo_actual,peso_actual = queue.pop(0)
        #al ser una marca asumimos que el nodo no solo está visitado si no que tambien tiene calculado su costo minimo
        visitado[nodo_actual] = True
        #inicializar una lista que agregará la suma de los pesos de los adyacentes para calcular cual es el de menor peso
        pesos = []
        for element in AdjList[nodo_actual]:
            #se suma el peso del nodo con el peso actual para representar el peso total
            pesos.append(peso_actual + element[1])
        if len(pesos)>0:
            #extraemos el peso de menor valor
            menor_peso = min(pesos)
            indice = pesos.index(menor_peso)
            #se agrega a la cola el nodo que tiene el peso de menor valor con su peso total para representar la siguiente marca
            queue.append((AdjList[nodo_actual][indice][0],menor_peso))
            #al diccionario camino se le agrega el nodo y su peso total definitivo
            camino[AdjList[nodo_actual][indice][0]] = (nodo_actual,menor_peso)

    # a diferencia de min_cost_dijkstra se agrega un paso adicional para hacer un "regreso" por los nodos hasta establecer el camino
    orden = []
    a = camino.get(Node2)
    #asegurar que el nodo 2 está en el diccionario camino (es alcanzable de alguna forma desde el node1)
    if a is not None:
        #cada elemento de camino tiene el nodo anterior por lo que es facil hacer el recorrido de forma invertida
        orden.append(Node2)
        anterior = camino[Node2][0] 
        orden.append(anterior)
        while anterior != Node1:
            anterior = camino[anterior][0]
            orden.append(anterior)
        #invertir el orden de la lista para representar correctamente el orden
        orden = orden[::-1]
    print(orden)

#dijkstra({1:[[2,7],[3,3]], 2:[[4,2]], 3:[[2,2],[4,8]], 4:[],5:[]},1,5)
dijkstra({1:[[2,4],[3,2]], 2:[[4,5]], 3:[[2,1],[4,8],[5,10]], 4:[[5,2],[6,6]],5:[[6,2]],6:[]},1,6)