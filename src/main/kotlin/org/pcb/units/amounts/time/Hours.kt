package org.pcb.units.amounts.time

import org.pcb.units.amounts.generic.TimeAmount
import org.pcb.units.unit.time.Hour
import org.pcb.units.unit.time.Hour.Companion.HOUR

class Hours(amount: Number)
    : TimeAmount<Hour>(amount, HOUR)
{
}