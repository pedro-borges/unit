package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Capacitance
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.CapacitanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Farad(amount: Number,
            magnitude: Magnitude = UNI)
    : CapacitanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
{
    open class FaradUnit(magnitude: Magnitude)
        : Unit<Capacitance>(Capacitance::class, "F", magnitude, 1.toBigBigDecimal())

    object FARAD : FaradUnit(UNI)
}
