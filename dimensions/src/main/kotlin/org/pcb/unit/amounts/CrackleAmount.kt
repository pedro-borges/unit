package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Crackle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class CrackleAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Crackle>(amount = amount.toBigDecimal(),
                      unit = CompositeUnit(
                          domain = Crackle::class,
                          units = mapOf(unit.major to 1,
                                        unit.minor to -5)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Crackle>) =
            CrackleAmount(amount = amount * unit.scale,
                          unit = METER to SECOND)
    }

    // region -> Crackle

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CrackleAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                      unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CrackleAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                      unit = distance to time)

    @JvmName("plusCrackle")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: CrackleAmount<L, T>) =
        CrackleAmount(amount = amount + that.amount
                               * convert(that.distance to distance)
                               / convert(that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.time to time),
                      unit = distance to time)

    operator fun plus(that: CrackleAmount<L, T>) =
        CrackleAmount(amount = amount + that.amount,
                      unit = distance to time)

    @JvmName("minusCrackle")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: CrackleAmount<L, T>) =
        CrackleAmount(amount = amount - that.amount
                               * convert(that.distance to distance)
                               / convert(that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.time to time,
                                         that.time to time),
                      unit = distance to time)

    operator fun minus(that: CrackleAmount<L, T>) =
        CrackleAmount(amount = amount - that.amount,
                      unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divCrackle")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: CrackleAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: CrackleAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Frequency

    @JvmName("divSnap")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SnapAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time,
                                           that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: SnapAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Pop

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        PopAmount(amount = amount * that.amount
                           / convert(that.time to time),
                  unit = distance to time)

    operator fun times(that: FrequencyAmount<T>) =
        PopAmount(amount = amount * that.amount,
                  unit = distance to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PopAmount(amount = amount / that.amount
                           / convert(that.time to time),
                  unit = distance to time)

    operator fun div(that: TimeAmount<T>) =
        PopAmount(amount = amount / that.amount,
                  unit = distance to time)

    // endregion

    // region -> Snap

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        SnapAmount(amount = amount / that.amount
                            * convert(that.time to time),
                   unit = distance to time)

    operator fun div(that: FrequencyAmount<T>) =
        SnapAmount(amount = amount / that.amount,
                   unit = distance to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SnapAmount(amount = amount * that.amount
                            * convert(that.time to time),
                   unit = distance to time)

    operator fun times(that: TimeAmount<T>) =
        SnapAmount(amount = amount * that.amount,
                   unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divPop")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PopAmount<L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: PopAmount<L, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
