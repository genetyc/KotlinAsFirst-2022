@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val numb = number.toString()
    return numb[0].code + numb[1].code == numb[2].code + numb[3].code
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    val diag1 = abs(x1 - x2)
    val diag2 = abs(y1 - y2)
    return diag1 == diag2 || x1 == x2 || y1 == y2
}


/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    return when {
        month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 -> 31
        month == 4 || month == 6 || month == 9 || month == 11 -> 30
        (month == 2 && year % 4 == 0 && year % 100 != 0) || (month == 2 && year % 4 == 0 && year % 400 == 0) -> 29
        else -> 28
    }
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    val hyp = sqrt(sqr(abs(x1 - x2)) + sqr(abs(y1 - y2)))
    return when {
        maxOf(r1, r2) <= hyp -> false
        hyp + r1 <= r2 -> true
        else -> false
    }
}

/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val sq_h = r * s
    val sum_b = a + b + c
    val min_b = minOf(a, b, c)
    val mid_b = sum_b - (maxOf(a, b, c) + minOf(a, b, c))
    val sq_b = min_b * mid_b
    return when {
        sq_b > sq_h -> false
        min_b <= minOf(r, s) && mid_b <= maxOf(r, s) -> true
        else -> false
    }
}
