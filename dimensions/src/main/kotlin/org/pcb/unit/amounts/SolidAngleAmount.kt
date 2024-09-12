package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SolidAngle
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SolidAngleAmount<A : Unit<Angle>>(
        amount: Number,
        unit: A)
    : Amount<SolidAngle>(amount = amount.toBigDecimal(),
                         unit = CompositeUnit(
                             domain = SolidAngle::class,
                             units = mapOf(unit to 1)))
{
    val angle = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SolidAngle>) =
            SolidAngleAmount(amount = amount * unit.scale,
                             unit = RADIAN)
    }

    // region -> SolidAngle

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SolidAngleAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                         unit = angle)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SolidAngleAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                         unit = angle)

    @JvmName("plusSolidAngle")
    operator fun <A : Unit<Angle>> plus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount + that.amount * convert(that.angle to angle,
                                                                 that.angle to angle),
                         unit = angle)

    operator fun plus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount + that.amount,
                         unit = angle)

    @JvmName("minusSolidAngle")
    operator fun <A : Unit<Angle>> minus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount - that.amount * convert(that.angle to angle,
                                                                 that.angle to angle),
                         unit = angle)

    operator fun minus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount - that.amount,
                         unit = angle)

    // endregion

    // region -> Natural

    @JvmName("divSolidAngle")
    operator fun <A : Unit<Angle>> div(that: SolidAngleAmount<A>) =
        Numeric(amount = amount / that.amount / convert(that.angle to angle,
                                                        that.angle to angle))

    operator fun div(that: SolidAngleAmount<A>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> LuminousFlux

    operator fun <LI : Unit<LuminousIntensity>> times(that: LuminousIntensityAmount<LI>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.luminousIntensity, angle))

    // endregion
}