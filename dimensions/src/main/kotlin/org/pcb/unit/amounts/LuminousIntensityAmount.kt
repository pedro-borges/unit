package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousIntensityAmount<LI : Unit<LuminousIntensity>>(
        amount: Number,
        unit: LI)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit to 1)))
{
    val luminousIntensity = unit

    // region -> LuminousIntensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminousIntensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = luminousIntensity)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminousIntensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = luminousIntensity)

    @JvmName("plusLuminousIntensity")
    operator fun <LI : Unit<LuminousIntensity>> plus(that: LuminousIntensityAmount<LI>) =
        LuminousIntensityAmount(amount = amount + that.amount * convert(that.luminousIntensity to luminousIntensity),
                                unit = luminousIntensity)

    operator fun plus(that: LuminousIntensityAmount<LI>) =
        LuminousIntensityAmount(amount = amount + that.amount,
                                unit = luminousIntensity)

    @JvmName("minusLuminousIntensity")
    operator fun <LI : Unit<LuminousIntensity>> minus(that: LuminousIntensityAmount<LI>) =
        LuminousIntensityAmount(amount = amount - that.amount * convert(that.luminousIntensity to luminousIntensity),
                                unit = luminousIntensity)

    operator fun minus(that: LuminousIntensityAmount<LI>) =
        LuminousIntensityAmount(amount = amount - that.amount,
                                unit = luminousIntensity)

    // endregion

    // region -> Numeric

    @JvmName("divLuminousIntensity")
    operator fun <LI : Unit<LuminousIntensity>> div(that: LuminousIntensityAmount<LI>) =
        Numeric(amount = amount / that.amount / convert(that.luminousIntensity to luminousIntensity))

    operator fun div(that: LuminousIntensityAmount<LI>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> LuminousFlux

    operator fun <A : Unit<Angle>> times(that: SolidAngleAmount<A>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(luminousIntensity, that.angle))

    // endregion

    // region -> Luminance

    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        LuminanceAmount(amount = amount / that.amount,
                        unit = luminousIntensity to that.distance)

    // endregion

    // region -> Area

    @JvmName("divLuminance")
    operator fun <LI : Unit<LuminousIntensity>, D : Unit<Distance>> div(that: LuminanceAmount<D, LI>) =
        AreaAmount(amount = amount / that.amount / convert(that.luminousIntensity to luminousIntensity),
                   unit = that.distance)

    operator fun <D : Unit<Distance>> div(that: LuminanceAmount<D, LI>) =
        AreaAmount(amount = amount / that.amount / convert(that.luminousIntensity to luminousIntensity),
                   unit = that.distance)

    // endregion
}