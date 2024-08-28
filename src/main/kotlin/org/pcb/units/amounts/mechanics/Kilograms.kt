package org.pcb.units.amounts.mechanics

import org.pcb.units.amounts.generic.MassAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.mechanics.Kilogram
import org.pcb.units.unit.mechanics.Kilogram.Companion.KILOGRAM

class Kilograms(amount: Number)
    : MassAmount<Kilogram>(amount.toBigDecimal(), KILOGRAM)
{
}