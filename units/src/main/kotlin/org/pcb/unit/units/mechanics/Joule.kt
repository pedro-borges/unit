package org.pcb.unit.units.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Energy
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Joule(amount: Number,
            magnitude: Magnitude = UNI)
    : EnergyAmount<Unit<Mass>, Unit<Distance>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(kilo(GRAM), METER) to SECOND)
{
    open class JouleUnit(magnitude: Magnitude)
        : Unit<Energy>(Energy::class, "J", 1.toBigDecimal(), magnitude)

    object JOULE : JouleUnit(UNI)
}
