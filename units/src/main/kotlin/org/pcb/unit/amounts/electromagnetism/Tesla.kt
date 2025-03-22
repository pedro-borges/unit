package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MagneticFluxDensityAmount
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.MagneticFluxDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Tesla(amount: Number,
            magnitude: Magnitude = UNI)
    : MagneticFluxDensityAmount<Unit<ElectricCurrent>, Unit<Mass>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = kilo(GRAM) to U2(AMPERE, SECOND))
{
    open class TeslaUnit(magnitude: Magnitude)
        : Unit<MagneticFluxDensity>(MagneticFluxDensity::class, "T", 1.toBigDecimal(), magnitude)

    object TESLA : TeslaUnit(UNI)
}
