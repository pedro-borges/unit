package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Temperature
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TemperatureAmount

class Kelvin(amount: Number,
             magnitude: Magnitude = UNI)
    : TemperatureAmount<Unit<Temperature>>(
    amount = amount * magnitude.scale,
    unit = KELVIN)
{
    open class KelvinUnit(magnitude: Magnitude)
        : Unit<Temperature>(Temperature::class, "K", magnitude, 1.toBigBigDecimal())

    object KELVIN : KelvinUnit(UNI)
}

