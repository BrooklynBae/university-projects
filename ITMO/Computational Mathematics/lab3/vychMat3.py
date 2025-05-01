def left_rectangles(f, a, b, n):
    h = (b - a) / n
    integral = 0.0
    for i in range(n):
        x = a + i * h
        integral += f(x)
    return integral * h


def right_rectangles(f, a, b, n):
    h = (b - a) / n
    integral = 0.0
    for i in range(1, n + 1):
        x = a + i * h
        integral += f(x)
    return integral * h


def middle_rectangles(f, a, b, n):
    h = (b - a) / n
    integral = 0.0
    for i in range(n):
        x = a + (i + 0.5) * h
        integral += f(x)
    return integral * h


def trapezoid(f, a, b, n):
    h = (b - a) / n
    integral = (f(a) + f(b)) / 2
    for i in range(1, n):
        x = a + i * h # Текущая точка на оси X
        integral += f(x)
    return integral * h # Умножаем сумму на шаг (получаем площадь всех трапеций)


def simpson(f, a, b, n):
    if n % 2 != 0:
        n += 1  # Метод Симпсона требует четное число интервалов
    h = (b - a) / n
    integral = f(a) + f(b)
    for i in range(1, n):
        x = a + i * h
        if i % 2 == 0: # Если индекс чётный
            integral += 2 * f(x) # Учитываем с коэффициентом 2
        else: # Если индекс нечётный
            integral += 4 * f(x) # Учитываем с коэффициентом 4
    return integral * h / 3 # Умножаем на (h/3) по формуле Симпсона


def calculate_integral():
    # Выбор функции
    print("Выберите функцию для интегрирования:")
    print("1. x^2")
    print("2. sin(x)")
    print("3. e^x")
    print("4. 1/x")
    print("5. sqrt(x)")

    choice = int(input("Введите номер функции (1-5): "))

    if choice == 1:
        f = lambda x: x ** 2
        func_name = "x^2"
        a = float(input("Введите нижний предел интегрирования a: "))
        b = float(input("Введите верхний предел интегрирования b: "))
        if a >= b:
            print("Ошибка: верхний предел должен быть больше нижнего. Попробуйте снова.")
            return
    elif choice == 2:
        f = lambda x: math.sin(x)
        func_name = "sin(x)"
        a = float(input("Введите нижний предел интегрирования a: "))
        b = float(input("Введите верхний предел интегрирования b: "))
        if a >= b:
            print("Ошибка: верхний предел должен быть больше нижнего. Попробуйте снова.")
            return
    elif choice == 3:
        f = lambda x: math.exp(x)
        func_name = "e^x"
        a = float(input("Введите нижний предел интегрирования a: "))
        b = float(input("Введите верхний предел интегрирования b: "))
        if a >= b:
            print("Ошибка: верхний предел должен быть больше нижнего. Попробуйте снова.")
            return
    elif choice == 4:
        f = lambda x: 1 / x
        func_name = "1/x"
        a = float(input("Введите нижний предел интегрирования a: "))
        b = float(input("Введите верхний предел интегрирования b: "))
        if (a <= 0 or b <= 0):
            print("Ошибка: для функции 1/x пределы должны быть положительными. Попробуйте снова.")
            return
        elif (a >= b):
            print("Ошибка: верхний предел должен быть больше нижнего. Попробуйте снова.")
            return
    elif choice == 5:
        f = lambda x: math.sqrt(x)
        func_name = "sqrt(x)"
        a = float(input("Введите нижний предел интегрирования a: "))
        b = float(input("Введите верхний предел интегрирования b: "))
        if (a < 0 or b < 0):
            print("Ошибка: для функции sqrt(x) пределы не могут быть отрицательными. Попробуйте снова.")
            return
        elif a >= b:
            print("Ошибка: верхний предел должен быть больше нижнего. Попробуйте снова.")
            return
    else:
        print("Неверный выбор функции")
        return

    # Ввод пределов интегрирования


    # Ввод точности
    epsilon = float(input("Введите требуемую точность (например, 0.001): "))

    # Выбор метода интегрирования
    print("\nВыберите метод интегрирования:")
    print("1. Левые прямоугольники")
    print("2. Правые прямоугольники")
    print("3. Средние прямоугольники")
    print("4. Метод трапеций")
    print("5. Метод Симпсона")
    method_choice = int(input("Введите номер метода (1-5): "))

    methods = {
        1: ("Левые прямоугольники", left_rectangles),
        2: ("Правые прямоугольники", right_rectangles),
        3: ("Средние прямоугольники", middle_rectangles),
        4: ("Метод трапеций", trapezoid),
        5: ("Метод Симпсона", simpson)
    }

    if method_choice not in methods:
        print("Неверный выбор метода")
        return

    method_name, method_func = methods[method_choice]

    # Начальное число разбиений
    n = 4

    print("\nРезультаты вычислений:")
    print("Функция: ", func_name)
    print("Интервал: [", a, ", ", b, "]")
    print("Метод: ", method_name)
    print("Точность: ", epsilon)
    print("\nИтерационный процесс:")

    prev_integral = 0

    while True:
        # Вычисляем интеграл выбранным методом
        integral = method_func(f, a, b, n)

        # Выводим информацию о текущей итерации
        print(f"\nРазбиение n = {n}")
        print(f"{method_name}: {integral:.6f}")

        # Проверяем точность
        if n > 4:
            error = abs(integral - prev_integral)
            print(f"Текущая погрешность: {error:.6f}")

            if error < epsilon:
                break

        # Увеличиваем число разбиений
        n *= 2
        prev_integral = integral

    # Вывод итоговых результатов
    print("\nИтоговые результаты:")
    print(f"Достигнутая точность: {error:.6f}")
    print(f"Число разбиений для достижения точности: {n}")
    print(f"Значение интеграла методом {method_name}: {integral:.6f}")

    # Вывод таблицы значений для последнего n
    print("\nТаблица значений для последнего разбиения:")
    h = (b - a) / n
    if method_choice == 3:  # Для средних прямоугольников
        print("i\tx_i\t\ty_i\t\tx_{i-1/2}\t\ty_{i-1/2}")
        for i in range(n + 1):
            x_i = a + i * h
            y_i = f(x_i)

            if i > 0:
                x_mid = a + (i - 0.5) * h
                y_mid = f(x_mid)
            else:
                x_mid = ""
                y_mid = ""

            print(f"{i}\t{x_i:.6f}\t{y_i:.6f}\t{x_mid if i > 0 else '':<12}\t{y_mid if i > 0 else '':<12}")
    elif method_choice == 5:  # Для метода Симпсона
        print("i\tx_i\t\ty_i\tКоэффициент")
        for i in range(n + 1):
            x_i = a + i * h
            y_i = f(x_i)
            if i == 0 or i == n:
                coeff = 1
            elif i % 2 == 1:
                coeff = 4
            else:
                coeff = 2
            print(f"{i}\t{x_i:.6f}\t{y_i:.6f}\t{coeff}")
    else:  # Для других методов
        print("i\tx_i\t\ty_i")
        for i in range(n + 1):
            x_i = a + i * h
            y_i = f(x_i)
            print(f"{i}\t{x_i:.6f}\t{y_i:.6f}")


import math

if __name__ == "__main__":
    calculate_integral()