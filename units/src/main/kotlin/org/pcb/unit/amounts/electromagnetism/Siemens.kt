package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.ElectricConductanceAmount
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricConductance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Siemens(amount: Number,
              magnitude: Magnitude = UNI)
    : ElectricConductanceAmount<Unit<Distance>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
{
    open class SiemensUnit(magnitude: Magnitude)
        : Unit<ElectricConductance>(ElectricConductance::class, "S", 1.toBigDecimal(), magnitude)

    object SIEMENS : SiemensUnit(UNI)
}
