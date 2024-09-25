package org.pcb.unit.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.TemperatureAmount
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.toBigDecimal

class Kelvin(amount: Number,
             magnitude: Magnitude = UNI)
    : TemperatureAmount<Unit<Temperature>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = KELVIN)
{
    open class KelvinUnit(magnitude: Magnitude)
        : Unit<Temperature>(Temperature::class, "K", 1.toBigDecimal(), magnitude)

    object KELVIN : KelvinUnit(UNI)
}
