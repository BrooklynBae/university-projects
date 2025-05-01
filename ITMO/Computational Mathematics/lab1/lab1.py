import numpy as np
from itertools import permutations;
import math

def is_diagonally_dominant(matrix, n):
    for i in range(n):
        diagonal_element = abs(matrix[i][i])
        sum_of_other_elements = sum(abs(matrix[i][j]) for j in range(n) if j != i)
        if diagonal_element < sum_of_other_elements:
            return False
    return True

def rearrange_for_diagonal_dominance(matrix, b, n):
    for perm in permutations(range(n)):
        new_matrix = matrix.copy()
        new_b = b.copy()

        for i in range(n):
            new_matrix[i] = matrix[perm[i]]
            new_b[i] = b[perm[i]]

        if is_diagonally_dominant(new_matrix, n):
            return new_matrix, new_b

    best_matrix = matrix.copy()
    best_b = b.copy()
    max_dominant_rows = 0

    for perm in permutations(range(n)):
        new_matrix = matrix.copy()
        new_b = b.copy()

        for i in range(n):
            new_matrix[i] = matrix[perm[i]]
            new_b[i] = b[perm[i]]

        # Считаем количество строк, удовлетворяющих диагональному преобладанию
        dominant_rows = 0
        for i in range(n):
            diagonal_element = abs(new_matrix[i][i])
            sum_of_other_elements = sum(abs(new_matrix[i][j]) for j in range(n) if j != i)
            if diagonal_element > sum_of_other_elements:
                dominant_rows += 1

        # Если найдена матрица с большим количеством "доминирующих" строк, обновляем лучшую
        if dominant_rows > max_dominant_rows:
            max_dominant_rows = dominant_rows
            best_matrix = new_matrix
            best_b = new_b

    if max_dominant_rows > 0:
        print("Найдена матрица, приближенная к диагонально преобладающей:")
        print(best_matrix)
        return best_matrix, best_b

    return matrix, b

def compute_norm(matrix, n):
    max_ratio = 0
    for i in range(n):
        diagonal_element = abs(matrix[i][i])
        sum_of_other_elements = sum(abs(matrix[i][j]) for j in range(n) if j != i)
        ratio = sum_of_other_elements / diagonal_element
        if ratio > max_ratio:
            max_ratio = ratio
    return max_ratio

def transform_matrix(matrix, n):
    transformed_matrix = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        diagonal_element = matrix[i][i]
        for j in range(n):
            if i == j:  # Если элемент на диагонали
                transformed_matrix[i][j] = 0  # Устанавливаем его в 0
            else:
                transformed_matrix[i][j] = matrix[i][j] / diagonal_element
    return transformed_matrix

def sum_and_sqrt(transformed_matrix, n):
    total_sum = 0
    for i in range(n):
        for j in range(n):
            total_sum += transformed_matrix[i][j]  # Используем преобразованную матрицу
    return math.sqrt(total_sum)

def jacobi_method(matrix, vector, epsilon, max_iterations=1000):
    n = len(matrix)
    x = np.zeros(n)
    x_new = np.zeros(n)
    iterations = 0
    errors = []

    while True:
        for i in range(n):
            sigma = sum(matrix[i][j] * x[j] for j in range(n) if j != i)
            x_new[i] = (vector[i] - sigma) / matrix[i][i]

        error = np.linalg.norm(x_new - x)
        errors.append(error)
        iterations += 1

        print(f"Итерация {iterations}: Норма разности = {error}")

        if error < epsilon or iterations >= max_iterations:
            break

        x = np.copy(x_new)

    residual = np.dot(matrix, x_new) - vector
    return x_new, iterations, errors, residual

def input_matrix(n):
    matrix = []
    print("Введите матрицу построчно (элементы разделяйте пробелом):")
    for i in range(n):
        while True:
            row_input = input(f"Строка {i + 1}: ").strip()
            try:
                row = list(map(float, row_input.split()))
                if len(row) != n:
                    print(f"Ошибка: в строке должно быть ровно {n} элементов.")
                else:
                    matrix.append(row)
                    break
            except ValueError:
                print("Ошибка: введите числа, разделенные пробелами.")
    return np.array(matrix)

