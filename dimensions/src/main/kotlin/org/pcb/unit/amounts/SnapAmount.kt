package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Snap
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SnapAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Snap>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Snap::class,
                       units = mapOf(unit.major to 1,
                                     unit.minor to -4)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Snap>) =
            SnapAmount(amount = amount * unit.scale,
                       unit = METER to SECOND)
    }

    // region -> Snap

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SnapAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SnapAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = distance to time)

    @JvmName("plusSnap")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: SnapAmount<L, T>) =
        SnapAmount(amount = amount + that.amount
                            * convert(that.distance to distance)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance to time)

    operator fun plus(that: SnapAmount<L, T>) =
        SnapAmount(amount = amount + that.amount,
                   unit = distance to time)

    @JvmName("minusSnap")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: SnapAmount<L, T>) =
        SnapAmount(amount = amount - that.amount
                            * convert(that.distance to distance)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance to time)

    operator fun minus(that: SnapAmount<L, T>) =
        SnapAmount(amount = amount - that.amount,
                   unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divSnap")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SnapAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SnapAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Crackle

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        CrackleAmount(amount = amount * that.amount
                               / convert(that.time to time),
                      unit = distance to time)

    operator fun times(that: FrequencyAmount<T>) =
        CrackleAmount(amount = amount * that.amount,
                      unit = distance to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        CrackleAmount(amount = amount / that.amount
                               / convert(that.time to time),
                      unit = distance to time)

    operator fun div(that: TimeAmount<T>) =
        CrackleAmount(amount = amount / that.amount,
                      unit = distance to time)

    // endregion

    // region -> Frequency

    @JvmName("divJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: JerkAmount<L, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Jerk

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        JerkAmount(amount = amount / that.amount
                            * convert(that.time to time),
                   unit = distance to time)

    operator fun div(that: FrequencyAmount<T>) =
        JerkAmount(amount = amount / that.amount,
                   unit = distance to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        JerkAmount(amount = amount * that.amount
                            * convert(that.time to time),
                   unit = distance to time)

    operator fun times(that: TimeAmount<T>) =
        JerkAmount(amount = amount * that.amount,
                   unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divCrackle")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: CrackleAmount<L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: CrackleAmount<L, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
