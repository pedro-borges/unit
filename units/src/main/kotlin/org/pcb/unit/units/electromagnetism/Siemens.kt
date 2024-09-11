package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricConductance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.units.ElectricConductanceAmount
import org.pcb.unit.units.base.Ampere.AmpereUnit
import org.pcb.unit.units.base.Gram.GramUnit
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Siemens(amount: Number,
              magnitude: Magnitude = UNI)
    : ElectricConductanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Distance>>(
    amount = amount,
    unit = U2(SECOND, AmpereUnit(magnitude)) to U2(kilo(GramUnit(magnitude)), METER))
{
    open class SiemensUnit(magnitude: Magnitude)
        : Unit<ElectricConductance>(ElectricConductance::class, magnitude.symbol + "S", magnitude.scale)

    object SIEMENS : SiemensUnit(UNI)
}
