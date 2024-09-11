package org.pcb.unit.units.frequency

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Frequency
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

class Hertz(amount: Number)
    : FrequencyAmount<Unit<Time>>(
    amount = amount,
    unit = NUMERIC to SECOND)
{
    object HERTZ : Unit<Frequency>(Frequency::class, "Hz", 1.toBigBigDecimal())
}