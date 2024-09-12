package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricTension
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

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
