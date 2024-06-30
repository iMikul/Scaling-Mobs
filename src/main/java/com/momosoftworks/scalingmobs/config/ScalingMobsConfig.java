package com.momosoftworks.scalingmobs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScalingMobsConfig
{
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ScalingMobsConfig INSTANCE = new ScalingMobsConfig();

    private static final ForgeConfigSpec.DoubleValue mobHealthRate;
    private static final ForgeConfigSpec.DoubleValue mobHealthBase;
    private static final ForgeConfigSpec.DoubleValue maxHealth;

    private static final ForgeConfigSpec.DoubleValue mobDamageRate;
    private static final ForgeConfigSpec.DoubleValue mobDamageBase;
    private static final ForgeConfigSpec.DoubleValue maxDamage;

    private static final ForgeConfigSpec.DoubleValue mobPiercingRate;
    private static final ForgeConfigSpec.DoubleValue mobPiercingBase;
    private static final ForgeConfigSpec.DoubleValue maxPiercing;

    private static final ForgeConfigSpec.DoubleValue mobDropsRate;
    private static final ForgeConfigSpec.DoubleValue mobDropsBase;
    private static final ForgeConfigSpec.DoubleValue maxDrops;

    public static final ForgeConfigSpec.IntValue mobsStopBurningDay;

    private static final ForgeConfigSpec.BooleanValue exponential;

    static
    {
        exponential = BUILDER
                .comment("If true, mob stats will increase exponentially")
                .define("exponential", false);

        mobHealthRate = BUILDER
                .comment("The decimal amount that hostile mobs' health increase per day")
                .defineInRange("mobScaleRate", 0.03, 0.0, Double.MAX_VALUE);
        mobHealthBase = BUILDER
                .comment("The decimal amount of the mobs' base health in the beginning")
                .defineInRange("mobHealthBase", 1.0, 0.0, Double.MAX_VALUE);
        maxHealth = BUILDER
                .comment("The maximum amount that hostile mobs' damage can scale to")
                .defineInRange("maxScaling", Double.MAX_VALUE, 0.0, Double.MAX_VALUE);

        mobDamageRate = BUILDER
                .comment("The decimal amount that hostile mobs' damage increase per day")
                .defineInRange("mobScaleRate", 0.03, 0.0, Double.MAX_VALUE);
        mobDamageBase = BUILDER
                .comment("The decimal amount of the mobs' base damage in the beginning")
                .defineInRange("mobStatsBase", 1.0, 0.0, Double.MAX_VALUE);
        maxDamage = BUILDER
                .comment("The maximum amount that hostile mobs' damage can scale to")
                .defineInRange("maxScaling", Double.MAX_VALUE, 0.0, Double.MAX_VALUE);

        mobPiercingRate = BUILDER
                .comment("The decimal amount of increase to mobs' damage that ignores armor per day")
                .defineInRange("mobPiercingRate", 0.01, 0.0, Double.MAX_VALUE);
        mobPiercingBase = BUILDER
                .comment("The decimal amount of mobs' damage that ignores armor in the beginning")
                .defineInRange("mobPiercingBase", 0.1, 0.0, Double.MAX_VALUE);
        maxPiercing = BUILDER
                .comment("The maximum amount of increase to mobs' damage that ignores armor")
                .defineInRange("maxPiercing", Double.MAX_VALUE, 0.0, Double.MAX_VALUE);

        mobDropsRate = BUILDER
                .comment("The decimal amount of increase to mobs' drops per day")
                .defineInRange("mobDropsRate", 0.02, 0.0, Double.MAX_VALUE);
        mobDropsBase = BUILDER
                .comment("The decimal amount of mobs' drops in the beginning")
                .defineInRange("mobDropsBase", 1.0, 0.0, Double.MAX_VALUE);
        maxDrops = BUILDER
                .comment("The maximum amount of increase to mobs' drops")
                .defineInRange("maxDrops", Double.MAX_VALUE, 0.0, Double.MAX_VALUE);

        mobsStopBurningDay = BUILDER
                .comment("The day that hostile mobs stop burning")
                .defineInRange("mobsStopBurningDay", 7, 0, Integer.MAX_VALUE);

        SPEC = BUILDER.build();
    }

    // Getters
    public double getMobHealthRate()
    {
        return mobHealthRate.get();
    }
    public double getMobHealthBase()
    {
        return mobHealthBase.get();
    }
    public double getMobHealthMax()
    {
        return maxHealth.get();
    }

    public double getMobDamageRate()
    {
        return mobDamageRate.get();
    }
    public double getMobDamageBase()
    {
        return mobDamageBase.get();
    }
    public double getMobDamageMax()
    {
        return maxDamage.get();
    }

    public double getPiercingRate()
    {
        return mobPiercingRate.get();
    }
    public double getMaxPiercing()
    {
        return maxPiercing.get();
    }
    public double getPiercingBase()
    {
        return mobPiercingBase.get();
    }

    public double getMobDropsRate()
    {
        return mobDropsRate.get();
    }
    public double getMobDropsBase()
    {
        return mobDropsBase.get();
    }
    public double getMobDropsMax()
    {
        return maxDrops.get();
    }

    public int getMobsStopBurningDay()
    {
        return mobsStopBurningDay.get();
    }
    public boolean areStatsExponential()
    {
        return exponential.get();
    }

    // Setters
    public void setMobHealthRate(double rate)
    {
        mobHealthRate.set(rate);
    }
    public void setMobHealthBase(double base)
    {
        mobHealthBase.set(base);
    }
    public void setMobHealthMax(double max)
    {
        maxHealth.set(max);
    }

    public void setMobDamageRate(double rate)
    {
        mobDamageRate.set(rate);
    }
    public void setMobDamageBase(double base)
    {
        mobDamageBase.set(base);
    }
    public void setMobDamageMax(double max)
    {
        maxDamage.set(max);
    }

    public void setPiercingRate(double rate)
    {
        mobPiercingRate.set(rate);
    }
    public void setPiercingBase(double base)
    {
        mobPiercingBase.set(base);
    }
    public void setMaxPiercing(double max)
    {
        maxPiercing.set(max);
    }

    public void setMobDropsRate(double rate)
    {
        mobDropsRate.set(rate);
    }
    public void setMobDropsBase(double base)
    {
        mobDropsBase.set(base);
    }
    public void setMobDropsMax(double max)
    {
        maxDrops.set(max);
    }

    public void setMobsStopBurningDay(int day)
    {
        mobsStopBurningDay.set(day);
    }
    public void setExponentialStats(boolean log)
    {
        exponential.set(log);
    }

    public static void setup()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path csConfigPath = Paths.get(configPath.toAbsolutePath().toString(), "scaling_mobs");

        // Create the config folder
        try
        {
            Files.createDirectory(csConfigPath);
        }
        catch (Exception e)
        {
            // Do nothing
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "scaling_mobs/main.toml");
    }

    public static ScalingMobsConfig getInstance()
    {
        return INSTANCE;
    }

    public void save() {
        SPEC.save();
    }
}
