package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Momentum
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class MomentumAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, L>, T>)
    : Amount<Momentum>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = listOf(unit.major.first to 1,
                                          unit.major.second to 1,
                                          unit.minor to -1)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor

    // region -> Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * that.unit.scale,
                       unit = U2(mass, length) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / that.unit.scale,
                       unit = U2(mass, length) to time)

    @JvmName("plusMomentum")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount + that.amount
                                * convert(that.mass to mass,
                                          that.length to length)
                                / convert(that.time to time),
                       unit = U2(mass, length) to time)

    operator fun plus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount + that.amount,
                       unit = U2(mass, length) to time)

    @JvmName("minusMomentum")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount - that.amount
                                * convert(that.mass to mass,
                                          that.length to length)
                                / convert(that.time to time),
                       unit = U2(mass, length) to time)

    operator fun minus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount - that.amount,
                       unit = U2(mass, length) to time)

    // endregion

    // region -> Natural

    @JvmName("divMomentum")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: MomentumAmount<M, L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MomentumAmount<M, L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Speed

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount / convert(that.mass to mass),
                    unit = length to time)

    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = length to time)

    // endregion

    // region -> Mass

    @JvmName("divSpeed")
    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.length to length)
                            * convert(that.time to time),
                   unit = mass)

    operator fun div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> Force

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount / convert(that.time to time),
                    unit = U2(mass, length) to time)

    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(mass, length) to time)

    // endregion

    // region -> Energy

    @JvmName("timesSpeed")
    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.length to length)
                              / convert(that.time to time),
                     unit = U2(mass, length) to time)

    operator fun times(that: SpeedAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, length) to time)

    // endregion

    // region -> MassFlowRate

    @JvmName("divLength")
    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount / convert(that.length to length),
                           unit = mass to time)

    operator fun div(that: LengthAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Length

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        LengthAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert((that.time to time)),
                           unit = length)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        LengthAmount(amount = amount / that.amount,
                     unit = length)

    // endregion
}
