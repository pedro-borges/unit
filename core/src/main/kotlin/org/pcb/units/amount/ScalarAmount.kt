package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.convert
import org.pcb.units.dimension.Scalar
import org.pcb.units.dimension.Time
import org.pcb.units.div
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class ScalarAmount<S : Unit<Scalar>>(amount: Number,
                                          val scalar: S)
    : Amount<Scalar>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Scalar::class,
                         units = listOf(scalar to 1)))
{
    // region Scalar

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount * that.amount * that.unit.scale,
                     scalar = scalar)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount / that.amount / that.unit.scale,
                     scalar = scalar)

    operator fun <S : Unit<Scalar>> plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount * convert(that.scalar to scalar),
                     scalar = scalar)

    @JvmName("plusScalar")
    operator fun plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount,
                     scalar = scalar)

    operator fun <S : Unit<Scalar>> minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount * convert(that.scalar to scalar),
                     scalar = scalar)

    @JvmName("minusScalar")
    operator fun minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount,
                     scalar = scalar)

    // endregion

    // region Frequency

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        FrequencyAmount(amount = amount * unit.scale / that.amount,
                        time = that.time)

    // endregion

    // region Time

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        TimeAmount(amount = amount * unit.scale / that.amount,
                   time = that.time)

    // endregion
}