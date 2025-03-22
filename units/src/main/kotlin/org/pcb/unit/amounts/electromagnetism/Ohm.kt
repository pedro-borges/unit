package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.ElectricResistanceAmount
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.ElectricResistance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Ohm(amount: Number,
          magnitude: Magnitude = UNI)
    : ElectricResistanceAmount<Unit<Distance>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
{
    open class OhmUnit(magnitude: Magnitude)
        : Unit<ElectricResistance>(ElectricResistance::class, "Ω", 1.toBigDecimal(), magnitude)

    object OHM : OhmUnit(UNI)
}
