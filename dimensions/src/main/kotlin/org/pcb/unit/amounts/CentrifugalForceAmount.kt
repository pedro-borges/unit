package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.CentrifugalForce
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

open class CentrifugalForceAmount<BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U3<BAC, L, M>, T>)
    : Amount<CentrifugalForce>(amount = amount.toBigDecimal(),
                               unit = CompositeUnit(
                                   domain = CentrifugalForce::class,
                                   units = mapOf(unit.major.first to 1,
                                                 unit.major.second to 1,
                                                 unit.major.third to 1,
                                                 unit.minor to -2)))
{
    val angle = unit.major.first
    val distance = unit.major.second
    val mass = unit.major.third
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<CentrifugalForce>) =
            CentrifugalForceAmount(amount = amount * unit.scale,
                                   unit = U3(RADIAN, METER, kilo(GRAM)) to SECOND)
    }

    // region -> CentrifugalForce

    override operator fun times(that: Number) =
        CentrifugalForceAmount(amount = amount * that.toBigDecimal(),
                               unit = U3(angle, distance, mass) to time)

    override operator fun div(that: Number) =
        CentrifugalForceAmount(amount = amount / that.toBigDecimal(),
                               unit = U3(angle, distance, mass) to time)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CentrifugalForceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                               unit = U3(angle, distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CentrifugalForceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                               unit = U3(angle, distance, mass) to time)

    @JvmName("plusCentrifugalForce")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: CentrifugalForceAmount<BAC, L, M, T>) =
        CentrifugalForceAmount(amount = amount + that.amount
                                        * convert(that.angle to angle,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        / convert(that.time to time,
                                                  that.time to time),
                               unit = U3(angle, distance, mass) to time)

    operator fun plus(that: CentrifugalForceAmount<BAC, L, M, T>) =
        CentrifugalForceAmount(amount = amount + that.amount,
                               unit = U3(angle, distance, mass) to time)

    @JvmName("minusCentrifugalForce")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: CentrifugalForceAmount<BAC, L, M, T>) =
        CentrifugalForceAmount(amount = amount - that.amount
                                        * convert(that.angle to angle,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        / convert(that.time to time,
                                                  that.time to time),
                               unit = U3(angle, distance, mass) to time)

    operator fun minus(that: CentrifugalForceAmount<BAC, L, M, T>) =
        CentrifugalForceAmount(amount = amount - that.amount,
                               unit = U3(angle, distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divCentrifugalForce")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: CentrifugalForceAmount<BAC, L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: CentrifugalForceAmount<BAC, L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Angle

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        AngleAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.mass to mass)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = angle)

    operator fun div(that: ForceAmount<L, M, T>) =
        AngleAmount(amount = amount / that.amount,
                    unit = angle)

    // endregion

    // region -> AngularVelocity

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        AngularVelocityAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.time to time),
                              unit = angle to time)

    operator fun div(that: MomentumAmount<L, M, T>) =
        AngularVelocityAmount(amount = amount / that.amount,
                              unit = angle to time)

    // endregion

    // region -> Force

    @JvmName("divAngle")
    operator fun <BAC : Unit<Angle>> div(that: AngleAmount<BAC>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.angle to angle),
                    unit = U2(distance, mass) to time)

    operator fun div(that: AngleAmount<BAC>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Momentum

    @JvmName("divAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> div(that: AngularVelocityAmount<BAC, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.angle to angle)
                                * convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun div(that: AngularVelocityAmount<BAC, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    // endregion
}
