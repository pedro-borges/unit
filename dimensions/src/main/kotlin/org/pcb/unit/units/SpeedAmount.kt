package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
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
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

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

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Speed>) =
            SpeedAmount(amount = amount.toBigBigDecimal() * unit.scale,
                        unit = METER to SECOND)
    }

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount * that.amount * that.unit.scale,
                    unit = length to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount / that.amount / that.unit.scale,
                    unit = length to time)

    @JvmName("plusSpeed")
    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount
                             * convert(that.length to length)
                             / convert(that.time to time),
                    unit = length to time)

    operator fun plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount,
                    unit = length to time)

    @JvmName("minusSpeed")
    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount
                             * convert(that.length to length)
                             / convert(that.time to time),
                    unit = length to time)

    operator fun minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount,
                    unit = length to time)

    // endregion

    // region -> Natural

    @JvmName("divSpeed")
    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.length to length)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Length

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = length)

    operator fun times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount,
                     unit = length)

    // endregion

    // region -> Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.time to time),
                           unit = length to time)

    // endregion

    // region -> Frequency

    @JvmName("divLength")
    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.length to length),
                        unit = NUMERIC to time)

    operator fun div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Energy

    @JvmName("timesMomentum")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: MomentumAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.length to length)
                              / convert(that.time to time),
                     unit = U2(that.mass, length) to time)

    operator fun <M : Unit<Mass>> times(that: MomentumAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, length) to time)

    // endregion

    // region -> Power

    @JvmName("timesForce")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ForceAmount<M, L, T>) =
        PowerAmount(amount = amount * that.amount
                              * convert(that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(that.mass, length) to time)

    operator fun <M : Unit<Mass>> times(that: ForceAmount<M, L, T>) =
        PowerAmount(amount = amount * that.amount,
                     unit = U2(that.mass, length) to time)

    // endregion
}