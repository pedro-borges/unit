package org.pcb.unit.solid

import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.VolumeAmount
import org.pcb.unit.dimensions.Distance

interface Solid<D : Unit<Distance>>
{
    val narrowest: DistanceAmount<D>
    val widest: DistanceAmount<D>
    val area: AreaAmount<D>
    val volume: VolumeAmount<D>
}