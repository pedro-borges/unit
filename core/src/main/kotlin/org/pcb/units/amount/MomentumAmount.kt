package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.Length
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Momentum
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class MomentumAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val length: L,
        val time: T)
    : Amount<Momentum>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = listOf(mass to 1,
                                          length to 1,
                                          time to -1)))
{
    // region Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * that.unit.scale,
                       mass = mass,
                       length = length,
                       time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / that.unit.scale,
                       mass = mass,
                       length = length,
                       time = time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                               that.length to length,
                                                               time to that.time),
                       mass = mass,
                       length = length,
                       time = time)

    @JvmName("plusMomentum")
    operator fun plus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount + that.amount,
                       mass = mass,
                       length = length,
                       time = time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                               that.length to length,
                                                               time to that.time),
                       mass = mass,
                       length = length,
                       time = time)

    @JvmName("minusMomentum")
    operator fun minus(that: MomentumAmount<M, L, T>) =
        MomentumAmount(amount = amount - that.amount,
                       mass = mass,
                       length = length,
                       time = time)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: MomentumAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.length to length,
                                                              time to that.time))

    @JvmName("divMomentum")
    operator fun div(that: MomentumAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount / convert(that.mass to mass),
                    length = length,
                    time = time)

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    length = length,
                    time = time)

    // endregion

    // region Mass

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount / convert(that.length to length,
                                                           time to that.time),
                   mass = mass)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    // endregion

    // region Force

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount / convert(that.time to time),
                    mass = mass,
                    length = length,
                    time = time,
                    time2 = time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    length = length,
                    time = time,
                    time2 = time)

    // endregion
}
