package org.pcb.units.amounts.time

import org.pcb.units.amounts.generic.TimeAmount
import org.pcb.units.unit.time.Minute
import org.pcb.units.unit.time.Minute.Companion.MINUTE

class Minutes(amount: Number)
    : TimeAmount<Minute>(amount, MINUTE)
{
}