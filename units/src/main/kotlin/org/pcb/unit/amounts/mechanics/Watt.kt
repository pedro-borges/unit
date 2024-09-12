package org.pcb.unit.amounts.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.PowerAmount
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Power
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Watt(amount: Number,
           magnitude: Magnitude = UNI)
    : PowerAmount<Unit<Mass>, Unit<Distance>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(kilo(GRAM), METER) to SECOND)
{
    open class WattUnit(magnitude: Magnitude)
        : Unit<Power>(Power::class, "W", 1.toBigDecimal(), magnitude)

    object WATT : WattUnit(UNI)
}
