package com.teamabode.cave_enhancements;

import com.teamabode.cave_enhancements.core.platform.RegistryHelper;
import com.teamabode.cave_enhancements.core.registry.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CaveEnhancements {
    public static final String MODID = "cave_enhancements";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static void init() {
        ModSounds.init();
        ModParticles.init();
        ModEntities.init();
        ModItems.init();
        ModBlocks.init();
        ModBlockEntities.init();
        ModEffects.init();
        ModPotions.init();
        RegistryHelper.registerBannerPattern("goop");
    }

    public static void queuedWork() {
        PotionBrewing.addMix(Potions.AWKWARD, ModItems.ROSE_QUARTZ.get(), ModPotions.REVERSAL.get());
        PotionBrewing.addMix(ModPotions.REVERSAL.get(), Items.REDSTONE, ModPotions.LONG_REVERSAL.get());
    }

    public static void addPotionTooltip(ItemStack itemStack, TooltipFlag tooltipFlag, List<Component> componentList) {
        if (PotionUtils.getPotion(itemStack) == ModPotions.REVERSAL.get() || PotionUtils.getPotion(itemStack) == ModPotions.LONG_REVERSAL.get()) {

            int i = 0;
            for (Component component : componentList) {
                if (!component.contains(Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE))) {
                    i++;
                    continue;
                }
                break;
            }
            componentList.add(i+1, Component.translatable("gameplay.reversal").withStyle(ChatFormatting.BLUE));
        }
    }
}
