package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.ElectricTension
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.ElectricTensionAmount

class Volt(amount: Number,
           magnitude: Magnitude = UNI)
    : ElectricTensionAmount<Unit<Mass>, Unit<Distance>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
{
    open class VoltUnit(magnitude: Magnitude)
        : Unit<ElectricTension>(ElectricTension::class, "V", 1.toBigDecimal(), magnitude)

    object VOLT : VoltUnit(UNI)
}
