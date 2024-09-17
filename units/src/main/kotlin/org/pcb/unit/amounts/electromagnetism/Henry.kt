package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.InductanceAmount
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
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
    unit = U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
{
    open class HenryUnit(magnitude: Magnitude)
        : Unit<Inductance>(Inductance::class, "H", 1.toBigDecimal(), magnitude)

    object HENRY : HenryUnit(UNI)
}
