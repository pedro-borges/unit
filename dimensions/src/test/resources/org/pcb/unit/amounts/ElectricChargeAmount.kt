package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.ElectricCharge
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Time
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricChargeAmount<EC : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        unit: U2<EC, T>)
    : Amount<ElectricCharge>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = ElectricCharge::class,
                                 units = mapOf(unit.first to 1,
                                               unit.second to 1)))
{
    val electricCurrent = unit.first
    val time = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricCharge>) =
            ElectricChargeAmount(amount = amount * unit.scale,
                                 unit = U2(AMPERE, SECOND))
    }

    $operators
}
