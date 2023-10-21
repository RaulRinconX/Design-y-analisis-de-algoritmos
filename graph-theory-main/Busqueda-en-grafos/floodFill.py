"""
Este archivo fue creado por Juan Orduz.
Fecha de creación: 3 de marzo de 2023
"""

########################################################
###        floodFill (implementación BFS)            ###
########################################################

"""
El problema consiste en modificar una imagen representada
por una matriz de números enteros m x n, llamada "image"
donde image[i][j] representa el valor del píxel de la imagen.

Se dan tres enteros: sr, sc y color. Estos representan las coordenadas
del punto de inicio del relleno (sr,sc) y el nuevo color (color) con el
que se va a rellenar.

Para realizar el relleno, se considera el píxel de inicio y se buscan 
todos los píxeles adyacentes que tengan el mismo color que el píxel de inicio.
Luego, se busca en esos píxeles adyacentes y se continúa el proceso hasta que
se hayan revisado todos los píxeles conectados 4-direccionalmente.

Matriz = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1
sc = 1
color = 2
Resultado: [[2, 2, 2], [2, 2, 0], [2, 0, 1]]

Matriz = [[0,0,0],[0,0,0],[0,0,0]]
sr = 1
sc = 1
color = 3
Resultado: [[3, 3, 3], [3, 3, 3], [3, 3, 3]]
"""

def floodFill(image, sr, sc, color):
    #tamaño n de la matriz
    n = len(image)
    #tamaño m de la matriz
    m = len(image[0])
    #valor de la coordenada inicial
    valor_coordenada_inicial = image[sr][sc]
    #visitado es un diccionario con la coordenada y de valor un boolean que indica si fue visitado o si no
    visitado = {}
    #inicializar el diccionario con todos los valores en False
    for i in range(len(image)):
        for j in range(len(image[0])):
            visitado[(i,j)] = False
    #elemento inicial de la cola es la coordenada inicial
    queue = [(sr, sc)] 
    while queue:
        sr, sc = queue.pop(0)
        #si el valor de la coordenada en el diccionario visitado es False
        if visitado[(sr, sc)] == False:
            #compara si es igual al valor de la coordenada inicial
            if image[sr][sc] == valor_coordenada_inicial:
                #cambia el color de la coordenada
                image[sr][sc] = color
                #lo marca como visitado
                visitado[(sr, sc)] = True
                #agrega las coordenadas adyacentes a la cola
                if sr > 0:
                    queue.append((sr-1, sc))
                if sr < n-1:
                    queue.append((sr+1, sc))
                if sc > 0:
                    queue.append((sr, sc-1))
                if sc < m-1:
                    queue.append((sr, sc+1))
    return image
        
print(floodFill([[1,1,1],[1,1,0],[1,0,1]],1,1,2))