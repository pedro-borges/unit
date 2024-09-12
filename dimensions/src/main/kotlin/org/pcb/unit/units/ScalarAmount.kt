package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ScalarAmount<S : Unit<Scalar>>(
        amount: Number,
        unit: S)
    : Amount<Scalar>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Scalar::class,
                         units = mapOf(unit to 1)))
{
    val scalar = unit

    // region -> Scalar

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = scalar)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = scalar)

    @JvmName("plusScalar")
    operator fun <S : Unit<Scalar>> plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount * convert(that.scalar to scalar),
                     unit = scalar)

    operator fun plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount,
                     unit = scalar)

    @JvmName("minusScalar")
    operator fun <S : Unit<Scalar>> minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount * convert(that.scalar to scalar),
                     unit = scalar)

    operator fun minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount,
                     unit = scalar)

    // endregion

    // region -> Frequency

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        FrequencyAmount(amount = amount * unit.scale / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> Time

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        TimeAmount(amount = amount * unit.scale * that.amount,
                   unit = that.time)

    // endregion

    // region -> ElectricResistance

    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricConductanceAmount<T, EC, M, D>) =
        ElectricResistanceAmount(amount = amount * that.amount * unit.scale,
                                 unit = U2(that.mass, that.distance) to U2(that.time, that.electricCurrent))

    // endregion

    // region -> ElectricConductance

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricConductanceAmount(amount = amount * unit.scale / that.amount,
                                  unit = U2(that.time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion
}