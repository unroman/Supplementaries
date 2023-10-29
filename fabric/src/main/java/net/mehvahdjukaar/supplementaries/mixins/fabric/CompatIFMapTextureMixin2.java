package net.mehvahdjukaar.supplementaries.mixins.fabric;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.NativeImage;
import net.mehvahdjukaar.moonlight.api.misc.OptionalMixin;
import net.mehvahdjukaar.supplementaries.common.misc.ColoredMapHandler;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@OptionalMixin(value = "fabric.net.raphimc.immediatelyfast.injection.mixins.map_atlas_generation.MixinMapRenderer_MapTexture")
@Mixin(value = MapRenderer.MapInstance.class, priority = 1500)
public class CompatIFMapTextureMixin2 {

    @Shadow
    private MapItemSavedData data;

    @TargetHandler(
            mixin = "fabric.net.raphimc.immediatelyfast.injection.mixins.map_atlas_generation.MixinMapRenderer_MapTexture",
            name = "updateAtlasTexture"
    )
    @WrapOperation(method = "@MixinSquared:Handler",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/NativeImage;upload(IIIIIIIZZ)V"))
    public void supplementaries_IFupdateColoredTexture(NativeImage instance,
                                                       int level, int xOffset, int yOffset, int unpackSkipPixels, int unpackSkipRows, int width, int height, boolean mipmap, boolean autoClose,
                                                       Operation<Void> operation) {
        ColoredMapHandler.getColorData(this.data).processTexture(instance, xOffset, yOffset, this.data.colors);
        operation.call(instance, level, xOffset, yOffset, unpackSkipPixels, unpackSkipRows, width, height, true, autoClose);
    }

}
