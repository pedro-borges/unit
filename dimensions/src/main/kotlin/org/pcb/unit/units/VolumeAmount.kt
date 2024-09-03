package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Triple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class VolumeAmount<L : Unit<Length>>(
        amount: Number,
        major: Triple<L, L, L>)
    : Amount<Length>(amount = amount.toBigBigDecimal() * convert(major.second to major.first,
                                                                 major.third to major.first),
                     unit = CompositeUnit(
                         domain = Length::class,
                         units = listOf(major.first to 3)))
{
    val length = major.first

    // region Volume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount * that.amount * that.unit.scale,
                     major = length * length * length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount / that.amount / that.unit.scale,
                     major = length * length * length)

    operator fun <L : Unit<Length>> plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount * convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     major = length * length * length)

    @JvmName("plusVolume")
    operator fun plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount,
                     major = length * length * length)

    operator fun <L : Unit<Length>> minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount * convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     major = length * length * length)

    @JvmName("minusVolume")
    operator fun minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount,
                     major = length * length * length)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>> div(that: VolumeAmount<L>) =
        NaturalAmount(
            amount = amount / that.amount / convert(that.length to length,
                                                    that.length to length,
                                                    that.length to length),
        )

    @JvmName("divLength")
    operator fun div(that: VolumeAmount<L>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Area

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        AreaAmount(amount = amount / that.amount / convert(that.length to length),
                   major = length * length)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        AreaAmount(amount = amount / that.amount,
                   major = length * length)

    // endregion

    // region Length

    operator fun <L : Unit<Length>> div(that: AreaAmount<L>) =
        LengthAmount(amount = amount / that.amount / convert(that.length to length,
                                                             that.length to length),
                     major = length)

    @JvmName("divArea")
    operator fun div(that: AreaAmount<L>) =
        LengthAmount(amount = amount / that.amount,
                     major = length)

    // endregion
}