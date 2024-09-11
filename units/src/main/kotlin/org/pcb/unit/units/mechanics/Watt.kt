package org.pcb.unit.units.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Power
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.base.Gram.GramUnit
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Watt(amount: Number,
           magnitude: Magnitude = UNI)
    : PowerAmount<Unit<Mass>, Unit<Distance>, Unit<Time>>(
    amount = amount,
    unit = U2(kilo(GramUnit(magnitude)), METER) to SECOND)
{
    open class WattUnit(magnitude: Magnitude)
        : Unit<Power>(Power::class, magnitude.symbol + "W", magnitude.scale)

    object WATT : Unit<Power>(Power::class, "W", 1.toBigBigDecimal())
}