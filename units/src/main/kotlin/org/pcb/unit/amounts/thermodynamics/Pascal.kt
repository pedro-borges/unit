package org.pcb.unit.amounts.thermodynamics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Pressure
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.PressureAmount

class Pascal(amount: Number,
             magnitude: Magnitude = UNI)
    : PressureAmount<Unit<Mass>, Unit<Distance>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = kilo(GRAM) to U2(METER, SECOND))
{
    open class PascalUnit(magnitude: Magnitude)
        : Unit<Pressure>(Pressure::class, "N", 1.toBigDecimal(), magnitude)

    object PASCAL : PascalUnit(UNI)
}
