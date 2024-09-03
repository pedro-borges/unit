package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Energy
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class EnergyAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<Triple<M, L, L>, Tuple<T, T>>)
    : Amount<Force>(amount = amount.toBigBigDecimal()
                             * convert(unit.first.third to unit.first.second)
                             / convert(unit.second.second to unit.second.first),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.first.first to 1,
                                       unit.first.second to 2,
                                       unit.second.first to -2)))
{
    val mass = unit.first.first
    val length = unit.first.second
    val time = unit.second.first

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Energy>) =
            EnergyAmount(amount = amount.toBigBigDecimal() * unit.scale,
                         unit = (KILOGRAM * METER * METER) /
                                (SECOND * SECOND))
    }

    // region -> Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * that.unit.scale,
                     unit = (mass * length * length) /
                            (time * time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / that.unit.scale,
                     unit = (mass * length * length) /
                            (time * time))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = (mass * length * length) /
                            (time * time))

    @JvmName("plusEnergy")
    operator fun plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount,
                     unit = (mass * length * length) /
                            (time * time))


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = (mass * length * length) /
                            (time * time))

    @JvmName("minusEnergy")
    operator fun minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount,
                     unit = (mass * length * length) /
                            (time * time))

    // endregion

    // region -> Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: EnergyAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length)
                               * convert(that.time to time,
                                         that.time to time))

    @JvmName("divEnergy")
    operator fun div(that: EnergyAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region -> Force

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        ForceAmount(amount = amount / that.amount / convert(that.length to length),
                    unit = (mass * length) /
                           (time * time))

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        ForceAmount(amount = amount / that.amount,
                    unit = (mass * length) /
                           (time * time))

    // endregion

    // region -> Length

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = length)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount,
                     unit = length)

    // endregion

    // region -> Speed

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: MomentumAmount<M, L, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.length to length)
                             * convert(that.time to time),
                    unit = length / time)

    @JvmName("divMomentum")
    operator fun div(that: MomentumAmount<M, L, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = length / time)

    // endregion

    // region -> Momentum

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.length to length)
                                * convert(that.time to time),
                       unit = (mass * length) /
                              (time))

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = (mass * length) /
                              (time))

    // endregion

    // region -> Power

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount / convert(that.time to time),
                    unit = (mass * length * length) /
                           (time * time * time))

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount,
                    unit = (mass * length * length) /
                           (time * time * time))

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = (mass * length * length) /
                                     (time * time * time * that.electricCurrent))

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = (mass * length * length) /
                                     (time * time * time * that.electricCurrent))

    // endregion

    // region -> ElectricCharge

    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass,
                                                that.length to length,
                                                that.length to length)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = that.electricCurrent * time)

    @JvmName("divElectricTension")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricChargeAmount(amount = amount / that.amount / convert(that.time to time),
                             unit = that.electricCurrent * time)

    // endregion
}