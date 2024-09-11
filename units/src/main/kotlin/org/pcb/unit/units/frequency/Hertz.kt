package org.pcb.unit.units.frequency

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Frequency
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NumericUnit

class Hertz(amount: Number,
            magnitude: Magnitude = UNI)
    : FrequencyAmount<Unit<Time>>(
    amount = amount,
    unit = NumericUnit(magnitude) to SECOND)
{
    open class HertzUnit(magnitude: Magnitude)
        : Unit<Frequency>(Frequency::class, magnitude.symbol + "Hz", magnitude.scale)

    object HERTZ : HertzUnit(UNI)
}