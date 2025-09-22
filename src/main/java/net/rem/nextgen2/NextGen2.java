package net.rem.nextgen2;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.common.Mod;
import net.rem.nextgen2.client.renderer.CrimsonAutomatonRenderer;
import net.rem.nextgen2.entity.HoarderZombie;
import net.rem.nextgen2.registry.ModEntityTypes;
import net.rem.nextgen2.registry.ModItems;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.slf4j.Logger;
import net.rem.nextgen2.client.renderer.CrimsonAutomatonRenderer;





// The value here should match an entry in the META-INF/mods.toml file
@Mod(NextGen2.MODID)
public class NextGen2 {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "nextgen2";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public NextGen2() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerEntityAttributes);


        MinecraftForge.EVENT_BUS.register(this);



    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup completed for NextGen2");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.HOARDER_SPAWN_EGG);
        }
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.HOARDER.get(), HoarderZombie.createAttributes().build());


    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server starting with NextGen2");
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = NextGen2.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() ->
                    EntityRenderers.register(net.rem.nextgen2.entity.ModEntityTypes.CRIMSON_AUTOMATON.get(), CrimsonAutomatonRenderer::new));

        }
    }
}
