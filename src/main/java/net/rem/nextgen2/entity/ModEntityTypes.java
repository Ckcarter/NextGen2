package net.rem.nextgen2.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rem.nextgen2.NextGen2;
import net.rem.nextgen2.entity.custom.CrimsonAutomaton;

public final class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NextGen2.MODID);

    public static final RegistryObject<EntityType<CrimsonAutomaton>> CRIMSON_AUTOMATON =
            ENTITY_TYPES.register("crimson_automaton",
                    () -> EntityType.Builder.of(CrimsonAutomaton::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(new ResourceLocation(NextGen2.MODID, "crimson_automaton").toString()));

    private ModEntityTypes() {
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}