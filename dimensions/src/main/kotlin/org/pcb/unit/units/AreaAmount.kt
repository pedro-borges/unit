package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class AreaAmount<L : Unit<Length>>(
        amount: Number,
        unit: Tuple<L, L>)
    : Amount<Length>(amount = amount.toBigBigDecimal() * convert(unit.second to unit.first),
                     unit = CompositeUnit(
                         domain = Length::class,
                         units = listOf(unit.first to 2)))
{
    val length = unit.first

    // region Area

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AreaAmount(amount = amount * that.amount * that.unit.scale,
                   unit = length * length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AreaAmount(amount = amount / that.amount / that.unit.scale,
                   unit = length * length)

    operator fun <L : Unit<Length>> plus(that: AreaAmount<L>) =
        AreaAmount(amount = amount + that.amount * convert(that.length to length,
                                                           that.length to length),
                   unit = length * length)

    @JvmName("plusArea")
    operator fun plus(that: AreaAmount<L>) =
        AreaAmount(amount = amount + that.amount,
                   unit = length * length)

    operator fun <L : Unit<Length>> minus(that: AreaAmount<L>) =
        AreaAmount(amount = amount - that.amount * convert(that.length to length,
                                                           that.length to length),
                   unit = length * length)

    @JvmName("minusArea")
    operator fun minus(that: AreaAmount<L>) =
        AreaAmount(amount = amount - that.amount,
                   unit = length * length)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>> div(that: AreaAmount<L>) =
        NaturalAmount(amount = amount / that.amount / convert(that.length to length,
                                                              that.length to length))

    @JvmName("divLength")
    operator fun div(that: AreaAmount<L>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Length

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        LengthAmount(amount = amount / that.amount / convert(that.length to length),
                     unit = length)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        LengthAmount(amount = amount / that.amount,
                     unit = length)

    // endregion

    // region Volume

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        VolumeAmount(amount = amount * that.amount * convert(that.length to length),
                     unit = length * length * length)

    @JvmName("timesLength")
    operator fun times(that: LengthAmount<L>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = length * length * length)

    // endregion
}