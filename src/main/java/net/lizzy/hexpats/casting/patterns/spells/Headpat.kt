package net.lizzy.hexpats.casting.patterns.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.paucal.common.ModStats
import at.petrak.paucal.common.misc.PatPat
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

class Headpat : SpellAction {
    override val argc = 1
    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val target = args.getEntity(0)
        return SpellAction.Result(Spell(target),0, listOf())
    }

    private data class Spell(val target: Entity) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val caster = env.castingEntity
            val world = env.world
            var player : PlayerEntity? = null
            if (caster != null) {
                player = env.world.getPlayerByUuid(caster.uuid)
            }
            if (world is ServerWorld) {
                val pos = target.eyePos
                world.spawnParticles(ParticleTypes.HEART, pos.x, pos.y + 0.5, pos.z, 1, 0.0, 0.0, 0.0, 0.1)
            } else {
                player?.swingHand(env.castingHand)
            }

            PatPat.tryPlayPatSound(target.uuid, target.eyePos, player, env.world)
            player?.incrementStat(ModStats.PLAYERS_PATTED)
            env.world.getPlayerByUuid(target.uuid)?.incrementStat(ModStats.HEADPATS_GOTTEN)
            if (target.isOnFire) {
                target.extinguish()
                if (world is ServerWorld) {
                    val pos = target.eyePos
                    world.spawnParticles(ParticleTypes.SMOKE, pos.x, pos.y + 0.5, pos.z, 10, 0.0, 0.0, 0.0, 0.1)
                }

                world.playSound(
                    player,
                    target.x,
                    target.y,
                    target.z,
                    SoundEvents.BLOCK_FIRE_EXTINGUISH,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
                )
            }
        }
    }
}