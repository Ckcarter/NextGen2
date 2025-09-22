package net.rem.nextgen2.event;

import net.rem.nextgen2.NextGen2;
import net.rem.nextgen2.entity.ModEntityTypes;
import net.rem.nextgen2.entity.custom.CrimsonAutomaton;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NextGen2.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventBusEvents {
    private ModEventBusEvents() {
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CRIMSON_AUTOMATON.get(), CrimsonAutomaton.createAttributes().build());
    }
}