package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Frequency
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.toBigDecimal

open class FrequencyAmount<T : Unit<Time>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, T>)
    : Amount<Frequency>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Frequency::class,
                            units = mapOf(unit.minor to -1)))
{
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Frequency>) =
            FrequencyAmount(amount = amount / unit.scale,
                            unit = NUMERIC to SECOND)
    }

    $operators
}
