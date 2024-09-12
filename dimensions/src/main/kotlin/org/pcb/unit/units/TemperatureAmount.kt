package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Temperature
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class TemperatureAmount<T : Unit<Temperature>>(
        amount: Number,
        unit: T)
    : Amount<Temperature>(amount = amount.toBigBigDecimal(),
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
    operator fun <T : Unit<Temperature>> plus(that: TemperatureAmount<T>) =
        TemperatureAmount(amount = amount + that.amount * convert(that.temperature to temperature),
                          unit = temperature)

    operator fun plus(that: TemperatureAmount<T>) =
        TemperatureAmount(amount = amount + that.amount,
                          unit = temperature)

    @JvmName("minusTemperature")
    operator fun <T : Unit<Temperature>> minus(that: TemperatureAmount<T>) =
        TemperatureAmount(amount = amount - that.amount * convert(that.temperature to temperature),
                          unit = temperature)

    operator fun minus(that: TemperatureAmount<T>) =
        TemperatureAmount(amount = amount - that.amount,
                          unit = temperature)

    // endregion

    // region -> Natural

    @JvmName("divTemperature")
    operator fun <T : Unit<Temperature>> div(that: TemperatureAmount<T>) =
        Numeric(amount = amount / that.amount / convert(that.temperature to temperature))

    operator fun div(that: TemperatureAmount<T>) =
        Numeric(amount = amount / that.amount)

    // endregion
}