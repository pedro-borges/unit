package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.MassFlowRate
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

open class MassFlowRateAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<MassFlowRate>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = MassFlowRate::class,
                               units = listOf(unit.major to 1,
                                              unit.minor to -1)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassFlowRate>) =
            MassFlowRateAmount(amount = amount.toBigBigDecimal() * unit.scale,
                               unit = KILOGRAM to SECOND)
    }

    // region -> Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount * that.amount * that.unit.scale,
                           unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount / that.amount / that.unit.scale,
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
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Momentum

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, that.length) to time)

    // endregion
}