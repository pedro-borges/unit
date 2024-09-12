package org.pcb.unit.amounts.frequency

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.FrequencyAmount
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Frequency
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

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
