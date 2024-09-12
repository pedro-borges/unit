package org.pcb.unit.amounts.optics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.IlluminanceAmount
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Illuminance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.toBigDecimal

class Lux(amount: Number,
          magnitude: Magnitude = UNI)
    : IlluminanceAmount<Unit<LuminousIntensity>, Unit<Angle>, Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(CANDELA, RADIAN) to METER)
{
    open class LuxUnit(magnitude: Magnitude)
        : Unit<Illuminance>(Illuminance::class, "lx", 1.toBigDecimal(), magnitude)

    object LUX : LuxUnit(UNI)
}