def input_vector(n):
    while True:
        print("Введите вектор правых частей (элементы разделяйте пробелом):")
        vector_input = input().strip()
        try:
            vector = list(map(float, vector_input.split()))
            if len(vector) != n:
                print(f"Ошибка: в векторе должно быть ровно {n} элементов.")
            else:
                return np.array(vector)
        except ValueError:
            print("Ошибка: введите числа, разделенные пробелами.")

def main():
    while True:
        try:
            n = int(input("Введите размерность матрицы (n): "))
            if n <= 0:
                print("Ошибка: размерность должна быть положительным числом.")
            else:
                break
        except ValueError:
            print("Ошибка: введите целое число.")

    matrix = input_matrix(n)

    vector = input_vector(n)

    while True:
        try:
            epsilon = float(input("Введите точность: "))
            if epsilon <= 0:
                print("Ошибка: точность должна быть положительным числом.")
            else:
                break
        except ValueError:
            print("Ошибка: введите число.")

    if not is_diagonally_dominant(matrix, n):
        print("\nДиагональное преобладание отсутствует. Переставим строки.")
        matrix, vector = rearrange_for_diagonal_dominance(matrix, vector, n)
        if not is_diagonally_dominant(matrix, n):
            print("Невозможно достичь диагонального преобладания. Метод может не сойтись.")
        else:
            print("Диагональное преобладание достигнуто!")

    matrix_norm_custom = compute_norm(matrix, n)
    print(f"Норма матрицы: {matrix_norm_custom}\n")

    solution, iterations, errors, residual = jacobi_method(matrix, vector, epsilon)

    errors = [float(error) for error in errors]

    print("\nРезультаты:")
    print(f"Вектор неизвестных (решение): {solution}")
    print(f"Количество итераций: {iterations}")
    print(f"Вектор погрешностей: {errors}")
    print(f"Вектор невязок: {residual}")
    transformed_matrix = transform_matrix(matrix, n)
    summ = sum_and_sqrt(transformed_matrix, n)
    print(f"Норма матрицы №3:  {summ}")

if __name__ == "__main__":
    main()
# Вектор правых частей b
# b — это целевые значения, которые должны быть достигнуты при решении системы.
# Точность
# tolerance — это параметр, который определяет, насколько близко приближенное решение должно быть к точному.
# Он используется для остановки итераций в численных методах.
# Действия:
# Для каждой строки матрицы проверяет, что модуль диагонального элемента больше суммы модулей остальных элементов строки.
# Если для хотя бы одной строки это условие не выполняется, возвращает False.
# Если условие выполняется для всех строк, возвращает True

#Методы Якоби и Гаусса-Зейделя гарантированно сходятся к решению, если матрица системы диагонально доминирующая.

# Как проверить диагональное преобладание?
# Для каждой строки матрицы:
#
# Вычислить модуль диагонального элемента |aij|
# Вычислить сумму модулей остальных элементов строки sum(i!=j |aij|)
# Проверить, выполняется ли условие |aij| > sum(i!=j |aij|)
# Если условие выполняется для всех строк, матрица диагонально доминирующая.

#Добавить перестановку строк матрицы для нахождения диагонального преобладания
# Диагональное преобладание отсутствует. Пытаемся переставить строки...
# Диагональное преобладание достигнуто после перестановки строк.
#
# Норма матрицы A (спектральная норма): 13.030989593863206
# Используемая точность: 0.001
#
# Вектор неизвестных (x):
# [0.99996508 0.99995608 0.99994456]
#
# Количество итераций: 7
#
# Вектор погрешностей (на каждой итерации):
# [2.256103, 0.683593, 0.188308, 0.054392, 0.01539, 0.004393, 0.00125, 0.000356]
#
# Вектор невязок:
# [-0.00044856 -0.00056448 -0.00071208]
#
# Решение, полученное с помощью библиотеки numpy:
# [1. 1. 1.]