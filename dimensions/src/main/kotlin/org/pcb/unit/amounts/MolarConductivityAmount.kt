package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MolarConductivity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MolarConductivityAmount<I : Unit<ElectricCurrent>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<T, I>, U2<M, N>>)
    : Amount<MolarConductivity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = MolarConductivity::class,
                                    units = mapOf(unit.major.first to 3,
                                                  unit.major.second to 2,
                                                  unit.minor.first to -1,
                                                  unit.minor.second to -1)))
{
    val time = unit.major.first
    val electricCurrent = unit.major.second
    val mass = unit.minor.first
    val substance = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MolarConductivity>) =
            MolarConductivityAmount(amount = amount * unit.scale,
                                    unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), MOLE))
    }

    // region -> MolarConductivity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MolarConductivityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = U2(time, electricCurrent) to U2(mass, substance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MolarConductivityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = U2(time, electricCurrent) to U2(mass, substance))

    @JvmName("plusMolarConductivity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> plus(that: MolarConductivityAmount<I, M, N, T>) =
        MolarConductivityAmount(amount = amount + that.amount
                                         * convert(that.electricCurrent to electricCurrent,
                                                   that.electricCurrent to electricCurrent,
                                                   that.time to time,
                                                   that.time to time,
                                                   that.time to time)
                                         / convert(that.mass to mass,
                                                   that.substance to substance),
                                unit = U2(time, electricCurrent) to U2(mass, substance))

    operator fun plus(that: MolarConductivityAmount<I, M, N, T>) =
        MolarConductivityAmount(amount = amount + that.amount,
                                unit = U2(time, electricCurrent) to U2(mass, substance))

    @JvmName("minusMolarConductivity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> minus(that: MolarConductivityAmount<I, M, N, T>) =
        MolarConductivityAmount(amount = amount - that.amount
                                         * convert(that.electricCurrent to electricCurrent,
                                                   that.electricCurrent to electricCurrent,
                                                   that.time to time,
                                                   that.time to time,
                                                   that.time to time)
                                         / convert(that.mass to mass,
                                                   that.substance to substance),
                                unit = U2(time, electricCurrent) to U2(mass, substance))

    operator fun minus(that: MolarConductivityAmount<I, M, N, T>) =
        MolarConductivityAmount(amount = amount - that.amount,
                                unit = U2(time, electricCurrent) to U2(mass, substance))

    // endregion

    // region -> Scalar

    @JvmName("divMolarConductivity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarConductivityAmount<I, M, N, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time)
                              * convert(that.mass to mass,
                                        that.substance to substance),
                     unit = NUMERIC)

    operator fun div(that: MolarConductivityAmount<I, M, N, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion
}
