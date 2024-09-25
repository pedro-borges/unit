package org.pcb.unit.amounts.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.ForceAmount
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Force
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Newton(amount: Number,
             magnitude: Magnitude = UNI)
    : ForceAmount<Unit<Distance>, Unit<Mass>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(METER, kilo(GRAM)) to SECOND)
{
    open class NewtonUnit(magnitude: Magnitude)
        : Unit<Force>(Force::class, "N", 1.toBigDecimal(), magnitude)

    object NEWTON : NewtonUnit(UNI)
}
