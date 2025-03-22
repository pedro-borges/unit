package org.pcb.unit.amounts.thermodynamics

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MolarHeatCapacityAmount
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Kelvin.KELVIN
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Mole.MOLE
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MolarHeatCapacity
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal

class Gas(amount: Number,
          magnitude: Magnitude = UNI)
    : MolarHeatCapacityAmount<Unit<Distance>, Unit<Mass>, Unit<Substance>, Unit<Temperature>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(METER, kilo(GRAM)) to U3(MOLE, KELVIN, SECOND))
{
    open class GasUnit(magnitude: Magnitude)
        : Unit<MolarHeatCapacity>(MolarHeatCapacity::class, "gas", 1.toBigDecimal(), magnitude)

    object GAS : GasUnit(UNI)
}
