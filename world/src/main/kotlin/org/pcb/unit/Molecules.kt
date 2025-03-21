package org.pcb.unit

import org.pcb.unit.amounts.chemistry.Molecule
import org.pcb.unit.chemisty.ChemicalElement.C
import org.pcb.unit.chemisty.ChemicalElement.H
import org.pcb.unit.chemisty.ChemicalElement.I
import org.pcb.unit.chemisty.ChemicalElement.N
import org.pcb.unit.chemisty.ChemicalElement.O

object Molecules
{
    val CO = Molecule(C to 1, O to 1)
    val CO2 = Molecule(C to 1, O to 2)
    val H2 = Molecule(H to 2)
    val CH4 = Molecule(C to 1, H to 4)
    val I2 = Molecule(I to 2)
    val N2 = Molecule(N to 2)
    val N2O = Molecule(N to 2, O to 1)
    val NH3 = Molecule(N to 1, H to 3)
    val NO2 = Molecule(N to 1, O to 2)
    val O2 = Molecule(O to 2)
    val O3 = Molecule(O to 3)
}