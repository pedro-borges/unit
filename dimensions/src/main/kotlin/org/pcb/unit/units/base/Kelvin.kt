package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Temperature
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.TemperatureAmount

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
