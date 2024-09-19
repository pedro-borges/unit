package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class TemperatureAmount<K : Unit<Temperature>>(
        amount: Number,
        unit: K)
    : Amount<Temperature>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = Temperature::class,
                              units = mapOf(unit to 1)))
{
    val temperature = unit

    // region -> Temperature

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TemperatureAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = temperature)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TemperatureAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = temperature)

    @JvmName("plusTemperature")
    operator fun <K : Unit<Temperature>> plus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount + that.amount
                                   * convert(that.temperature to temperature),
                          unit = temperature)

    operator fun plus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount + that.amount,
                          unit = temperature)

    @JvmName("minusTemperature")
    operator fun <K : Unit<Temperature>> minus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount - that.amount
                                   * convert(that.temperature to temperature),
                          unit = temperature)

    operator fun minus(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount - that.amount,
                          unit = temperature)

    // endregion

    // region -> Scalar

    @JvmName("divTemperature")
    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.temperature to temperature),
                     unit = NUMERIC)

    operator fun div(that: TemperatureAmount<K>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion
}
