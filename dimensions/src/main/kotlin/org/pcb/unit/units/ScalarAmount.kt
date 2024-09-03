package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount.ONE

open class ScalarAmount<S : Unit<Scalar>>(
        amount: Number,
        unit: S)
    : Amount<Scalar>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Scalar::class,
                         units = listOf(unit to 1)))
{
    val scalar = unit

    // region Scalar

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount * that.amount * that.unit.scale,
                     unit = scalar)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount / that.amount / that.unit.scale,
                     unit = scalar)

    operator fun <S : Unit<Scalar>> plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount * convert(that.scalar to scalar),
                     unit = scalar)

    @JvmName("plusScalar")
    operator fun plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount,
                     unit = scalar)

    operator fun <S : Unit<Scalar>> minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount * convert(that.scalar to scalar),
                     unit = scalar)

    @JvmName("minusScalar")
    operator fun minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount,
                     unit = scalar)

    // endregion

    // region Frequency

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        FrequencyAmount(amount = amount * unit.scale / that.amount,
                        unit = ONE / that.time)

    // endregion

    // region Time

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        TimeAmount(amount = amount * unit.scale / that.amount,
                   unit = that.time)

    // endregion
}