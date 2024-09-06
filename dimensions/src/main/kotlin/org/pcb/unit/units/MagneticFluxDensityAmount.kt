package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.MagneticFlux
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class MagneticFluxDensityAmount<M : Unit<Mass>, T2 : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<M, U2<T2, EC>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major to 1,
                                                 unit.minor.first to -2,
                                                 unit.minor.second to -1)))
{
    val mass = unit.major
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFlux>) =
            MagneticFluxDensityAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                      unit = KILOGRAM to U2(SECOND, AMPERE))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount * that.amount * that.unit.scale,
                                  unit = mass to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount / that.amount / that.unit.scale,
                                  unit = mass to U2(time, electricCurrent))

    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: MagneticFluxDensityAmount<M, T, EC>) =
        MagneticFluxDensityAmount(amount = amount + that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.time to time,
                                                     that.time to time,
                                                     that.electricCurrent to electricCurrent),
                                  unit = mass to U2(time, electricCurrent))

    @JvmName("plusMagneticFluxDensity")
    operator fun plus(that: MagneticFluxDensityAmount<M, T2, EC>) =
        MagneticFluxDensityAmount(amount = amount + that.amount,
                                  unit = mass to U2(time, electricCurrent))

    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: MagneticFluxDensityAmount<M, T, EC>) =
        MagneticFluxDensityAmount(amount = amount - that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.time to time,
                                                     that.time to time,
                                                     that.electricCurrent to electricCurrent),
                                  unit = mass to U2(time, electricCurrent))

    @JvmName("minusMagneticFluxDensity")
    operator fun minus(that: MagneticFluxDensityAmount<M, T2, EC>) =
        MagneticFluxDensityAmount(amount = amount - that.amount,
                                  unit = mass to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<M, T, EC>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.electricCurrent to electricCurrent),
                     unit = NUMERIC)

    @JvmName("divMagneticFluxDensity")
    operator fun div(that: MagneticFluxDensityAmount<M, T2, EC>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> MagneticFlux

    operator fun <L : Unit<Length>> times(that: AreaAmount<L>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(mass, that.length) to U2(time, electricCurrent))

    // endregion
}
