package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.MassFlux
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class MassFluxAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<MassFlux>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = MassFlux::class,
                               units = listOf(unit.major to 1,
                                              unit.minor to -1)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassFlux>) =
            MassFluxAmount(amount = amount.toBigBigDecimal() * unit.scale,
                               unit = KILOGRAM to SECOND)
    }

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassFluxAmount(amount = amount * that.amount * that.unit.scale,
                           unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassFluxAmount(amount = amount / that.amount / that.unit.scale,
                           unit = mass to time)

    @JvmName("plusMassFlux")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: MassFluxAmount<M, T>) =
        MassFluxAmount(amount = amount + that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun plus(that: MassFluxAmount<M, T>) =
        MassFluxAmount(amount = amount + that.amount,
                           unit = mass to time)

    @JvmName("minusMassFlux")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: MassFluxAmount<M, T>) =
        MassFluxAmount(amount = amount - that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun minus(that: MassFluxAmount<M, T>) =
        MassFluxAmount(amount = amount - that.amount,
                           unit = mass to time)

    // endregion

    // region -> Natural

    @JvmName("divMassFlux")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFluxAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MassFluxAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Momentum

    operator fun <D: Unit<Distance>> times(that: DistanceAmount<D>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, that.distance) to time)

    // endregion
}