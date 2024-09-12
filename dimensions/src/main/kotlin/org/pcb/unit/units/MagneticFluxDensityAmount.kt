package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.MagneticFluxDensity
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.base.Numeric.NUMERIC
import org.pcb.unit.units.base.Second.SECOND

open class MagneticFluxDensityAmount<M : Unit<Mass>, T2 : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<M, U2<T2, EC>>)
    : Amount<MagneticFluxDensity>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = MagneticFluxDensity::class,
                                      units = mapOf(unit.major to 1,
                                                    unit.minor.first to -2,
                                                    unit.minor.second to -1)))
{
    val mass = unit.major
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFluxDensity>) =
            MagneticFluxDensityAmount(amount = amount.toBigDecimal() * unit.scale,
                                      unit = kilo(GRAM) to U2(SECOND, AMPERE))
    }

    // region -> MagneticFluxDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = mass to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = mass to U2(time, electricCurrent))

    @JvmName("plusMagneticFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: MagneticFluxDensityAmount<M, T, EC>) =
        MagneticFluxDensityAmount(amount = amount + that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.time to time,
                                                     that.time to time,
                                                     that.electricCurrent to electricCurrent),
                                  unit = mass to U2(time, electricCurrent))

    operator fun plus(that: MagneticFluxDensityAmount<M, T2, EC>) =
        MagneticFluxDensityAmount(amount = amount + that.amount,
                                  unit = mass to U2(time, electricCurrent))

    @JvmName("minusMagneticFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: MagneticFluxDensityAmount<M, T, EC>) =
        MagneticFluxDensityAmount(amount = amount - that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.time to time,
                                                     that.time to time,
                                                     that.electricCurrent to electricCurrent),
                                  unit = mass to U2(time, electricCurrent))

    operator fun minus(that: MagneticFluxDensityAmount<M, T2, EC>) =
        MagneticFluxDensityAmount(amount = amount - that.amount,
                                  unit = mass to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    @JvmName("divMagneticFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<M, T, EC>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass)
                         * convert(that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent))

    operator fun div(that: MagneticFluxDensityAmount<M, T2, EC>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> MagneticFlux

    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(mass, that.distance) to U2(time, electricCurrent))

    // endregion
}
