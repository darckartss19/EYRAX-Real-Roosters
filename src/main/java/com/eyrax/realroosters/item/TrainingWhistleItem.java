package com.eyrax.realroosters.item;

import com.eyrax.realroosters.entity.CombatStance;
import com.eyrax.realroosters.entity.HeritageChicken;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TrainingWhistleItem extends Item {
    public TrainingWhistleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof HeritageChicken chicken) {
            return command(player, chicken);
        }
        return InteractionResult.PASS;
    }

    public InteractionResult command(Player player, HeritageChicken chicken) {
        if (chicken.isBaby() || chicken.isHen()) {
            if (!player.level().isClientSide) {
                player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.roosters_only")
                        .withStyle(ChatFormatting.RED), true);
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }

        if (chicken.isTame() && !chicken.isOwnedBy(player) && !player.getAbilities().instabuild) {
            if (!player.level().isClientSide) {
                player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.not_owner")
                        .withStyle(ChatFormatting.RED), true);
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }

        if (!player.level().isClientSide) {
            CombatStance next = chicken.getStance().next();
            chicken.setStance(next);
            chicken.setOrderedToSit(next == CombatStance.PASSIVE);
            chicken.setTarget(null);
            chicken.getNavigation().stop();
            player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.stance_changed",
                    Component.translatable("stance.eyrax_real_roosters." + next.serializedName()))
                    .withStyle(ChatFormatting.YELLOW), true);
        }
        return InteractionResult.sidedSuccess(player.level().isClientSide);
    }
}
