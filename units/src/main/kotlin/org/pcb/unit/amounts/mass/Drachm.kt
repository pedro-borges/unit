package org.pcb.unit.amounts.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

class Drachm(amount: Number,
             magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = DRACHM)
{
    open class DrachmUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "dr", 0.0017718451953125.toBigDecimal(), magnitude)

    object DRACHM : DrachmUnit(UNI)
}
