package org.pcb.unit.units.frequency

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Frequency
import org.pcb.unit.dimension.Time
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

class Hertz(amount: Number,
            magnitude: Magnitude = UNI)
    : FrequencyAmount<Unit<Time>>(
    amount = amount * magnitude.scale,
    unit = NUMERIC to SECOND)
{
    open class HertzUnit(magnitude: Magnitude)
        : Unit<Frequency>(Frequency::class, "Hz", magnitude, 1.toBigBigDecimal())

    object HERTZ : HertzUnit(UNI)
}