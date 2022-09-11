@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt

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
    var numb = n
    if (n.toString().length == 1) return 1 else {
        while (numb > 0) {
            count++
            numb /= 10
        }
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
    return when {
        n == 1 -> 1
        n == 2 -> 1
        else -> sum
    }
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var div: Int = n
    for (i in 2..n / 2 + 1) {
        if (n % i == 0) {
            if (i < div) div = i else continue
        }
    }
    return div
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divis: Int = -1
    for (i in 1..(n / 2)) {
        if (n % i == 0) divis = maxOf(i, divis)
    }
    return divis
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
    if (x == 1) return 0 else if (x == 2) return 1 else {
        while (numb != 1) {
            if (numb % 2 == 0) numb /= 2 else numb = numb * 3 + 1
            count++
        }
    }
    return count
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val ma = minOf(m, n)
    var nok = n * m + 1
    for (i in ma..m * n) {
        if (i % m == 0 && i % n == 0) nok = minOf(nok, i) else continue
    }
    return nok
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val l1: MutableList<Int> = mutableListOf()
    val l2: MutableList<Int> = mutableListOf()
    for (i in 1..n) {
        if (n % i == 0) l1.add(i) else continue
    }
    for (j in 2..m) {
        if (m % j == 0) l2.add(j) else continue
    }
    val se: MutableSet<Int> = mutableSetOf()
    for (i in l1) {
        se.add(i)
    }
    for (j in l2) {
        se.add(j)
    }
    return (se.size) == (l1.size + l2.size)
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var pod = n
    var slovo = ""
    val nasd: MutableList<Int> = mutableListOf()
    val len = n.toString().length
    for (i in 1..len) {
        nasd.add(pod % 10)
        pod /= 10
    }
    for (i in nasd) {
        slovo += i
    }
    return slovo.toInt()
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
    var slovo = ""
    val nasd = mutableListOf<String>()
    val len = n.toString().length
    for (i in 1..len) {
        nasd.add(((pod % 10)).toString())
        pod /= 10
    }
    for (i in nasd) {
        slovo += i
    }
    return slovo == n.toString()
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
    val numb = n.toString()
    val fi = numb[0]
    for (i in numb) {
        if (i == fi) continue else return true
    }
    return false
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
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

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
