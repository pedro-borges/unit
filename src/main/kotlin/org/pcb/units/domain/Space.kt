package org.pcb.units.domain

interface Space : Domain
{
    operator fun div(time: Time): Speed
}