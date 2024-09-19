package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.toBigDecimal

open class TemperatureAmount<T : Unit<Temperature>>(
        amount: Number,
        unit: T)
    : Amount<Temperature>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = Temperature::class,
                              units = mapOf(unit to 1)))
{
    val temperature = unit

    $operators
}
