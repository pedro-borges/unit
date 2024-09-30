package org.pcb.unit.shape

import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance

interface Shape<D : Unit<Distance>>
{
    val narrowest: DistanceAmount<D>
    val widest: DistanceAmount<D>
    val perimeter: DistanceAmount<D>
    val area: AreaAmount<D>
}