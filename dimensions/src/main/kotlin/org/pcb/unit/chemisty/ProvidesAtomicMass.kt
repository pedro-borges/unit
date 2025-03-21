package org.pcb.unit.chemisty

import org.pcb.unit.amounts.MolarMassAmount
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Mole.MOLE

interface ProvidesAtomicMass
{
    val atomicMass: MolarMassAmount<GRAM, MOLE>
}