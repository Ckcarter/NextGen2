package net.rem.nextgen2.client.renderer;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rem.nextgen2.entity.custom.CrimsonAutomaton;

public class CrimsonAutomatonRenderer extends MobRenderer<CrimsonAutomaton, IronGolemModel<CrimsonAutomaton>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("textures/entity/iron_golem/iron_golem.png");

    public CrimsonAutomatonRenderer(EntityRendererProvider.Context context) {
        super(context, new IronGolemModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CrimsonAutomaton entity) {
        return TEXTURE;
    }
}