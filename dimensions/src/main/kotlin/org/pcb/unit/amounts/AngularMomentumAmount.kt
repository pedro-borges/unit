package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AngularMomentum
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AngularMomentumAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, L>, T>)
    : Amount<AngularMomentum>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = AngularMomentum::class,
                                  units = mapOf(unit.major.first to 2,
                                                unit.major.second to 1,
                                                unit.minor to -1)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<AngularMomentum>) =
            AngularMomentumAmount(amount = amount * unit.scale,
                                  unit = U2(kilo(GRAM), METER) to SECOND)
    }

    // region -> AngularMomentum

    override operator fun <S : Unit<Scalar>> times(that: Number) =
        AngularMomentumAmount(amount = amount * that.toBigDecimal(),
                              unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: Number) =
        AngularMomentumAmount(amount = amount / that.toBigDecimal(),
                              unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AngularMomentumAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AngularMomentumAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = U2(mass, distance) to time)

    @JvmName("plusAngularMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: AngularMomentumAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount + that.amount
                                       * convert(that.distance to distance,
                                                 that.mass to mass,
                                                 that.mass to mass)
                                       / convert(that.time to time),
                              unit = U2(mass, distance) to time)

    operator fun plus(that: AngularMomentumAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount + that.amount,
                              unit = U2(mass, distance) to time)

    @JvmName("minusAngularMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: AngularMomentumAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount - that.amount
                                       * convert(that.distance to distance,
                                                 that.mass to mass,
                                                 that.mass to mass)
                                       / convert(that.time to time),
                              unit = U2(mass, distance) to time)

    operator fun minus(that: AngularMomentumAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount - that.amount,
                              unit = U2(mass, distance) to time)

    // endregion

    // region -> Scalar

    @JvmName("divAngularMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: AngularMomentumAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass,
                                        that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: AngularMomentumAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        ActionAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun div(that: LinearDensityAmount<L, M>) =
        ActionAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> LinearDensity

    @JvmName("divAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.mass to mass)
                                     * convert(that.time to time),
                            unit = mass to distance)

    operator fun div(that: ActionAmount<L, M, T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Mass

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time),
                   unit = mass)

    operator fun div(that: MomentumAmount<L, M, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MomentOfInertia

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        MomentOfInertiaAmount(amount = amount / that.amount
                                       / convert(that.mass to mass)
                                       * convert(that.distance to distance,
                                                 that.time to time),
                              unit = U2(distance, mass))

    operator fun div(that: ViscosityAmount<L, M, T>) =
        MomentOfInertiaAmount(amount = amount / that.amount,
                              unit = U2(distance, mass))

    // endregion

    // region -> Momentum

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.mass to mass),
                       unit = U2(distance, mass) to time)

    operator fun div(that: MassAmount<M>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Viscosity

    @JvmName("divMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MomentOfInertiaAmount<L, M>) =
        ViscosityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass),
                        unit = mass to U2(distance, time))

    operator fun div(that: MomentOfInertiaAmount<L, M>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, time))

    // endregion
}
