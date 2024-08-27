package org.pcb.units.domain

interface Time : Domain
{
    operator fun div(space: Space): Pace
}