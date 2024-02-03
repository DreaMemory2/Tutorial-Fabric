package net.starlight.potato_core.register;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.starlight.potato_core.FirstMod;

public class ModVillagers {
    /**
     * 村民职业方块职业方块：蓝色混凝土
     */
    public static final PointOfInterestType BLUE_BLOCK = registerBlock("blue_block", Blocks.LIGHT_BLUE_CONCRETE);
    /**
     * 职业村民
     */
    public static final VillagerProfession BLUE_WORKER = registerProfession("blue_worker",
            RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(FirstMod.MOD_ID, "blue_block")));

    /**
     * @param name 职业村民的Id
     * @param type 职业方块的类型
     * @return 注册职业村民
     */
    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        // 设置成为职业村民的声音
        SoundEvent sound = SoundEvents.ENTITY_VILLAGER_WORK_ARMORER;
        return Registry.register(Registries.VILLAGER_PROFESSION, setId(name),
                // 注册职业村民构造器
                VillagerProfessionBuilder.create().id(setId(name)).workstation(type).workSound(sound).build());
    }

    /**
     * @param name  职业方块的ID
     * @param block 职业方块的中的方块ID
     * @return 注册职业方块
     */
    public static PointOfInterestType registerBlock(String name, Block block) {
        return PointOfInterestHelper.register(setId(name), 1, 1,
                ImmutableList.copyOf(block.getStateManager().getStates()));
    }

    /**
     * 实现村民交易系统
     */
    public static void registerTrades() {
        // 注册村民交易
        // 注册蓝色村民交易系统
        // registerVillagerOffers(职业，交易等级，交易详情)
        TradeOfferHelper.registerVillagerOffers(BLUE_WORKER, 1,
                factories -> {
                    // 添加一个交易选项内容
                    factories.add((entity, random) -> new TradeOffer(
                            // 输入所交易物品
                            new ItemStack(Items.DIAMOND, 5),
                            new ItemStack(Items.DIAMOND_HELMET),
                            // 最大交易次数，交易一次得到的经验奖励，激怒村民后涨价幅度
                            6, 2, 0.02f
                    ));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.DIAMOND, 8),
                            new ItemStack(Items.DIAMOND_CHESTPLATE, 1),
                            6, 2, 0.02F
                    ));
                });
        TradeOfferHelper.registerVillagerOffers(BLUE_WORKER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.DIAMOND, 7),
                            new ItemStack(Items.DIAMOND_LEGGINGS, 1),
                            6, 2, 0.02F
                    ));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.DIAMOND, 4),
                            new ItemStack(Items.DIAMOND_BOOTS, 1),
                            6, 2, 0.02F
                    ));
                });
    }

    public static Identifier setId(String name) {
        return new Identifier(FirstMod.MOD_ID, name);
    }

    public static void registerVillager() {
        FirstMod.LOGGER.debug("Registering villagers for" + FirstMod.MOD_ID);
    }
}
