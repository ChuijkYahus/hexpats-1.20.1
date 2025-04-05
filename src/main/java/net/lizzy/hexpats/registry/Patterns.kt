package net.lizzy.hexpats.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import net.lizzy.hexpats.Hexpats
import net.lizzy.hexpats.casting.patterns.spells.Headpat
import net.minecraft.registry.Registry

object Patterns {
    @JvmStatic
    fun register() {
        Registry.register<ActionRegistryEntry>(
            HexActions.REGISTRY,
            Hexpats.MOD_ID + ":" + "headpat",
            ActionRegistryEntry(HexPattern.fromAngles("weweeeee", HexDir.EAST), Headpat())
        )
    }
}
