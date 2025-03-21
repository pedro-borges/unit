package org.pcb.unit.solid

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance

class Cube<L : Unit<Distance>>(side: DistanceAmount<L>)
    : Cuboid<L>(side, side, side)
