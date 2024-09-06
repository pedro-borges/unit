package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Pace
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class PaceAmount<T : Unit<Time>, L : Unit<Length>>(
        amount: Number,
        unit: Pair<T, L>)
    : Amount<Pace>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Pace::class,
                       units = listOf(unit.major to 1,
                                      unit.minor to -1)))
{
    val time = unit.major
    val length = unit.minor

    // region -> Pace

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PaceAmount(amount = amount * that.amount * that.unit.scale,
                   unit = time to length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PaceAmount(amount = amount / that.amount / that.unit.scale,
                   unit = time to length)

    operator fun <T : Unit<Time>, L : Unit<Length>> plus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount + that.amount
                            * convert(that.time to time)
                            / convert(that.length to length),
                   unit = time to length)

    @JvmName("plusPace")
    operator fun plus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount + that.amount,
                   unit = time to length)

    operator fun <T : Unit<Time>, L : Unit<Length>> minus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount - that.amount
                            * convert(that.time to time)
                            / convert(that.length to length),
                   unit = time to length)

    @JvmName("minusPace")
    operator fun minus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount - that.amount,
                   unit = time to length)

    // endregion

    // region -> Natural

    operator fun <T : Unit<Time>, L : Unit<Length>> div(that: PaceAmount<T, L>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.time to time)
                              / convert(that.length to length),
                     unit = NUMERIC)

    @JvmName("divPace")
    operator fun div(that: PaceAmount<T, L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.time to time)
                              * convert(that.length to length),
                     unit = NUMERIC)

    @JvmName("timesSpeed")
    operator fun times(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Time

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        TimeAmount(amount = amount * that.amount * convert(that.length to length),
                   unit = time)

    @JvmName("timesLength")
    operator fun times(that: LengthAmount<L>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion
}