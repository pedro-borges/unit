package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.MassFlowRate
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class MassFlowRateAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<MassFlowRate>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = MassFlowRate::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor to -1)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassFlowRate>) =
            MassFlowRateAmount(amount = amount.toBigBigDecimal() * unit.scale,
                               unit = kilo(GRAM) to SECOND)
    }

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = mass to time)

    @JvmName("plusMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount + that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun plus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount + that.amount,
                           unit = mass to time)

    @JvmName("minusMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount - that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun minus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount - that.amount,
                           unit = mass to time)

    // endregion

    // region -> Natural

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass)
                         * convert(that.time to time))

    operator fun div(that: MassFlowRateAmount<M, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Mass

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MassAmount(amount = amount * that.amount * convert(that.time to time),
                   unit = mass)

    operator fun times(that: TimeAmount<T>) =
        MassAmount(amount = amount * that.amount * convert(that.time to time),
                   unit = mass)

    // endregion

    // region -> Momentum

    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, that.distance) to time)

    // endregion
}