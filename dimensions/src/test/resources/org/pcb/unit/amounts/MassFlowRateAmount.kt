package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MassFlowRate
import org.pcb.unit.dimensions.Time
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassFlowRateAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<MassFlowRate>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = MassFlowRate::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor to -1)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassFlowRate>) =
            MassFlowRateAmount(amount = amount * unit.scale,
                               unit = kilo(GRAM) to SECOND)
    }

    $operators
}
