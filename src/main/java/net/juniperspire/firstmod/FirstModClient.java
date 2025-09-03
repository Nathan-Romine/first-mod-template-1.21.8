package net.juniperspire.firstmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;

public class FirstModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult)->
        {

            player.sendMessage(Text.literal("hello"), false);
            BlockState targetedBlock = world.getBlockState(hitResult.getBlockPos());
            if (targetedBlock.getBlock() instanceof WallSignBlock)
            {
                player.sendMessage(Text.literal("I saw the sign"), true);
                player.sendMessage(Text.literal(targetedBlock.get(WallSignBlock.FACING).asString()), false);
                if (targetedBlock.get(WallSignBlock.FACING) == Direction.EAST)
                    player.sendMessage(Text.literal("It's facing east"), false);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;

        });
    }
}
