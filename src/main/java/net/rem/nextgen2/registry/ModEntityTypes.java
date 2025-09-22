package net.rem.nextgen2.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.rem.nextgen2.NextGen2;
import net.rem.nextgen2.entity.HoarderZombie;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntityTypes {
    private ModEntityTypes() {
    }

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NextGen2.MODID);

    public static final RegistryObject<EntityType<HoarderZombie>> HOARDER = ENTITY_TYPES.register(
            "hoarder",
            () -> EntityType.Builder
                    .of(HoarderZombie::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(NextGen2.MODID, "hoarder").toString()));
}