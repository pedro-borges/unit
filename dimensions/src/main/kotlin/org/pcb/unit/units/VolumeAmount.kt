package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Volume
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class VolumeAmount<L3 : Unit<Length>>(
        amount: Number,
        unit: L3)
    : Amount<Length>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Length::class,
                         units = listOf(unit to 3)))
{
    val length = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Volume>) =
            VolumeAmount(amount = amount.toBigBigDecimal() * unit.scale,
                         unit = METER)
    }

    // region -> Volume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount * that.amount * that.unit.scale,
                     unit = length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount / that.amount / that.unit.scale,
                     unit = length)

    @JvmName("plusVolume")
    operator fun <L : Unit<Length>> plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount * convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     unit = length)

    operator fun plus(that: VolumeAmount<L3>) =
        VolumeAmount(amount = amount + that.amount,
                     unit = length)

    @JvmName("minusVolume")
    operator fun <L : Unit<Length>> minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount * convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     unit = length)

    operator fun minus(that: VolumeAmount<L3>) =
        VolumeAmount(amount = amount - that.amount,
                     unit = length)

    // endregion

    // region -> Natural

    @JvmName("divVolume")
    operator fun <L : Unit<Length>> div(that: VolumeAmount<L>) =
        ScalarAmount(amount = amount / that.amount / convert(that.length to length,
                                                             that.length to length,
                                                             that.length to length),
                     unit = NUMERIC)

    operator fun div(that: VolumeAmount<L3>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divLength")
    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        AreaAmount(amount = amount / that.amount / convert(that.length to length),
                   unit = length)

    operator fun div(that: LengthAmount<L3>) =
        AreaAmount(amount = amount / that.amount,
                   unit = length)

    // endregion

    // region -> Length

    @JvmName("divArea")
    operator fun <L : Unit<Length>> div(that: AreaAmount<L>) =
        LengthAmount(amount = amount / that.amount / convert(that.length to length,
                                                             that.length to length),
                     unit = length)

    operator fun div(that: AreaAmount<L3>) =
        LengthAmount(amount = amount / that.amount,
                     unit = length)

    // endregion
}