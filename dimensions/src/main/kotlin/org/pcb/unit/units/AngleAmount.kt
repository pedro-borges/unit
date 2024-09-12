package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Angle
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class AngleAmount<T : Unit<Angle>>(
        amount: Number,
        unit: T)
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
    operator fun <T : Unit<Angle>> plus(that: AngleAmount<T>) =
        AngleAmount(amount = amount + that.amount * convert(that.angle to angle),
                    unit = angle)

    operator fun plus(that: AngleAmount<T>) =
        AngleAmount(amount = amount + that.amount,
                    unit = angle)

    @JvmName("minusAngle")
    operator fun <T : Unit<Angle>> minus(that: AngleAmount<T>) =
        AngleAmount(amount = amount - that.amount * convert(that.angle to angle),
                    unit = angle)

    operator fun minus(that: AngleAmount<T>) =
        AngleAmount(amount = amount - that.amount,
                    unit = angle)

    // endregion

    // region -> Natural

    @JvmName("divAngle")
    operator fun <T : Unit<Angle>> div(that: AngleAmount<T>) =
        Numeric(amount = amount / that.amount / convert(that.angle to angle))

    operator fun div(that: AngleAmount<T>) =
        Numeric(amount = amount / that.amount)

    // endregion
}