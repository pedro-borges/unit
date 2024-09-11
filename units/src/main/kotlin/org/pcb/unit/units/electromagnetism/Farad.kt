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
import org.pcb.unit.units.CapacitanceAmount
import org.pcb.unit.units.base.Ampere.AmpereUnit
import org.pcb.unit.units.base.Gram.GramUnit
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Farad(amount: Number,
            magnitude: Magnitude = UNI)
    : CapacitanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Distance>>(
    amount = amount,
    unit = U2(SECOND, AmpereUnit(magnitude)) to U2(kilo(GramUnit(magnitude)), METER))
{
    open class FaradUnit(magnitude: Magnitude)
        : Unit<Capacitance>(Capacitance::class, magnitude.symbol + "F", magnitude.scale)

    object FARAD : FaradUnit(UNI)
}
