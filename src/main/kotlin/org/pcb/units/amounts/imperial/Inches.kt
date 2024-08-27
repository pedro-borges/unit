package org.pcb.units.amounts.imperial

import org.pcb.units.amounts.domain.SpaceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.imperial.Inch.Companion.INCHES

class Inches(amount: Number)
    : SpaceAmount(amount.toBigDecimal(), INCHES)
{
}