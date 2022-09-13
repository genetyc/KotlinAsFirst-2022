@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt
import kotlin.math.abs

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var numb = abs(n)
    if (n == 0) return 1 else
        while (numb >= 1) {
            count++
            numb /= 10
        }
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fCurrent = 1
    var fPrev: Int
    var sum = 2
    for (i in 4..n) {
        fPrev = fCurrent
        fCurrent = sum
        sum = fPrev + fCurrent
    }
    return when (n) {
        1 -> 1
        2 -> 1
        else -> sum
    }
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            return i
        }
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    val nasd = mutableListOf<Int>()
    for (i in 1..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) nasd.add(i)
    }
    return when (nasd.size) {
        1 -> 1
        else -> n / nasd[1]
    }
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var numb = x
    return when (x) {
        1 -> 0
        else -> {
            while (numb != 1) {
                if (numb % 2 == 0) numb /= 2 else numb = numb * 3 + 1
                count++
            }
            return count
        }
    }
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    fun gcd(a: Int, b: Int): Int =
        when {
            a == b || b == 0 -> a
            a == 0 -> b
            a > b -> gcd(a % b, b)
            else -> gcd(a, b % a)
        }
    return (m * n) / gcd(m, n)
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = lcm(m, n) == m * n

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    fun pow(x: Int, n: Int): Int {
        var xpowd = 1
        for (i in 1..n) {
            xpowd *= x
        }
        return xpowd
    }

    var pod = n
    val nasd = mutableListOf<Int>()
    while (pod > 0) {
        nasd.add(pod % 10)
        pod /= 10
    }
    var ans = 0
    for (i in 0 until nasd.size) {
        ans += pow(10, i) * nasd.reversed()[i]
    }
    return ans
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var pod = n
    val nasd = mutableListOf<Int>()
    while (pod > 0) {
        nasd.add(pod % 10)
        pod /= 10
    }
    for (i in 0 until nasd.size) {
        if (nasd[i] != nasd[nasd.size - i - 1]) {
            return false
        }
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var pod = n
    val nasd = mutableListOf<Int>()
    while (pod > 0) {
        nasd.add(pod % 10)
        pod /= 10
    }
    val ans = mutableSetOf<Int>()
    for (i in nasd) {
        ans.add(i)
    }
    return ans.size != 1 && n != 0
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var count = 1
    var sin = x % (2 * Math.PI)
    val sinc = sin
    var asd = sin
    while (abs(asd) >= eps) {
        asd = -asd * sinc * sinc / ((count * 2 + 1) * (count * 2)).toDouble()
        count += 1
        sin += asd
    }
    return sin
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val constX = x % (2 * Math.PI)
    var count = 1
    var cos = 1.0
    var eq = 1.0
    while (abs(eq) >= eps) {
        eq = -eq * constX * constX / ((count * 2 - 1) * (count * 2)).toDouble()
        count += 1
        cos += eq
    }
    return cos
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    val listSq: MutableList<Int> = mutableListOf()
    val list2: MutableList<String> = mutableListOf()
    for (i in 1..n + 1) {
        listSq.add(i * i)
    }
    for (i in listSq) {
        for (j in i.toString()) {
            list2.add(j.toString())
        }
    }
    return list2[n - 1].toInt()
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var fCurrent = 1
    var fPrev: Int
    var sum = 2
    val lis: MutableList<Int> = mutableListOf(1, 1, 2)
    val lis2: MutableList<String> = mutableListOf()
    for (i in 4..n) {
        fPrev = fCurrent
        fCurrent = sum
        sum = fPrev + fCurrent
        lis += sum
    }
    for (i in lis) {
        for (j in i.toString()) {
            lis2.add(j.toString())
        }
    }
    return (lis2[n - 1]).toInt()
}