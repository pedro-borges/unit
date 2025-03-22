package org.pcb.unit.amounts.optics

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.LuminousFluxAmount
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousFlux
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.toBigDecimal

class Lumen(amount: Number,
            magnitude: Magnitude = UNI)
    : LuminousFluxAmount<Unit<Angle>, Unit<LuminousIntensity>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(RADIAN, CANDELA))
{
    open class LumenUnit(magnitude: Magnitude)
        : Unit<LuminousFlux>(LuminousFlux::class, "lm", 1.toBigDecimal(), magnitude)

    object LUMEN : LumenUnit(UNI)
}