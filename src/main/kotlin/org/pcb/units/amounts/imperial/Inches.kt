package org.pcb.units.amounts.imperial

import org.pcb.units.amounts.generic.SpaceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.imperial.Inch
import org.pcb.units.unit.imperial.Inch.Companion.INCH

class Inches(amount: Number)
    : SpaceAmount<Inch>(amount.toBigDecimal(), INCH)
{
}