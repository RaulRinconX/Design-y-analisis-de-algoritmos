"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 4 de marzo de 2023
"""

########################################################
###                min_cost_dijkstra                 ###
########################################################

"""
este algoritmo "implementa dijkstra" para saber el costo del camino más corto a cada uno de los nodos 
lista de adyacencia = {1:[[2,7],[3,3]], 2:[[4,2]], 3:[[2,2],[4,8]], 4:[]}
Resultado: {1: 0, 3: 3, 2: 5, 4: 7}
lista de adyacencia = {1:[[2,4],[3,2]], 2:[[4,5]], 3:[[2,1],[4,8],[5,10]], 4:[[5,2],[6,6]],5:[[6,2]],6:[]}
Resultado: {1: 0, 3: 2, 2: 3, 4: 8, 5: 10, 6: 12}
lista de adyacencia = {1:[[2,7],[3,3]], 2:[[3,1],[4,2]], 3:[[2,-10],[4,8]], 4:[]}
Resultado: no hay resultado se queda en bucle por un ciclo infinito (pesos negativos)
El resultado anterior es un diccionario que tiene cada nodo como llave y el valor es una tupla
donde el valor es el costo minimo para acceder. Es decir, el camino más corto para llegar a 4 es 7
Un problema de esta implementación es que solo muestra el costo minimo más no el camino para llegar al nodo
con dicho costo. Si no se puede llegar al nodo de ninguna manera, no aparecerá en el diccionario.
la complejidad depende del número de nodos y aristas en el grafo representado por la lista de adyacencia.
En cada iteración del while loop, se realiza una búsqueda lineal en la lista de adyacencia para obtener
los pesos de los nodos adyacentes.
Luego, se realiza otra búsqueda lineal en la lista de pesos para encontrar el peso mínimo.
En el peor de los casos, cada nodo tiene conexiones con todos los demás nodos, lo que resulta en O(|V|^2) 
comparaciones de peso en total.
si se quiere obtener el costo minimo de un nodo en especifico es tan sencillo como agregar en parametros un nodo de llegada
y al diccionario camino hacerle un get.
"""


def min_cost_dijkstra(AdjList):
    llaves = list(AdjList.keys())
    camino = {}
    #escoger arbitrariamente un nodo para la implementación
    nodo_inicial = llaves[0]
    camino[nodo_inicial] = 0
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
            camino[AdjList[nodo_actual][indice][0]] = (menor_peso)
    print(camino)

    return 0

min_cost_dijkstra({1:[[2,7],[3,3]], 2:[[4,2]], 3:[[2,2],[4,8]], 4:[]})
#min_cost_dijkstra({1:[[2,4],[3,2]], 2:[[4,5]], 3:[[2,1],[4,8],[5,10]], 4:[[5,2],[6,6]],5:[[6,2]],6:[]})