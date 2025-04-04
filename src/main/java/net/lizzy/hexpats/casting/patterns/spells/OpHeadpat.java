package net.lizzy.hexpats.casting.patterns.spells;

import at.petrak.hexcasting.api.casting.castables.SpellAction;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.*;
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster;
import at.petrak.paucal.common.misc.PatPat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OpHeadpat implements SpellAction {
    public static final OpHeadpat INSTANCE = new OpHeadpat();

    @Override
    public int getArgc() { return 1; }

    @Override
    public long getMediaCost() { return 0; }

    @NotNull
    @Override
    public List<Iota> execute(@NotNull List<? extends Iota> list, @NotNull CastingEnvironment castingEnvironment) {

        Entity player = OperatorUtils.getEntity(list, 0, getArgc());
        LivingEntity caster = castingEnvironment.getCastingEntity();
        World world = castingEnvironment.getWorld();
        Hand hand = castingEnvironment.getCastingHand();

        if (caster.isPlayer()) {
            PlayerEntity casterPlayer= world.getPlayerByUuid(caster.getUuid());
            PatPat.onPat(casterPlayer, world, hand, player, null);
        }
        else {
            throw new MishapBadCaster();
        }

        return List.of();
    }
}
