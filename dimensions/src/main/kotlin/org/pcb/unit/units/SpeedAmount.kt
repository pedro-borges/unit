package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Speed
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.ONE

open class SpeedAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Speed>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Speed::class,
                        units = listOf(unit.major to 1,
                                       unit.minor to -1)))
{
    val length = unit.major
    val time = unit.minor

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount * that.amount * that.unit.scale,
                    unit = Pair(length, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount / that.amount / that.unit.scale,
                    unit = Pair(length, time))

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount
                             * convert(that.length to length)
                             / convert(that.time to time),
                    unit = Pair(length, time))

    @JvmName("plusSpeed")
    operator fun plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount,
                    unit = Pair(length, time))

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount
                             * convert(that.length to length)
                             / convert(that.time to time),
                    unit = Pair(length, time))

    @JvmName("minusSpeed")
    operator fun minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount,
                    unit = Pair(length, time))

    // endregion

    // region -> Natural
    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        amount / that.amount / convert(that.length to length) * convert(that.time to time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        amount / that.amount

    // endregion

    // region -> Length

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = length)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount,
                     unit = length)

    // endregion

    // region -> Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.time to time),
                           unit = Pair(length, time))

    // endregion

    // region -> Frequency

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.length to length),
                        unit = Pair(ONE, time))

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = Pair(ONE, time))

    // endregion

    // region -> Energy

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: MomentumAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.length to length)
                              / convert(that.time to time),
                     unit = Pair(T2(that.mass, length),
                                 time))

    @JvmName("timesMomentum")
    operator fun <M : Unit<Mass>> times(that: MomentumAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = Pair(T2(that.mass, length),
                                 time))

    // endregion
}