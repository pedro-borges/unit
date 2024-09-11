package org.pcb.unit.units.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.base.Gram.GramUnit
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Newton(amount: Number,
             magnitude: Magnitude = UNI)
    : ForceAmount<Unit<Mass>, Unit<Distance>, Unit<Time>>(
    amount = amount,
    unit = U2(kilo(GramUnit(magnitude)), METER) to SECOND)
{
    open class NewtonUnit(magnitude: Magnitude)
        : Unit<Force>(Force::class, magnitude.symbol + "N", magnitude.scale)

    object NEWTON : NewtonUnit(UNI)
}