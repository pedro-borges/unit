package org.pcb.unit.amount

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

open class VolumeAmount<L : Unit<Length>>(
        amount: Number,
        val length: L,
        length2: L,
        length3: L)
    : Amount<Length>(amount = amount.toBigBigDecimal() * convert(length2 to length,
                                                                 length3 to length),
                     unit = CompositeUnit(
                         domain = Length::class,
                         units = listOf(length to 3)))
{
    // region Volume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount * that.amount * that.unit.scale,
                     length = length,
                     length2 = length,
                     length3 = length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount / that.amount / that.unit.scale,
                     length = length,
                     length2 = length,
                     length3 = length)

    operator fun <L : Unit<Length>> plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount * convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     length = length,
                     length2 = length,
                     length3 = length)

    @JvmName("plusLength")
    operator fun plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount,
                     length = length,
                     length2 = length,
                     length3 = length)

    operator fun <L : Unit<Length>> minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount * convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     length = length,
                     length2 = length,
                     length3 = length)

    @JvmName("minusLength")
    operator fun minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount,
                     length = length,
                     length2 = length,
                     length3 = length)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>> div(that: VolumeAmount<L>) =
        NaturalAmount(amount = amount / that.amount / convert(that.length to length,
                                                              that.length to length,
                                                              that.length to length),)

    @JvmName("divLength")
    operator fun div(that: VolumeAmount<L>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Area

    operator fun <L : Unit<Length>>div(that: LengthAmount<L>) =
        AreaAmount(amount = amount / that.amount / convert(that.length to length),
                   length = length,
                   length2 = length)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        AreaAmount(amount = amount / that.amount,
                   length = length,
                   length2 = length)

    // endregion

    // region Length

    operator fun <L : Unit<Length>>div(that: AreaAmount<L>) =
        LengthAmount(amount = amount / that.amount / convert(that.length to length,
                                                           that.length to length),
                   length = length)

    @JvmName("divArea")
    operator fun div(that: AreaAmount<L>) =
        LengthAmount(amount = amount / that.amount,
                   length = length)

    // endregion
}