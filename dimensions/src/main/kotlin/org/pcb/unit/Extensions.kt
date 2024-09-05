package org.pcb.unit

import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import java.math.RoundingMode.HALF_EVEN
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricConductivityAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Numeric.NUMBER

fun String.toBigBigDecimal() =
    BigDecimal(this)

fun Number.toBigBigDecimal(): BigDecimal =
    when
    {
        this is BigDecimal -> this.setScale(33, HALF_EVEN)
        else               -> BigDecimal(toString()).setScale(33, HALF_EVEN)
    }

fun MutableMap<Unit<*>, Int>.putOrRemove(unit: Unit<*>, value: Int)
{
    if (value == 0) this.remove(unit)
    else this[unit] = value
}

fun BigDecimal.power(power: Int): BigDecimal
{
    var result = 1.toBigBigDecimal()
    for (a in 1..kotlin.math.abs(power))
    {
        if (power > 0) result = result.multiply(this, DECIMAL128)
        if (power < 0) result = result.divide(this, DECIMAL128)
    }
    return result
}

fun Collection<Pair<Unit<*>, Int>>.sumToMap(): Map<Unit<*>, Int>
{
    val result = HashMap<Unit<*>, Int>()

    forEach { result[it.first] = (result[it.first] ?: 0) + it.second }

    return result
}

fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
    units.fold(1.toBigBigDecimal()) { acc, unit ->
        acc * unit.first.scale / unit.second.scale
    }

typealias Fraction<A, B> = Pair<A, B>

operator fun Number.div(other: BigDecimal): BigDecimal = toBigBigDecimal().divide(other, 42, HALF_EVEN).stripTrailingZeros()
operator fun Number.times(other: BigDecimal): BigDecimal = toBigBigDecimal().multiply(other, DECIMAL128).stripTrailingZeros()
operator fun Number.plus(other: BigDecimal): BigDecimal = toBigBigDecimal().add(other).stripTrailingZeros()
operator fun Number.minus(other: BigDecimal): BigDecimal = toBigBigDecimal().subtract(other).stripTrailingZeros()

operator fun <B : Unit<*>> Number.div(second: B) = Fraction(NUMBER(this), second)
operator fun <B : Tuple<*, *>> Number.div(second: B) = Fraction(NUMBER(this), second)
operator fun <B : Triple<*, *, *>> Number.div(second: B) = Fraction(NUMBER(this), second)
operator fun <B : Quadruple<*, *, *, *>> Number.div(second: B) = Fraction(NUMBER(this), second)
operator fun <B : Quintuple<*, *, *, *, *>> Number.div(second: B) = Fraction(NUMBER(this), second)
operator fun <B : Sextuple<*, *, *, *, *, *>> Number.div(second: B) = Fraction(NUMBER(this), second)

val <A, B> Fraction<A, B>.major: A get() = first
val <A, B> Fraction<A, B>.minor get() = second

operator fun <S : Unit<Scalar>> Number.times(that: ScalarAmount<S>) =
    this * that.amount * that.unit.scale

operator fun <S : Unit<Scalar>> Number.div(that: ScalarAmount<S>) =
    this / that.amount / that.unit.scale

operator fun <S : Unit<Scalar>> Number.plus(that: ScalarAmount<S>) =
    this + that.amount * that.unit.scale

operator fun <S : Unit<Scalar>> Number.minus(that: ScalarAmount<S>) =
    this - that.amount * that.unit.scale

operator fun <T : Unit<Time>> Number.div(that: TimeAmount<T>) =
    FrequencyAmount(amount = this / that.amount,
                    unit = 1 / that.time)

operator fun <T : Unit<Time>> Number.div(that: FrequencyAmount<T>) =
    TimeAmount(amount = this * that.amount,
               unit = that.time)

operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> Number.div(that: ElectricConductivityAmount<T, EC, M, L>) =
    ElectricResistanceAmount(amount = this * that.amount,
                             unit = (that.mass * that.length * that.length) /
                                    (that.time * that.time * that.time * that.electricCurrent * that.electricCurrent))

operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> Number.div(that: ElectricResistanceAmount<M, L, T, EC>) =
    ElectricConductivityAmount(amount = this / that.amount,
                               unit = (that.time * that.time * that.time * that.electricCurrent * that.electricCurrent) /
                                      (that.mass * that.length * that.length))