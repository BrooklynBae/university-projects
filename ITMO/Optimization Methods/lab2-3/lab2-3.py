#Метод деления отрезка пополам
def f(x):
    return (1/3) * x**3 - 5 * x + x * math.log(x)

def bisectionMethod(a, b, e):
    while (b - a) > 2 * e:
        x1 = (a + b - e) / 2
        x2 = (a + b + e) / 2

        y1 = f(x1)
        y2 = f(x2)

        if y1 > y2:
            a = x1
        else:
            b = x2

    xm = (a + b) / 2
    return xm
def main():
result = bisectionMethod(a, b, e)
print(f"Минимум функции находится в точке: {result}")
print(f"Значение функции в этой точке: {f(result)}")

#Метод золотого сечения
def goldenSectionMethod(a, b, e):
    golden_ratio = 0.618
    x1 = a + (1 - golden_ratio) * (b - a)
    x2 = a + golden_ratio * (b - a)

    f_x1 = f(x1)
    f_x2 = f(x2)

    while abs(b - a) > e:
        if f_x1 < f_x2:
            b = x2
            x2 = x1
            f_x2 = f_x1
            x1 = a + (1 - golden_ratio) * (b - a)
            f_x1 = f(x1)
        else:
            a = x1
            x1 = x2
            f_x1 = f_x2
            x2 = a + golden_ratio * (b - a)
            f_x2 = f(x2)

    return (a + b) / 2
def main():
result = goldenSectionMethod(a, b, e)
print(f"Минимум функции находится в точке: {result}")
print(f"Значение функции в этой точке: {f(result)}")

#Метод хорд
def chord_method(a, b, epsilon):
    # Проверяем, что f'(a) и f'(b) имеют разные знаки
    if f_derivative(a) * f_derivative(b) >= 0:
        raise ValueError("Производная на концах интервала должна иметь разные знаки.")

    while True:
        # Вычисляем точку пересечения хорды с осью OX
        x_hat = a - (f_derivative(a) * (a - b)) / (f_derivative(a) - f_derivative(b))

        # Вычисляем значение производной в новой точке
        f_derivative_x_hat = f_derivative(x_hat)

        # Проверяем условие завершения
        if abs(f_derivative_x_hat) <= epsilon:
            return x_hat  # Возвращаем найденную точку минимума

        # Обновляем интервал
        if f_derivative_x_hat > 0:
            b = x_hat
        else:
            a = x_hat

#Метод Ньютона
def newton_method(x0, epsilon):
    x_k = x0  # Начальное приближение
    while True:
        f_prime = f_derivative(x_k)  # Значение первой производной в точке x_k
        f_double_prime = f_second_derivative(x_k)  # Значение второй производной в точке x_k

        # Проверка на окончание итераций
        if abs(f_prime) <= epsilon:
            return x_k  # Возвращаем найденную точку минимума

        # Обновление приближения
        x_k = x_k - f_prime / f_double_prime
def main():
	x0 = (a + b) / 2
x_min = newton_method(x0, e)
print(f"Точка минимума: {x_min}")
print(f"Значение функции в точке минимума: {f(x_min)}")

#Метод квадратной аппроксимации
def quadratic_approximation(x1, delta_x, epsilon1=0.0001, epsilon2=0.0001, max_iter=100):
    x2 = x1 + delta_x
    f1, f2 = f(x1), f(x2)

    if f1 > f2:
        x3 = x1 + 2 * delta_x
    else:
        x3 = x1 - delta_x

    f3 = f(x3)

    for _ in range(max_iter):
        F_min = min(f1, f2, f3)
        x_min = x1 if f1 == F_min else (x2 if f2 == F_min else x3)

        denominator = (x2 - x3) * f1 + (x3 - x1) * f2 + (x1 - x2) * f3
        if denominator == 0:
            x1 = x_min
            x2 = x1 + delta_x
            f1, f2 = f(x1), f(x2)
            if f1 > f2:
                x3 = x1 + 2 * delta_x
            else:
                x3 = x1 - delta_x
            f3 = f(x3)
            continue

        x_bar = 0.5 * ((x2**2 - x3**2) * f1 + (x3**2 - x1**2) * f2 + (x1**2 - x2**2) * f3)
        x_bar /= denominator
        f_bar = f(x_bar)

        if abs((F_min - f_bar) / f_bar) < epsilon1 and abs((x_min - x_bar) / x_bar) < epsilon2:
            return x_bar

        if x_bar < x1 or x_bar > x3:
            x1 = x_bar
            x2 = x1 + delta_x
            f1, f2 = f(x1), f(x2)
            if f1 > f2:
                x3 = x1 + 2 * delta_x
            else:
                x3 = x1 - delta_x
            f3 = f(x3)
        else:
            if f_bar < F_min:
                x_min = x_bar
                F_min = f_bar

            if x_min < x2:
                x3 = x2
                x2 = x_min
            else:
                x1 = x2
                x2 = x_min

            f1, f2, f3 = f(x1), f(x2), f(x3)

    return x_bar
def main():
x1 = 1.5
delta_x = 0.1
min_x = quadratic_approximation(x1, delta_x)
print(f"Точка минимума: {min_x}")
print(f"Значение функции в точке минимума: {f(min_x)}")
