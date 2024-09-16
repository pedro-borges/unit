package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.CapacitanceAmount
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Capacitance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Farad(amount: Number,
            magnitude: Magnitude = UNI)
    : CapacitanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
{
    open class FaradUnit(magnitude: Magnitude)
        : Unit<Capacitance>(Capacitance::class, "F", 1.toBigDecimal(), magnitude)

    object FARAD : FaradUnit(UNI)
}
