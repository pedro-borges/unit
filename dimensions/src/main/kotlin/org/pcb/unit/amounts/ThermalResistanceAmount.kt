package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Kelvin.KELVIN
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.ThermalResistance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ThermalResistanceAmount<L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U3<T, L, K>, M>)
    : Amount<ThermalResistance>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = ThermalResistance::class,
                                    units = mapOf(unit.major.first to 3,
                                                  unit.major.second to 2,
                                                  unit.major.third to 1,
                                                  unit.minor to -1)))
{
    val time = unit.major.first
    val distance = unit.major.second
    val temperature = unit.major.third
    val mass = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ThermalResistance>) =
            ThermalResistanceAmount(amount = amount * unit.scale,
                                    unit = U3(SECOND, METER, KELVIN) to kilo(GRAM))
    }

    // region -> Convert

    fun convertTo(unit: Unit<ThermalResistance>) =
        ThermalResistanceAmount(amount = amount * convert(this.unit to unit),
                                unit = unit)

    // endregion

    // region -> ThermalResistance

    override operator fun times(that: Number) =
        ThermalResistanceAmount(amount = amount * that.toBigDecimal(),
                                unit = U3(time, distance, temperature) to mass)

    override operator fun div(that: Number) =
        ThermalResistanceAmount(amount = amount / that.toBigDecimal(),
                                unit = U3(time, distance, temperature) to mass)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ThermalResistanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = U3(time, distance, temperature) to mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ThermalResistanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = U3(time, distance, temperature) to mass)

    @JvmName("plusThermalResistance")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> plus(that: ThermalResistanceAmount<L, M, K, T>) =
        ThermalResistanceAmount(amount = amount + that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.temperature to temperature,
                                                   that.time to time,
                                                   that.time to time,
                                                   that.time to time)
                                         / convert(that.mass to mass),
                                unit = U3(time, distance, temperature) to mass)

    operator fun plus(that: ThermalResistanceAmount<L, M, K, T>) =
        ThermalResistanceAmount(amount = amount + that.amount,
                                unit = U3(time, distance, temperature) to mass)

    @JvmName("minusThermalResistance")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> minus(that: ThermalResistanceAmount<L, M, K, T>) =
        ThermalResistanceAmount(amount = amount - that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.temperature to temperature,
                                                   that.time to time,
                                                   that.time to time,
                                                   that.time to time)
                                         / convert(that.mass to mass),
                                unit = U3(time, distance, temperature) to mass)

    operator fun minus(that: ThermalResistanceAmount<L, M, K, T>) =
        ThermalResistanceAmount(amount = amount - that.amount,
                                unit = U3(time, distance, temperature) to mass)

    // endregion

    // region -> Scalar

    @JvmName("divThermalResistance")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalResistanceAmount<L, M, K, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.temperature to temperature,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time)
                              * convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: ThermalResistanceAmount<L, M, K, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Volume

    @JvmName("timesThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: ThermalConductivityAmount<L, M, K, T>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.mass to mass)
                              / convert(that.temperature to temperature,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = distance)

    operator fun times(that: ThermalConductivityAmount<L, M, K, T>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion
}
