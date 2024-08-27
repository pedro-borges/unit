package org.pcb.units.amounts.mechanics

import org.pcb.units.amounts.domain.MassAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.mechanics.Kilogram.Companion.KILOGRAMS

class Kilograms(amount: Number)
    : MassAmount(amount.toBigDecimal(), KILOGRAMS)
{
}