
package net.rem.nextgen2.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rem.nextgen2.NextGen2;

public final class ModItems {
    private ModItems() {
    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NextGen2.MODID);

    public static final RegistryObject<Item> HOARDER_SPAWN_EGG = ITEMS.register(
            "hoarder_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    ModEntityTypes.HOARDER,
                    0x4A7A2A,
                    0x78C850,
                    new Item.Properties()));
}