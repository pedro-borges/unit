package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Speed
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.NaturalAmount.ONE

open class SpeedAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<Speed>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Speed::class,
                        units = listOf(unit.first to 1,
                                       unit.second to -1)))
{
    val length = unit.first
    val time = unit.second

    // region Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount * that.amount * that.unit.scale,
                    unit = length / time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount / that.amount / that.unit.scale,
                    unit = length / time)

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount
                             * convert(that.length to length)
                             / convert(that.time to time),
                    unit = length / time)

    @JvmName("plusSpeed")
    operator fun plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount,
                    unit = length / time)

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount
                             * convert(that.length to length)
                             / convert(that.time to time),
                    unit = length / time)

    @JvmName("minusSpeed")
    operator fun minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount,
                    unit = length / time)

    // endregion

    // region Scalar
    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.length to length)
                               * convert(that.time to time))

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Length

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = length)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount,
                     unit = length)

    // endregion

    // region Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.time to time),
                           unit = length / (time * time))

    // endregion

    // region Frequency

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.length to length),
                        unit = ONE / time)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = ONE / time)

    // endregion

    // region Energy

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: MomentumAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.length to length)
                              / convert(that.time to time),
                     unit = (that.mass * length * length) / (time * time))

    @JvmName("timesMomentum")
    operator fun <M : Unit<Mass>> times(that: MomentumAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = (that.mass * length * length) / (time * time))

    // endregion
}