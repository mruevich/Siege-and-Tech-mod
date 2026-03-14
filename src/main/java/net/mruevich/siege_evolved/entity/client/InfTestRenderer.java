package net.mruevich.siege_evolved.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.entity.custom.InfTestEntity;

public class InfTestRenderer extends MobRenderer<InfTestEntity, inf_test<InfTestEntity>> {
    public InfTestRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new inf_test<>(pContext.bakeLayer(ModModelLayers.INF_TEST_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(InfTestEntity pEntity) {
        return new ResourceLocation(SiegeEvolved.MODID,"textures/entity/inf_test.png");
    }

    @Override
    public void render(InfTestEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
