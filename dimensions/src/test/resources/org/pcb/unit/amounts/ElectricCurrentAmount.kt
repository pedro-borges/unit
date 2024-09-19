package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.toBigDecimal

open class ElectricCurrentAmount<EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = mapOf(unit to 1)))
{
    val electricCurrent = unit

    $operators
}
