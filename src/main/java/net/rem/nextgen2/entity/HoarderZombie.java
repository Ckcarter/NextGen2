package net.rem.nextgen2.entity;

import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HoarderZombie extends Zombie {
    private static final int INVENTORY_SIZE = 36;
    private static final List<Item> LOOT_ITEMS = List.of(
            Items.DIAMOND,
            Items.EMERALD,
            Items.GOLD_INGOT,
            Items.IRON_INGOT,
            Items.COPPER_INGOT,
            Items.REDSTONE,
            Items.LAPIS_LAZULI,
            Items.AMETHYST_SHARD,
            Items.QUARTZ);

    private final SimpleContainer inventory = new SimpleContainer(INVENTORY_SIZE);

    public HoarderZombie(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        fillInventory();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes();
    }

    private void fillInventory() {
        for (int slot = 0; slot < inventory.getContainerSize(); ++slot) {
            Item item = LOOT_ITEMS.get(slot % LOOT_ITEMS.size());
            inventory.setItem(slot, new ItemStack(item, item.getMaxStackSize()));
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        if (this.level().isClientSide) {
            return;
        }

        for (int slot = 0; slot < inventory.getContainerSize(); ++slot) {
            ItemStack stack = inventory.removeItemNoUpdate(slot);
            if (!stack.isEmpty()) {
                this.spawnAtLocation(stack);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        CompoundTag inventoryTag = new CompoundTag();
        NonNullList<ItemStack> stacks = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
        for (int slot = 0; slot < inventory.getContainerSize(); ++slot) {
            stacks.set(slot, inventory.getItem(slot));
        }
        ContainerHelper.saveAllItems(inventoryTag, stacks, true);
        tag.put("HoarderInventory", inventoryTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        inventory.clearContent();
        if (tag.contains("HoarderInventory", Tag.TAG_COMPOUND)) {
            CompoundTag inventoryTag = tag.getCompound("HoarderInventory");
            NonNullList<ItemStack> stacks = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
            ContainerHelper.loadAllItems(inventoryTag, stacks);
            for (int slot = 0; slot < stacks.size(); ++slot) {
                inventory.setItem(slot, stacks.get(slot));
            }
        }

        if (inventory.isEmpty()) {
            fillInventory();
        }
    }
}