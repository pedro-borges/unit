package org.pcb.unit.units.frequency

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Frequency
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

class Hertz(amount: Number,
            magnitude: Magnitude = UNI)
    : FrequencyAmount<Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = NUMERIC to SECOND)
{
    open class HertzUnit(magnitude: Magnitude)
        : Unit<Frequency>(Frequency::class, "Hz", 1.toBigDecimal(), magnitude)

    object HERTZ : HertzUnit(UNI)
}
