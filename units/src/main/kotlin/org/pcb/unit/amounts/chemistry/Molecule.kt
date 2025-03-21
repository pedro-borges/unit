package org.pcb.unit.amounts.chemistry

import org.pcb.unit.amounts.MolarMassAmount
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Mole.MOLE
import org.pcb.unit.chemisty.ChemicalElement
import org.pcb.unit.chemisty.ProvidesAtomicMass

class Molecule(vararg elements: Pair<ChemicalElement, Int>)
    : ProvidesAtomicMass
{
    override val atomicMass =
        elements
            .map { it.first.atomicMass * it.second }
            .reduceOrNull(MolarMassAmount<GRAM, MOLE>::plus)
        ?: MolarMassAmount(0, GRAM to MOLE)
}