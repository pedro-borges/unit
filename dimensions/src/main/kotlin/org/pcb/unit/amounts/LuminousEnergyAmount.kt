package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousEnergy
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousEnergyAmount<LI : Unit<LuminousIntensity>, A : Unit<Angle>, T : Unit<Time>>(
        amount: Number,
        unit: U3<LI, A, T>)
    : Amount<LuminousEnergy>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = LuminousEnergy::class,
                                 units = mapOf(unit.first to 1,
                                               unit.second to 2,
                                               unit.third to 1)))
{
    val luminousIntensity = unit.first
    val angle = unit.second
    val time = unit.third

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LuminousEnergy>) =
            LuminousEnergyAmount(amount = amount * unit.scale,
                                 unit = U3(CANDELA, RADIAN, SECOND))
    }

    // region -> LuminousEnergy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminousEnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = U3(luminousIntensity, angle, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminousEnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = U3(luminousIntensity, angle, time))

    @JvmName("plusLuminousEnergy")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, T : Unit<Time>> plus(that: LuminousEnergyAmount<LI, A, T>) =
        LuminousEnergyAmount(amount = amount + that.amount * convert(that.luminousIntensity to luminousIntensity,
                                                                     that.angle to angle,
                                                                     that.angle to angle,
                                                                     that.time to time),
                             unit = U3(luminousIntensity, angle, time))

    operator fun plus(that: LuminousEnergyAmount<LI, A, T>) =
        LuminousEnergyAmount(amount = amount + that.amount,
                             unit = U3(luminousIntensity, angle, time))

    @JvmName("minusLuminousEnergy")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, T : Unit<Time>> minus(that: LuminousEnergyAmount<LI, A, T>) =
        LuminousEnergyAmount(amount = amount - that.amount * convert(that.luminousIntensity to luminousIntensity,
                                                                     that.angle to angle,
                                                                     that.angle to angle,
                                                                     that.time to time),
                             unit = U3(luminousIntensity, angle, time))

    operator fun minus(that: LuminousEnergyAmount<LI, A, T>) =
        LuminousEnergyAmount(amount = amount - that.amount,
                             unit = U3(luminousIntensity, angle, time))

    // endregion

    // region -> Natural

    @JvmName("divLuminousFlux")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, T : Unit<Time>> div(that: LuminousEnergyAmount<LI, A, T>) =
        Numeric(amount = amount / that.amount / convert(that.luminousIntensity to luminousIntensity,
                                                        that.angle to angle,
                                                        that.angle to angle,
                                                        that.time to time))

    operator fun div(that: LuminousEnergyAmount<LI, A, T>) =
        Numeric(amount = amount / that.amount)

    // endregion
}