@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.fold(0.0) { sum, element -> sum + element * element })

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return when {
        (list.isEmpty()) -> 0.0
        else -> (list.fold(0.0) { sum, element -> sum + element }) / list.size.toDouble()
    }
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val sr = (list.fold(0.0) { sum, element -> sum + element }) / list.size.toDouble()
    for (i in 0 until list.size) {
        list[i] -= sr
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var su = 0
    for (i in 0 until a.size) {
        su += a[i] * b[i]
    }
    return su
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var su = 0
    fun pow(x: Int, n: Int): Int {
        var xpowd = 1
        for (i in 1..n) {
            xpowd *= x
        }
        return xpowd
    }
    return when {
        p.isEmpty() -> 0
        (p.size == 1) -> p[0]
        else -> {
            for (i in 1..p.size) {
                su += p[i - 1] * pow(x, i - 1)
            }
            return su
        }
    }
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] = list[i] + list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val lis2: MutableList<Int> = mutableListOf()
    var nn = n
    fun isPrime(n: Int) = n>= 2 && (2..n/2).all {n % it != 0}
    return when {
        isPrime(n) -> listOf(n)
        else -> {
            for (i in 2..n / 2 + 1) {
                while (nn % i == 0) {
                    lis2.add(i)
                    nn /= i
                }
            }
            lis2
        }
    }
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val lis2: MutableList<Int> = mutableListOf()
    var nn = n
    fun isPrime(n: Int) = n >= 2 && (2..n / 2).all { n % it != 0 }
    return if (isPrime(n)) n.toString() else {
        for (i in 2..n / 2 + 1) {
            while (nn % i == 0) {
                lis2.add(i)
                nn /= i
            }
        }
        lis2.sorted().joinToString(separator = "*")
    }
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list: MutableList<Int> = mutableListOf()
    var xx = n
    var last: Int = if (n < base) n else -1
    while (xx >= base) {
        if (xx / base < base) last = xx / base
        list.add(xx % base)
        xx /= base
    }
    list.add(last)
    return list.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var list: MutableList<Int> = mutableListOf()
    var xx = n
    var last: Int = if (n < base) n else -1
    while (xx >= base) {
        if (xx / base < base) last = xx / base
        list.add(xx % base)
        xx /= base
    }
    list.add(last)
    list = list.reversed() as MutableList<Int>
    val lis: MutableList<Any> = mutableListOf()
    for (i in 0 until list.size) {
        if (list[i] < 10) lis.add(list[i]) else lis.add((list[i] + 87.hashCode()).toChar())
    }
    return lis.joinToString(separator = "")
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    fun pow(x: Int, n: Int): Int {
        var xpowd = 1
        for (i in 1..n) {
            xpowd *= x
        }
        return xpowd
    }

    val list2 = digits.reversed()
    var ss = 0
    for (i in 0 until list2.size) {
        ss += (list2[i] * pow(base, i))
    }
    return ss
}


