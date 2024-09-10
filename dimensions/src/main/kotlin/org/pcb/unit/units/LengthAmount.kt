package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class LengthAmount<L : Unit<Length>>(
        amount: Number,
        unit: L)
    : Amount<Length>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Length::class,
                         units = listOf(unit to 1)))
{
    val length = unit

    // region -> Length

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LengthAmount(amount = amount * that.amount * that.unit.scale,
                     unit = length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LengthAmount(amount = amount / that.amount / that.unit.scale,
                     unit = length)

    @JvmName("plusLength")
    operator fun <L : Unit<Length>> plus(that: LengthAmount<L>) =
        LengthAmount(amount = amount + that.amount * convert(that.length to length),
                     unit = length)

    operator fun plus(that: LengthAmount<L>) =
        LengthAmount(amount = amount + that.amount,
                     unit = length)

    @JvmName("minusLength")
    operator fun <L : Unit<Length>> minus(that: LengthAmount<L>) =
        LengthAmount(amount = amount - that.amount * convert(that.length to length),
                     unit = length)

    operator fun minus(that: LengthAmount<L>) =
        LengthAmount(amount = amount - that.amount,
                     unit = length)

    // endregion

    // region -> Natural

    @JvmName("divLength")
    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        ScalarAmount(amount = amount / that.amount / convert(that.length to length),
                     unit = NUMERIC)

    operator fun div(that: LengthAmount<L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Speed

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = length to that.time)

    // endregion

    // region -> Area

    @JvmName("timesLength")
    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        AreaAmount(amount = amount * that.amount * convert(that.length to length),
                   unit = length)

    operator fun times(that: LengthAmount<L>) =
        AreaAmount(amount = amount * that.amount,
                   unit = length)

    // endregion

    // region -> Volume

    @JvmName("timesArea")
    operator fun <L : Unit<Length>> times(that: AreaAmount<L>) =
        VolumeAmount(amount = amount * that.amount * convert(that.length to length,
                                                             that.length to length),
                     unit = length)

    operator fun times(that: AreaAmount<L>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = length)

    // endregion

    // region -> Energy

    @JvmName("timesForce")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: ForceAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.length to length),
                     unit = U2(that.mass, length) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, length) to that.time)

    // endregion
}