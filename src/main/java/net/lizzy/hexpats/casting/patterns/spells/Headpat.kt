package net.lizzy.hexpats.casting.patterns.spells

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.paucal.common.misc.PatPat
import net.minecraft.entity.Entity

class Headpat : SpellAction {
    override val argc = 1
    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val target = args.getEntity(0)
        return SpellAction.Result(Spell(target),0, listOf(ParticleSpray.burst(env.castingEntity.pos,0.5,10)))
    }

    private data class Spell(val target: Entity) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val caster = env.castingEntity
            val player = env.world.getPlayerByUuid(caster.uuid)
            PatPat.onPat(player,env.world,env.castingHand,target,null)
        }
    }
}