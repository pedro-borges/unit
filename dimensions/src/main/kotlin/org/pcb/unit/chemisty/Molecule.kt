package org.pcb.unit.chemisty

import org.pcb.unit.amounts.MolarMassAmount
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Mole.MOLE

class Molecule(name: String, vararg elements: Pair<ChemicalElement, Int>)
    : ProvidesAtomicMass
{
    override val atomicMass =
        elements
            .map { it.first.atomicMass * it.second }
            .reduceOrNull(MolarMassAmount<GRAM, MOLE>::plus)
        ?: MolarMassAmount(0, GRAM to MOLE)
}