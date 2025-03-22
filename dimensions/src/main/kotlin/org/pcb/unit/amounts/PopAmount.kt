package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Pop
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PopAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Pop>(amount = amount.toBigDecimal(),
                  unit = CompositeUnit(
                      domain = Pop::class,
                      units = mapOf(unit.major to 1,
                                    unit.minor to -6)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Pop>) =
            PopAmount(amount = amount * unit.scale,
                      unit = METER to SECOND)
    }

    // region -> Convert

    fun convertTo(unit: Unit<Pop>) =
        PopAmount(amount = amount * convert(this.unit to unit),
                  unit = unit)

    // endregion

    // region -> Pop

    override operator fun times(that: Number) =
        PopAmount(amount = amount * that.toBigDecimal(),
                  unit = distance to time)

    override operator fun div(that: Number) =
        PopAmount(amount = amount / that.toBigDecimal(),
                  unit = distance to time)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PopAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                  unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PopAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                  unit = distance to time)

    @JvmName("plusPop")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: PopAmount<L, T>) =
        PopAmount(amount = amount + that.amount
                           * convert(that.distance to distance)
                           / convert(that.time to time,
                                     that.time to time,
                                     that.time to time,
                                     that.time to time,
                                     that.time to time,
                                     that.time to time),
                  unit = distance to time)

    operator fun plus(that: PopAmount<L, T>) =
        PopAmount(amount = amount + that.amount,
                  unit = distance to time)

    @JvmName("minusPop")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: PopAmount<L, T>) =
        PopAmount(amount = amount - that.amount
                           * convert(that.distance to distance)
                           / convert(that.time to time,
                                     that.time to time,
                                     that.time to time,
                                     that.time to time,
                                     that.time to time,
                                     that.time to time),
                  unit = distance to time)

    operator fun minus(that: PopAmount<L, T>) =
        PopAmount(amount = amount - that.amount,
                  unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divPop")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PopAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: PopAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Crackle

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        CrackleAmount(amount = amount / that.amount
                               * convert(that.time to time),
                      unit = distance to time)

    operator fun div(that: FrequencyAmount<T>) =
        CrackleAmount(amount = amount / that.amount,
                      unit = distance to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        CrackleAmount(amount = amount * that.amount
                               * convert(that.time to time),
                      unit = distance to time)

    operator fun times(that: TimeAmount<T>) =
        CrackleAmount(amount = amount * that.amount,
                      unit = distance to time)

    // endregion

    // region -> Frequency

    @JvmName("divCrackle")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: CrackleAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time,
                                           that.time to time,
                                           that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: CrackleAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion
}
