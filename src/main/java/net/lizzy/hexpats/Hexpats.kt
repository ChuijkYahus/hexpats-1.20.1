package net.lizzy.hexpats

import net.fabricmc.api.ModInitializer
import net.lizzy.hexpats.registry.Patterns.register
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Hexpats : ModInitializer {
    override fun onInitialize() {
        LOGGER.info("Hello Fabric world!")

        register()
    }

    companion object {
        const val MOD_ID: String = "hexpats"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }
}