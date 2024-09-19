package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AngleAmount<A : Unit<Angle>>(
        amount: Number,
        unit: A)
    : Amount<Angle>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Angle::class,
                        units = mapOf(unit to 1)))
{
    val angle = unit

    // region -> Angle

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AngleAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = angle)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AngleAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = angle)

    @JvmName("plusAngle")
    operator fun <A : Unit<Angle>> plus(that: AngleAmount<A>) =
        AngleAmount(amount = amount + that.amount
                             * convert(that.angle to angle),
                    unit = angle)

    operator fun plus(that: AngleAmount<A>) =
        AngleAmount(amount = amount + that.amount,
                    unit = angle)

    @JvmName("minusAngle")
    operator fun <A : Unit<Angle>> minus(that: AngleAmount<A>) =
        AngleAmount(amount = amount - that.amount
                             * convert(that.angle to angle),
                    unit = angle)

    operator fun minus(that: AngleAmount<A>) =
        AngleAmount(amount = amount - that.amount,
                    unit = angle)

    // endregion

    // region -> Scalar

    @JvmName("divAngle")
    operator fun <A : Unit<Angle>> div(that: AngleAmount<A>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle),
                     unit = NUMERIC)

    operator fun div(that: AngleAmount<A>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AngularVelocity

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        AngularVelocityAmount(amount = amount * that.amount,
                              unit = angle to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        AngularVelocityAmount(amount = amount / that.amount,
                              unit = angle to that.time)

    // endregion

    // region -> SolidAngle

    @JvmName("timesAngle")
    operator fun <A : Unit<Angle>> times(that: AngleAmount<A>) =
        SolidAngleAmount(amount = amount * that.amount
                                  * convert(that.angle to angle),
                         unit = angle)

    operator fun times(that: AngleAmount<A>) =
        SolidAngleAmount(amount = amount * that.amount,
                         unit = angle)

    // endregion

    // region -> Time

    @JvmName("divAngularVelocity")
    operator fun <A : Unit<Angle>, T : Unit<Time>> div(that: AngularVelocityAmount<A, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.angle to angle),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: AngularVelocityAmount<A, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion
}
