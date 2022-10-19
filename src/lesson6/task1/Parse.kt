@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import junit.framework.AssertionFailedError
import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.function.DoubleBinaryOperator

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun convertToWord(str: String): Int {
    return when (str) {
        "января" -> 1
        "февраля" -> 2
        "марта" -> 3
        "апреля" -> 4
        "мая" -> 5
        "июня" -> 6
        "июля" -> 7
        "августа" -> 8
        "сентября" -> 9
        "октября" -> 10
        "ноября" -> 11
        "декабря" -> 12
        else -> -1
    }
}

fun dateStrToDigit(str: String): String {
    val lis: List<String> = str.split(" ").map { it }
    if (lis.size != 3) return ""
    val lis2 = mutableListOf<String>()
    try {
        lis2.addAll(
            listOf(
                "%02d".format(lis[0].toInt()),
                "%02d".format(convertToWord(lis[1])), lis[2]
            )
        )
    } catch (e: NumberFormatException) {
        return ""
    }
    return when {
        lis2[0].toInt() > daysInMonth(lis2[1].toInt(), lis[2].toInt()) -> ""
        lis2[1].toInt() == -1 -> ""
        else -> lis2.joinToString(separator = ".")
    }
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun convertToNumb(str: String): Pair<String, List<Int>> {
    val lis: List<String> = str.split(".").map { it }
    if (lis.size != 3) return ("Error" to listOf(0))
    try {
        if (lis[0].toInt() > daysInMonth(lis[1].toInt(), lis[2].toInt()))
            return ("Error" to listOf(0))
    } catch (e: NumberFormatException) {
        return ("Error" to listOf(0))
    }
    return when (lis[1]) {
        "01" -> ("января" to lis.map { it.toInt() })
        "02" -> ("февраля" to lis.map { it.toInt() })
        "03" -> ("марта" to lis.map { it.toInt() })
        "04" -> ("апреля" to lis.map { it.toInt() })
        "05" -> ("мая" to lis.map { it.toInt() })
        "06" -> ("июня" to lis.map { it.toInt() })
        "07" -> ("июля" to lis.map { it.toInt() })
        "08" -> ("августа" to lis.map { it.toInt() })
        "09" -> ("сентября" to lis.map { it.toInt() })
        "10" -> ("октября" to lis.map { it.toInt() })
        "11" -> ("ноября" to lis.map { it.toInt() })
        "12" -> ("декабря" to lis.map { it.toInt() })
        else -> ("Error" to listOf(0))
    }
}

fun dateDigitToStr(digital: String): String {
    val (month, lis) = convertToNumb(digital)
    if (month != "Error") return ("${lis[0]} $month ${lis[2]}")
    return ""
}

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun spl(n: String): String = n.split(" ", "+", "(", ")", "-")
    .joinToString(separator = "")

fun chec(n: String): Boolean = when {
    "()" in n -> false
    n.isEmpty() -> false
    n.count { s -> s.toString() == "(" } != n.count { s ->
        s.toString() == ")"
    } -> false

    n.count { s -> s.toString() == "+" } > 1 -> false
    n.indexOfFirst { c -> c.toString() == "+" } > 0 -> false
    n.contains(Regex("""[^\d+)(\- ]""")) -> false
    else -> true
}

fun isPlusThere(n: String): Boolean = n[0].toString() == "+"
fun flattenPhoneNumber(phone: String): String {
    return if (!chec(phone)) "" else {
        if (isPlusThere(phone)) "+" + spl(phone) else spl(phone)
    }
}

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    return if (jumps.contains(Regex("""[^\d-% ]"""))) -1
    else {
        val numbers = mutableListOf(-1)
        val j = jumps.split(" ", "%", "-")
        for (i in j) {
            if (i.toIntOrNull() != null) numbers.add(i.toInt())
        }
        return numbers.max()
    }
}

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (jumps.contains(Regex("""[^0-9%\-+ ]"""))) return -1
    val spl = jumps.split("+").map { it.trim() }
    var max = -1
    val spl2 = spl.map { it.split(" ") }
    for (i in 0 until spl2.size - 1) {
        if (spl2[i].last().contains(Regex("""[0-9]"""))
            && spl2[i + 1].first().contains(Regex("""[0-9]"""))
            || spl2[i].last().contains(Regex("""[0-9]"""))
            && spl2[i + 1].first().contains(Regex(""""""))
        ) {
            max = maxOf(spl2[i].last().toInt(), max)
        }
    }
    return max
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val spl = expression.split(" ")
    if (spl.first().contains(Regex("""[-+]""")) || spl.last()
            .contains(Regex("""[-+]"""))
    ) throw IllegalArgumentException()
    for (i in spl) if (i.contains(Regex("""[0-9]"""))
        && i.contains(Regex("""[+-]"""))
    ) throw IllegalArgumentException()
    for (i in 1 until spl.size) {
        if (spl[i].contains(Regex("""[+-]""")) && spl[i - 1].contains
                (Regex("""[+-]""")) || spl[i].contains(Regex("""[0-9]"""))
            && spl[i - 1].contains(Regex("""[0-9]"""))
        ) throw IllegalArgumentException()
    }
    var count = spl[0].toInt()
    if (spl.size == 1) return spl[0].toInt() else {
        for (i in spl.indices) {
            if (spl[i].contains(Regex("""[0-9]"""))) continue else {
                when (spl[i]) {
                    "+" -> count += spl[i + 1].toInt()
                    else -> count -= spl[i + 1].toInt()
                }
            }
        }
    }
    return count
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val spl = str.split(" ").map { it.lowercase() }
    var count = 0
    if (spl.size == 1) return -1
    for (i in 0 until spl.size - 1) {
        if (spl[i] == spl[i + 1] && spl[i] != "") return count
        count += spl[i].length + 1
    }
    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String {
    val spl = description.split(" ", ";").filter { it != "" }
    val mp = mutableMapOf<String, Double>()
    for (i in 0 until spl.size - 1 step 2) {
        mp[spl[i]] = spl[i + 1].toDouble()
    }
    var mx = -1.0
    var ans = ""
    for ((i, k) in mp.entries) {
        if (k > mx) {
            mx = k; ans = i
        }
    }
    return ans
}

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */

fun transform(n: String): Int = when (n) {
    "I" -> 1
    "V" -> 5
    "X" -> 10
    "L" -> 50
    "C" -> 100
    "D" -> 500
    else -> 1000
}

fun fromRoman(roman: String): Int {
    if (roman.contains(Regex("""[^IVXLCDM]""")) || roman.isEmpty()) return -1
    var count = 0
    val lis: List<Int> = roman.map { transform(it.toString()) }
    for (i in 0 until lis.size - 1) {
        if (lis[i] < lis[i + 1]) {
            count -= lis[i]
        } else count += lis[i]
    }
    return count + lis.last()
}

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
