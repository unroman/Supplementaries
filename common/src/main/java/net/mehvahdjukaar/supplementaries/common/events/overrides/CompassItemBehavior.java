package net.mehvahdjukaar.supplementaries.common.events.overrides;


import net.mehvahdjukaar.supplementaries.common.block.blocks.GlobeBlock;
import net.mehvahdjukaar.supplementaries.configs.ClientConfigs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

class CompassItemBehavior implements ItemUseOverride {

    @Override
    public boolean isEnabled() {
        return ClientConfigs.Tweaks.COMPASS_CLICK.get();
    }

    @Override
    public boolean appliesToItem(Item item) {
        return item == Items.COMPASS;
    }

    @Override
    public InteractionResult tryPerformingAction(Level world, Player player, InteractionHand hand,
                                                 ItemStack stack, BlockHitResult hit) {
        if (world.isClientSide) {
            GlobeBlock.displayCurrentCoordinates(world, player, player.blockPosition());
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
