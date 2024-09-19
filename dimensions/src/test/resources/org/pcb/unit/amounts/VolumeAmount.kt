package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Volume
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class VolumeAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
    : Amount<Volume>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Volume::class,
                         units = mapOf(unit to 3)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Volume>) =
            VolumeAmount(amount = amount * unit.scale,
                         unit = METER)
    }

    $operators
}