/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var ss = 0
    var list2: MutableList<Int> = mutableListOf()
    fun pow(x: Int, n: Int): Int {
        var xpowd = 1
        for (i in 1..n) {
            xpowd *= x
        }
        return xpowd
    }
    for (i in 0 until str.toList().size) {
        if ((str.toList()[i].hashCode() - 87) < 0) list2.add(str.toList()[i].hashCode() - 48) else list2.add(str.toList()[i].hashCode() - 87)
    }
    list2 = list2.reversed() as MutableList<Int>
    for (i in 0 until list2.size) {
        ss += list2[i] * pow(base, i)
    }
    return ss
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val lis: MutableList<Int> = mutableListOf()
    for (i in n.toString()) {
        lis.add((i.toString()).toInt())
    }
    fun ones(n: Int): String {
        return when {
            n == 1 -> "один"
            n == 2 -> "два"
            n == 3 -> "три"
            n == 4 -> "четыре"
            n == 5 -> "пять"
            n == 6 -> "шесть"
            n == 7 -> "семь"
            n == 8 -> "восемь"
            n == 9 -> "девять"
            else -> ""
        }
    }

    fun onesTh(n: Int): String {
        return when {
            n == 1 -> "одна тысяча"
            n == 2 -> "две тысячи"
            n == 3 -> "три тысячи"
            n == 4 -> "четыре тысячи"
            n == 5 -> "пять тысяч"
            n == 6 -> "шесть тысяч"
            n == 7 -> "семь тысяч"
            n == 8 -> "восемь тысяч"
            n == 9 -> "девять тысяч"
            else -> "тысяч"
        }
    }

    fun hund(n: Int): String {
        return when {
            n == 1 -> "сто"
            n == 2 -> "двести"
            n == 3 -> "триста"
            n == 4 -> "четыреста"
            n == 5 -> "пятьсот"
            n == 6 -> "шестьсот"
            n == 7 -> "семьсот"
            n == 8 -> "восемьсот"
            n == 9 -> "девятьсот"
            else -> ""
        }
    }

    fun tens(n: Int): String {
        return when {
            n == 2 -> "двадцать"
            n == 3 -> "тридцать"
            n == 4 -> "сорок"
            n == 5 -> "пятьдесят"
            n == 6 -> "шестьдесят"
            n == 7 -> "семьдесят"
            n == 8 -> "восемьдесят"
            n == 9 -> "девяносто"
            else -> ""
        }
    }

    fun tensOnes(n: Int): String {
        return when {
            n == 1 -> "десять"
            n == 2 -> "одиннадцать"
            n == 3 -> "двенадцать"
            n == 4 -> "тринадцать"
            n == 5 -> "четырнадцать"
            n == 6 -> "пятнадцать"
            n == 7 -> "шестнадцать"
            n == 8 -> "семнадцать"
            n == 9 -> "восемнадцать"
            n == 10 -> "девятнадцать"
            else -> "Error"
        }
    }

    fun tensOnesHunds(n: Int): String {
        return when {
            n == 1 -> "десять тысяч"
            n == 2 -> "одиннадцать тысяч"
            n == 3 -> "двенадцать тысяч"
            n == 4 -> "тринадцать тысяч"
            n == 5 -> "четырнадцать тысяч"
            n == 6 -> "пятнадцать тысяч"
            n == 7 -> "шестнадцать тысяч"
            n == 8 -> "семнадцать тысяч"
            n == 9 -> "восемнадцать тысяч"
            n == 10 -> "девятнадцать тысяч"
            else -> "Error"
        }
    }
    return when {
        (lis.size == 1) -> ones(lis[0])
        (lis.size == 2 && lis[0] == 1) -> tensOnes(lis[0] + lis[1]).trim().replace("   ", " ").replace("  ", " ")
        (lis.size == 2 && lis[0] != 1) -> "${tens(lis[0])} ${ones(lis[1])}".trim().replace("   ", " ")
            .replace("  ", " ")

        (lis.size == 3 && lis[1] == 1) -> "${hund(lis[0])} ${tensOnes(lis[1] + lis[2])}".trim().replace("   ", " ")
            .replace("  ", " ")

        (lis.size == 3 && lis[1] != 1) -> "${hund(lis[0])} ${tens(lis[1])} ${ones(lis[2])}".trim().replace("   ", " ")
            .replace("  ", " ")

        (lis.size == 4 && lis[2] == 1) -> "${onesTh(lis[0])} ${hund(lis[1])} ${tensOnes(lis[3] + lis[2])}".trim()
            .replace("   ", " ").replace("  ", " ")

        (lis.size == 4 && lis[2] != 1) -> "${onesTh(lis[0])} ${hund(lis[1])} ${tens(lis[2])} ${ones(lis[3])}".trim()
            .replace("   ", " ").replace("  ", " ")

        (lis.size == 5 && lis[0] == 1 && lis[3] == 1) -> "${tensOnesHunds(lis[0] + lis[1])} ${hund(lis[2])} ${
            tensOnes(
                lis[3] + lis[4]
            )
        }".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 5 && lis[0] != 1 && lis[3] == 1) -> "${tens(lis[0])} ${onesTh(lis[1])} ${hund(lis[2])} ${
            tensOnes(
                lis[3] + lis[4]
            )
        }".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 5 && lis[0] == 1 && lis[3] != 1) -> "${tensOnesHunds(lis[0] + lis[1])} ${hund(lis[2])} ${tens(lis[3])} ${
            ones(
                lis[4]
            )
        }".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 5 && lis[0] != 1 && lis[3] != 1) -> "${tens(lis[0])} ${onesTh(lis[1])} ${hund(lis[2])} ${tens(lis[3])} ${
            ones(
                lis[4]
            )
        }".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 6 && lis[1] == 1 && lis[4] == 1) -> "${hund(lis[0])} ${tensOnesHunds(lis[2] + lis[1])} ${hund(lis[3])} ${
            tensOnes(
                lis[5] + lis[4]
            )
        }".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 6 && lis[1] == 1 && lis[4] != 1) -> "${hund(lis[0])} ${tensOnesHunds(lis[2] + lis[1])} ${hund(lis[3])} ${
            tens(
                lis[4]
            )
        } ${ones(lis[5])}".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 6 && lis[1] != 1 && lis[4] == 1) -> "${hund(lis[0])} ${tens(lis[1])} ${onesTh(lis[2])} ${hund(lis[3])} ${
            tensOnes(
                lis[4] + lis[5]
            )
        }".trim().replace("   ", " ").replace("  ", " ")

        (lis.size == 6 && lis[1] != 1 && lis[4] != 1) -> "${hund(lis[0])} ${tens(lis[1])} ${onesTh(lis[2])} ${hund(lis[3])} ${
            tens(
                lis[4]
            )
        } ${ones(lis[5])}".trim().replace("   ", " ").replace("  ", " ")

        else -> "Error"
    }
}
