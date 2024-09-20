package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Jerk
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class JerkAmount<D : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<D, T>)
    : Amount<Jerk>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Jerk::class,
                       units = mapOf(unit.major to 1,
                                     unit.minor to -3)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Jerk>) =
            JerkAmount(amount = amount * unit.scale,
                       unit = METER to SECOND)
    }

    // region -> Jerk

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        JerkAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        JerkAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = distance to time)

    @JvmName("plusJerk")
    operator fun <D : Unit<Distance>, T : Unit<Time>> plus(that: JerkAmount<D, T>) =
        JerkAmount(amount = amount + that.amount
                            * convert(that.distance to distance)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance to time)

    operator fun plus(that: JerkAmount<D, T>) =
        JerkAmount(amount = amount + that.amount,
                   unit = distance to time)

    @JvmName("minusJerk")
    operator fun <D : Unit<Distance>, T : Unit<Time>> minus(that: JerkAmount<D, T>) =
        JerkAmount(amount = amount - that.amount
                            * convert(that.distance to distance)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance to time)

    operator fun minus(that: JerkAmount<D, T>) =
        JerkAmount(amount = amount - that.amount,
                   unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divJerk")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<D, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: JerkAmount<D, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        AccelerationAmount(amount = amount / that.amount
                                    * convert(that.time to time),
                           unit = distance to time)

    operator fun div(that: FrequencyAmount<T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        AccelerationAmount(amount = amount * that.amount
                                    * convert(that.time to time),
                           unit = distance to time)

    operator fun times(that: TimeAmount<T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to time)

    // endregion

    // region -> Frequency

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: AccelerationAmount<D, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Snap

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        SnapAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = distance to time)

    operator fun times(that: FrequencyAmount<T>) =
        SnapAmount(amount = amount * that.amount,
                   unit = distance to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        SnapAmount(amount = amount / that.amount
                            / convert(that.time to time),
                   unit = distance to time)

    operator fun div(that: TimeAmount<T>) =
        SnapAmount(amount = amount / that.amount,
                   unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divSnap")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SnapAmount<D, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: SnapAmount<D, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
