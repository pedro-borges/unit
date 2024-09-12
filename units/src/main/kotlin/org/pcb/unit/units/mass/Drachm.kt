package org.pcb.unit.units.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Drachm(amount: Number,
             magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount * magnitude.scale,
    unit = DRACHM)
{
    open class DrachmUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "dr", magnitude, 0.0017718451953125.toBigBigDecimal())

    object DRACHM : DrachmUnit(UNI)
}
