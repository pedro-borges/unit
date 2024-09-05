package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.MagneticFlux
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Weber(amount: Number)
    : MagneticFluxAmount<Unit<Mass>, Unit<Length>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount,
    unit = (KILOGRAM * METER * METER) /
           (SECOND * SECOND * AMPERE))
{
    object WEBER : Unit<MagneticFlux>(MagneticFlux::class)
    {
        override val symbol = "Wb"
        override val scale = 1.toBigBigDecimal()
    }
}
