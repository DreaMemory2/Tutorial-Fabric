package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.starlight.potato_core.register.*;

public class ModChineseGenerator extends FabricLanguageProvider {

    public ModChineseGenerator(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        // 方块语言配置
        builder.add(ModBlocks.WOOL_1X, "1重压缩毛巾");
        builder.add(ModBlocks.WOOL_2X, "2重压缩毛巾");
        builder.add(ModBlocks.WOOL_3X, "3重压缩毛巾");
        builder.add(ModBlocks.WOOL_4X, "4重压缩毛巾");
        builder.add(ModBlocks.WOOL_5X, "5重压缩毛巾");
        builder.add(ModBlocks.WOOL_6X, "6重压缩毛巾");
        builder.add(ModBlocks.WOOL_7X, "7重压缩毛巾");
        builder.add(ModBlocks.WOOL_8X, "8重压缩毛巾");
        builder.add(ModBlocks.WOOL_9X, "§C9重压缩毛巾");
        builder.add(ModBlocks.DRINK_MACHINE, "饮料制作机");
        builder.add(ModBlocks.APPLE_CAKE, "苹果蛋糕");
        builder.add(ModBlocks.CORN, "玉米种子");
        builder.add(ModBlocks.IRON_LOG, "铁原木");
        builder.add(ModBlocks.IRON_LEAVES, "铁树叶");
        builder.add(ModBlocks.IRON_SAPLING, "铁树苗");
        builder.add(ModBlocks.STONE, "生态石头");
        builder.add(ModBlocks.MAPLE_LEAVES, "枫树叶");
        // 白色木头语言配置系列
        builder.add(ModBlocks.WHITE_LOG, "白色原木");
        builder.add(ModBlocks.WHITE_WOOD, "白色橡木");
        builder.add(ModBlocks.STRIPPED_WHITE_LOG, "去皮白色原木");
        builder.add(ModBlocks.STRIPPED_WHITE_WOOD, "去皮白色橡木");
        // 白色木板语言配置系列
        builder.add(ModBlocks.WHITE_PLANKS, "白色木板");
        builder.add(ModBlocks.WHITE_PLANK_SLAB, "白色木板台阶");
        builder.add(ModBlocks.WHITE_PLANK_STAIRS, "白色木板楼梯");
        builder.add(ModBlocks.WHITE_PLANK_FENCE, "白色木板栅栏");
        builder.add(ModBlocks.WHITE_PLANK_FENCE_GATE, "白色木板栅栏门");
        builder.add(ModBlocks.WHITE_PLANK_DOOR, "白色木板门");
        builder.add(ModBlocks.WHITE_PLANK_TRAPDOOR, "白色木板活板门");
        builder.add(ModBlocks.WHITE_PLANK_PRESSURE_PLATE, "白色木板压力板");
        builder.add(ModBlocks.WHITE_PLANK_BUTTON, "白色木板按钮");

        // 物品语言配置
        builder.add(ModItems.CHICKEN_SOUP, "鸡汤");
        builder.add(ModItems.ENDER_PEARL, "末影珍珠");
        builder.add(ModItems.RAP_BASKETBALL, "唱Rap篮球");
        builder.add(ModItems.SMALL_BLACK, "小黑子");
        builder.add(ModItems.KUN_BOOK, "Kun的书");
        builder.add(ModItems.COLORS_BLACK, "五彩斑斓的黑");
        builder.add(ModFluids.PAREDISE_WATER_BUCKET, "桶装温泉水");
        builder.add(ModItems.FLAME_FLINT, "烈焰打火石");
        builder.add(ModItems.APPLE_JUICE, "苹果汁");
        builder.add(ModItems.CORN, "玉米");

        // 翻译组
        generatePotionTranslations(builder, "清爽药水");
        // 其他翻译信息
        builder.add("item.potato_core.galaxy_item.desc", "Galaxy-2023");
        builder.add("item.potato_core.dowsing_rod.no_found", "探测器未发现矿石");
        builder.add("item.potato_core.dowsing_rod.tooltip","矿石侦测器，右键可以检测方块下方是否有矿石");
        builder.add("itemGroup.potato_core.potato_item", "土豆核心的物品组");
        builder.add("itemGroup.potato_core.potato_block", "土豆核心的方块组");
        builder.add("itemGroup.potato_core.potato_building", "土豆核心的建筑方块");
        builder.add("potato_core.tootip.liquid.amount", "%s MB");
        builder.add("potato_core.tooltip.liquid.amount.with.capacity", "%s / %s MB");
        builder.add("effect.potato_core.fresh", "清爽");
    }

    private void generatePotionTranslations(TranslationBuilder builder, String potion) {
        builder.add("item.minecraft.potion.effect.fresh", potion);
        builder.add("item.minecraft.potion.effect.long_fresh", potion);
        builder.add("item.minecraft.potion.effect.strong_fresh", potion);
        builder.add("item.minecraft.splash_potion.effect.fresh", "喷溅型" + potion);
        builder.add("item.minecraft.splash_potion.effect.long_fresh", "喷溅型" + potion);
        builder.add("item.minecraft.splash_potion.effect.strong_fresh", "喷溅型" + potion);
        builder.add("item.minecraft.lingering_potion.effect.fresh", "滞留型" + potion);
        builder.add("item.minecraft.lingering_potion.effect.long_fresh", "滞留型" + potion);
        builder.add("item.minecraft.lingering_potion.effect.strong_fresh", "滞留型" + potion);
        builder.add("item.minecraft.tipped_arrow.effect.fresh", "清爽之箭");
        builder.add("item.minecraft.tipped_arrow.effect.long_fresh", "清爽之箭");
        builder.add("item.minecraft.tipped_arrow.effect.strong_fresh", "清爽之箭");
    }
}
