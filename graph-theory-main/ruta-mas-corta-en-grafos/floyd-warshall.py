"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 12 de marzo de 2023
"""
########################################################
###                 Floyd-Warshall                   ###
########################################################
"""

Complejidad O(V^3)

Este algortimo determina el camino minimo entre todos los pares de vertices de un grafo y a su vez
retorna False si tiene un ciclo negativo o True si no tiene un ciclo negativo

El algoritmo recorre cada elemento de cada columna y lo suma con cada elemento de cada fila. en caso de que que
la suma de ellos sea un valor menor a el elemento en la posición entre ellos (si tenemos [0][1] y [1][0] compara la suma con [1][1])
o el valor sea None se actualiza en la matriz.

Se detecta si hay un ciclo negativo cuando en el resultado final hay valores menores a cero en la diagonal de la matriz

Matriz de adyacencia = [[0,None,8,2],[2,0,4,1],[None,None,0,3],[None,None,3,0]]
Resultado: (True, [[0, None, 5, 2], [2, 0, 4, 1], [None, None, 0, 3], [None, None, 3, 0]])

Matriz de adyacencia = [[0,4,8,None,None],[4,0,1,2,None],[8,None,0,4,2],[None,2,4,0,7],[None,None,2,7,0]]
Resultado: (True, [[0, 4, 5, 6, 7], [4, 0, 1, 2, 3], [8, 6, 0, 4, 2], [6, 2, 3, 0, 5], [10, 8, 2, 6, 0]])

Matriz de adyacencia = [[0,-2,8,None,None],[-2,0,1,2,None],[8,None,0,4,2],[None,2,4,0,7],[None,None,2,7,0]]
Resultado: (False, [[-4, -6, -5, -4, -3], [-2, -4, 1, 2, 3], [4, 2, 0, 4, 2], [0, -2, -1, 0, 1], [6, 4, 2, 6, 0]])
"""

def floydWarshall(adjMatriz):
    for i in range(len(adjMatriz[0])):
        for j in range(len(adjMatriz[0])):
            #adjMatriz[i][j] elemento de la fila
            for k in range(len(adjMatriz[0])):
                if adjMatriz[j][i] != None and adjMatriz[i][k] != None and j != i:
                    costo = adjMatriz[j][i] + adjMatriz[i][k]
                    if adjMatriz[j][k] == None:
                        adjMatriz[j][k] = costo
                    elif costo < adjMatriz[j][k]:
                        adjMatriz[j][k] = costo
    for i in range(len(adjMatriz[0])):
        if adjMatriz[i][i] < 0:
            return False,adjMatriz
    return True,adjMatriz

#adjMatriz = [[0,None,8,2],[2,0,4,1],[None,None,0,3],[None,None,3,0]]
#adjMatriz = [[0,4,8,None,None],[4,0,1,2,None],[8,None,0,4,2],[None,2,4,0,7],[None,None,2,7,0]]
adjMatriz = [[0,-2,8,None,None],[-2,0,1,2,None],[8,None,0,4,2],[None,2,4,0,7],[None,None,2,7,0]]     
print(floydWarshall(adjMatriz))
