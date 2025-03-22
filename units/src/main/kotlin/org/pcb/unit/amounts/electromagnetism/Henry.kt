package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.InductanceAmount
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Inductance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Henry(amount: Number,
            magnitude: Magnitude = UNI)
    : InductanceAmount<Unit<Distance>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
{
    open class HenryUnit(magnitude: Magnitude)
        : Unit<Inductance>(Inductance::class, "H", 1.toBigDecimal(), magnitude)

    object HENRY : HenryUnit(UNI)
}
