package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.LuminousIntensity
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC

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

    // region -> Natural

    @JvmName("divLuminousIntensity")
    operator fun <LI : Unit<LuminousIntensity>> div(that: LuminousIntensityAmount<LI>) =
        Numeric(amount = amount / that.amount / convert(that.luminousIntensity to luminousIntensity))

    operator fun div(that: LuminousIntensityAmount<LI>) =
        Numeric(amount = amount / that.amount)

    // endregion
}