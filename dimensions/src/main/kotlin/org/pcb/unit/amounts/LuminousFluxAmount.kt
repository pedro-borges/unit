package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousFlux
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousFluxAmount<LI : Unit<LuminousIntensity>, A : Unit<Angle>>(
        amount: Number,
        unit: U2<LI, A>)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit.first to 1,
                                                  unit.second to 2)))
{
    val luminousIntensity = unit.first
    val angle = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LuminousFlux>) =
            LuminousFluxAmount(amount = amount * unit.scale,
                               unit = U2(CANDELA, RADIAN))
    }

    // region -> LuminousFlux

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminousFluxAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(luminousIntensity, angle))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminousFluxAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(luminousIntensity, angle))

    @JvmName("plusLuminousFlux")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>> plus(that: LuminousFluxAmount<LI, A>) =
        LuminousFluxAmount(amount = amount + that.amount * convert(that.luminousIntensity to luminousIntensity,
                                                                   that.angle to angle,
                                                                   that.angle to angle),
                           unit = U2(luminousIntensity, angle))

    operator fun plus(that: LuminousFluxAmount<LI, A>) =
        LuminousFluxAmount(amount = amount + that.amount,
                           unit = U2(luminousIntensity, angle))

    @JvmName("minusLuminousFlux")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>> minus(that: LuminousFluxAmount<LI, A>) =
        LuminousFluxAmount(amount = amount - that.amount * convert(that.luminousIntensity to luminousIntensity,
                                                                   that.angle to angle,
                                                                   that.angle to angle),
                           unit = U2(luminousIntensity, angle))

    operator fun minus(that: LuminousFluxAmount<LI, A>) =
        LuminousFluxAmount(amount = amount - that.amount,
                           unit = U2(luminousIntensity, angle))

    // endregion

    // region -> Natural

    @JvmName("divLuminousFlux")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>> div(that: LuminousFluxAmount<LI, A>) =
        Numeric(amount = amount / that.amount / convert(that.luminousIntensity to luminousIntensity,
                                                        that.angle to angle,
                                                        that.angle to angle))

    operator fun div(that: LuminousFluxAmount<LI, A>) =
        Numeric(amount = amount / that.amount)

    // endregion
}