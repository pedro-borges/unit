package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.dimensions.Area
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AreaAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
    : Amount<Area>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Area::class,
                       units = mapOf(unit to 2)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Area>) =
            AreaAmount(amount = amount * unit.scale,
                       unit = METER)
    }

    $operators
}
