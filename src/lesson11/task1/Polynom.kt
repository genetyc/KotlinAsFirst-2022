@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Класс "полином с вещественными коэффициентами".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 16.
 * Объект класса -- полином от одной переменной (x) вида 7x^4+3x^3-6x^2+x-8.
 * Количество слагаемых неограничено.
 *
 * Полиномы можно складывать -- (x^2+3x+2) + (x^3-2x^2-x+4) = x^3-x^2+2x+6,
 * вычитать -- (x^3-2x^2-x+4) - (x^2+3x+2) = x^3-3x^2-4x+2,
 * умножать -- (x^2+3x+2) * (x^3-2x^2-x+4) = x^5+x^4-5x^3-3x^2+10x+8,
 * делить с остатком -- (x^3-2x^2-x+4) / (x^2+3x+2) = x-5, остаток 12x+16
 * вычислять значение при заданном x: при x=5 (x^2+3x+2) = 42.
 *
 * В конструктор полинома передаются его коэффициенты, начиная со старшего.
 * Нули в середине и в конце пропускаться не должны, например: x^3+2x+1 --> Polynom(1.0, 2.0, 0.0, 1.0)
 * Старшие коэффициенты, равные нулю, игнорировать, например Polynom(0.0, 0.0, 5.0, 3.0) соответствует 5x+3
 */
class Polynom(vararg coeffs: Double) {
    private val coeffs = coeffs.reversed().toMutableList()

    private val noNilsCoeffs = coeffs.dropWhile { it == 0.0 }

    private fun dropNils(): Polynom = Polynom(*coeffs.reversed().dropWhile { it == 0.0 }.toDoubleArray())

    private operator fun get(coeff: Int): Double = coeffs[coeff]

    private fun toNil(): Polynom {
        if (this.coeffs.count { it == 0.0 } == this.coeffs.size) return Polynom(0.0)
        return this
    }

    private fun degreeUp(n: Int): Polynom {
        val newCoeffs = coeffs.toDoubleArray().copyOf().toMutableList()
        for (i in 0 until n) newCoeffs.add(0, 0.0)
        return Polynom(*newCoeffs.reversed().toDoubleArray())
    }

    /**
     * Геттер: вернуть значение коэффициента при x^i
     */
    fun coeff(i: Int): Double = coeffs.getOrElse(i) { throw IndexOutOfBoundsException() }

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double {
        var sum = 0.0
        coeffs.forEachIndexed { power, modificator -> sum += (x.pow(power.toDouble()) * modificator) }
        return sum
    }

    /**
     * Степень (максимальная степень x при ненулевом слагаемом, например 2 для x^2+x+1).
     *
     * Степень полинома с нулевыми коэффициентами считать равной 0.
     * Слагаемые с нулевыми коэффициентами игнорировать, т.е.
     * степень 0x^2+0x+2 также равна 0.
     */
    fun degree(): Int {
        val found = coeffs.indexOfLast { it != 0.0 }
        return if (found != -1) found else 0
    }

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {
        val newCoeffs = coeffs.toDoubleArray().copyOf().toMutableList()
        for (coeff in other.coeffs.indices) {
            if (coeff < newCoeffs.size) {
                newCoeffs[coeff] += other.coeffs[coeff]
            } else newCoeffs.add(other.coeffs[coeff])
        }
        return Polynom(*newCoeffs.reversed().toDoubleArray()).toNil()
    }

    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom {
        coeffs.forEachIndexed { index, number -> coeffs[index] = -number }
        return Polynom(*coeffs.reversed().toDoubleArray())
    }

    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom {
        val newCoeffs = coeffs.toDoubleArray().copyOf().toMutableList()
        for (coeff in other.coeffs.indices) {
            if (coeff < newCoeffs.size) {
                newCoeffs[coeff] -= other.coeffs[coeff]
            } else newCoeffs.add(-other.coeffs[coeff])
        }
        return Polynom(*newCoeffs.reversed().toDoubleArray()).toNil()
    }

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        val product = MutableList(coeffs.size + other.coeffs.size - 1) { 0.0 }
        for (coeff in coeffs.indices) {
            for (anotherCoeff in other.coeffs.indices) {
                product[coeff + anotherCoeff] += coeffs[coeff] * other.coeffs[anotherCoeff]
            }
        }
        return Polynom(*product.reversed().toDoubleArray()).toNil()
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom {
        var tempCoeffs = coeffs
        var tempPolynom = Polynom(*tempCoeffs.reversed().toDoubleArray())
        val divisor = other.coeffs.dropLastWhile { it == 0.0 }
        val newCoeffs = MutableList(tempCoeffs.size - divisor.size + 1) { 0.0 }
        while (tempCoeffs.size >= divisor.size) {
            val degree = tempCoeffs.size - divisor.size
            val modif = tempCoeffs.last() / divisor.last()
            val denomPolynom = Polynom(modif).degreeUp(degree) * Polynom(*divisor.reversed().toDoubleArray())
            newCoeffs[degree] = modif
            tempPolynom = (tempPolynom - denomPolynom).dropNils()
            tempCoeffs = tempPolynom.coeffs
        }
        return Polynom(*newCoeffs.reversed().toDoubleArray())
    }

    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom = if (this.coeffs.size < other.coeffs.size) this
    else this - other * (this / other)

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Polynom && this.noNilsCoeffs == other.noNilsCoeffs

    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int = coeffs.hashCode()
}
