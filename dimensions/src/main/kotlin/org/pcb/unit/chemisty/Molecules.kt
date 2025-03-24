package org.pcb.unit.chemisty

import org.pcb.unit.chemisty.ChemicalElement.C
import org.pcb.unit.chemisty.ChemicalElement.H
import org.pcb.unit.chemisty.ChemicalElement.I
import org.pcb.unit.chemisty.ChemicalElement.N
import org.pcb.unit.chemisty.ChemicalElement.Na
import org.pcb.unit.chemisty.ChemicalElement.O
import org.pcb.unit.chemisty.ChemicalElement.S

object Molecules
{
    val C12H22O11 = Molecule("Sucrose", C to 12, H to 22, O to 11)
    val C3H6O = Molecule("Acetone", C to 3, H to 6, O to 1)
    val CO = Molecule("Carbon monoxide", C to 1, O to 1)
    val CO2 = Molecule("Carbon dioxide", C to 1, O to 2)
    val H2 = Molecule("Hydrogen", H to 2)
    val H2SO4 = Molecule("Sulphuric acid", H to 2, S to 1, O to 4)
    val CH4 = Molecule("Methane", C to 1, H to 4)
    val I2 = Molecule("Iodine", I to 2)
    val NaHCO3 = Molecule("Sodium bicarbonate", Na to 1, H to 1, C to 1, O to 3)
    val N2 = Molecule("Nitrogen", N to 2)
    val N2O = Molecule("Nitrous oxide", N to 2, O to 1)
    val NH3 = Molecule("Ammonia", N to 1, H to 3)
    val NO2 = Molecule("Nitrogen dioxide", N to 1, O to 2)
    val O2 = Molecule("Oxygen", O to 2)
    val O3 = Molecule("Ozone", O to 3)
}