package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.register.ModBlocks;

import java.util.function.BiConsumer;

public class ModBlockLootTables extends SimpleFabricLootTableProvider {

    public ModBlockLootTables(FabricDataOutput output) {
        super(output, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        // 与树相关掉落物配置
        exporter.accept(registerLootTables("iron_leaves"), createLeavesLootTables());
        exporter.accept(registerLootTables("iron_sapling"), createBlockLootTables(ModBlocks.IRON_SAPLING));
        exporter.accept(registerLootTables("iron_log"), createBlockLootTables(ModBlocks.IRON_LOG));
        // Crystal Grass Loot Tables
        exporter.accept(registerLootTables("crystal_grass"), createGrassLootTables());
        // 默认设置
        exporter.accept(registerLootTables("crystal_dirt"), createBlockLootTables(ModBlocks.CRYSAL_DIRT));
    }

    /**
     * <p>普通方块掉落物方法</p>
     */
    private LootTable.Builder createBlockLootTables(Block block) {
        return LootTable.builder().pool(LootPool.builder()
                .bonusRolls(ConstantLootNumberProvider.create(0.0F))
                .conditionally(SurvivesExplosionLootCondition.builder())
                .with(ItemEntry.builder(block.asItem()))
                .rolls(ConstantLootNumberProvider.create(1.0F)));
    }

    private LootTable.Builder createLeavesLootTables() {
        return LootTable.builder().pool(LootPool.builder()
                // 树苗和树叶掉落物配置
                .bonusRolls(ConstantLootNumberProvider.create(0.0F))

                .with(AlternativeEntry.builder(
                        ItemEntry.builder(ModBlocks.IRON_LEAVES.asItem())
                                .conditionally(AlternativeLootCondition.builder(

                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS)),

                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(
                                                new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))
                                )),

                        ItemEntry.builder(ModBlocks.IRON_SAPLING.asItem())

                                .conditionally(SurvivesExplosionLootCondition.builder())

                                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.05F, 0.0625F, 0.083333336F, 0.1F))
                ))

                .rolls(ConstantLootNumberProvider.create(1.0F))

        ).pool(LootPool.builder()
                // 木棍战利品设置，如橡木树叶中掉落树枝
                .bonusRolls(ConstantLootNumberProvider.create(0.0F))
                .conditionally(InvertedLootCondition.builder(
                        AlternativeLootCondition.builder(

                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS)),

                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(
                                        new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))
                        )
                ))

                .with(ItemEntry.builder(Items.STICK)

                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))

                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F), false))

                        .apply(ExplosionDecayLootFunction.builder())
                )
                .rolls(ConstantLootNumberProvider.create(1.0F))
        ).pool(LootPool.builder()
                // 果实战利品设置，如橡木树叶中掉落苹果
                .bonusRolls(ConstantLootNumberProvider.create(0.0F))
                .conditionally(InvertedLootCondition.builder(
                        AlternativeLootCondition.builder(

                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS)),

                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(
                                        new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))
                        )
                ))

                .with(ItemEntry.builder(Items.IRON_INGOT)

                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))

                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F), false))

                        .apply(ExplosionDecayLootFunction.builder())
                )
                .rolls(ConstantLootNumberProvider.create(1.0F))
        );
    }

    private LootTable.Builder createGrassLootTables() {
        return LootTable.builder().pool(LootPool.builder()
                .bonusRolls(ConstantLootNumberProvider.create(0.0F))
                .with(AlternativeEntry.builder(
                        // 使用精准采集挖掘草方块时，破坏后获得草方块
                        ItemEntry.builder(ModBlocks.CRYSAL_GRASS_BLOCK.asItem())
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(
                                        new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))),
                        // 没有精准采集挖掘草方块时，破坏后获得泥土
                        ItemEntry.builder(ModBlocks.CRYSAL_DIRT.asItem())
                                .conditionally(SurvivesExplosionLootCondition.builder())))
                .rolls(ConstantLootNumberProvider.create(1.0F))
        );
    }

    private Identifier registerLootTables(String id) {
        return new Identifier(FirstMod.MOD_ID, "blocks/" + id);
    }
}
