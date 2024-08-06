package com.momosoftworks.scalingmobs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    private static final ForgeConfigSpec.DoubleValue mobSpeedRate;
    private static final ForgeConfigSpec.DoubleValue mobSpeedBase;
    private static final ForgeConfigSpec.DoubleValue maxSpeed;


    private static final ForgeConfigSpec.DoubleValue mobPiercingRate;
    private static final ForgeConfigSpec.DoubleValue mobPiercingBase;
    private static final ForgeConfigSpec.DoubleValue maxPiercing;

    private static final ForgeConfigSpec.DoubleValue mobDropsRate;
    private static final ForgeConfigSpec.DoubleValue mobDropsBase;
    private static final ForgeConfigSpec.DoubleValue maxDrops;

    public static final ForgeConfigSpec.IntValue mobsStopBurningDay;

    private static final ForgeConfigSpec.BooleanValue exponential;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> mobWhitelist;

    static
    {
        BUILDER.push("Misc");

        exponential = BUILDER
                .comment("If true, mob stats will increase exponentially")
                .define("Use Exponential Scaling", false);

        mobsStopBurningDay = BUILDER
                .comment("After this day in the Minecraft world, all hostile mobs will be immune to burning in daylight")
                .defineInRange("Mobs Stop Burning on Day", 7, 0, Integer.MAX_VALUE);

        mobWhitelist = BUILDER
                .comment("A list of mobs that will scale, even if they're not monsters")
                .defineList("Mob Whitelist", List.of(),
                            element -> element instanceof String);  BUILDER.pop();

        BUILDER.push("Health");

        mobHealthRate = BUILDER
                .comment("The decimal amount that hostile mobs' health increase per day")
                .defineInRange("Health Scale Rate", 0.03, 0.0, Double.POSITIVE_INFINITY);
        mobHealthBase = BUILDER
                .comment("The decimal amount of the mobs' base health in the beginning")
                .defineInRange("Mob Health Base", 1.0, 0.0, Double.POSITIVE_INFINITY);
        maxHealth = BUILDER
                .comment("The maximum amount that hostile mobs' damage can scale to")
                .defineInRange("Max Scaled Health", Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY);

        BUILDER.pop();
        BUILDER.push("Damage");

        mobDamageRate = BUILDER
                .comment("The decimal amount that hostile mobs' damage increase per day")
                .defineInRange("Damage Scale Rate", 0.03, 0.0, Double.POSITIVE_INFINITY);
        mobDamageBase = BUILDER
                .comment("The decimal amount of the mobs' base damage in the beginning")
                .defineInRange("Mob Damage Base", 1.0, 0.0, Double.POSITIVE_INFINITY);
        maxDamage = BUILDER
                .comment("The maximum amount that hostile mobs' damage can scale to")
                .defineInRange("Max Scaled Damage", Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY);

        BUILDER.pop();
        BUILDER.push("Speed");

        mobSpeedRate = BUILDER
                .comment("The decimal amount that hostile mobs' speed increase per day")
                .defineInRange("Speed Scale Rate", 0.005, 0.0, Double.POSITIVE_INFINITY);

        mobSpeedBase = BUILDER
                .comment("The decimal amount of the mobs' base speed in the beginning")
                .defineInRange("Mob Speed Base", 1.0, 0.0, Double.POSITIVE_INFINITY);

        maxSpeed = BUILDER
                .comment("The maximum amount that hostile mobs' speed can scale to")
                .defineInRange("Max Scaled Speed", 1.5, 0.0, Double.POSITIVE_INFINITY);

        BUILDER.pop();
        BUILDER.push("Armor Piercing");

        mobPiercingRate = BUILDER
                .comment("The decimal amount of increase to mobs' damage that ignores armor per day")
                .defineInRange("Armor Piercing Scale Rate", 0.01, 0.0, Double.POSITIVE_INFINITY);
        mobPiercingBase = BUILDER
                .comment("The decimal amount of mobs' damage that ignores armor in the beginning")
                .defineInRange("Armor Piercing Base", 0.1, 0.0, Double.POSITIVE_INFINITY);
        maxPiercing = BUILDER
                .comment("The maximum amount of increase to mobs' damage that ignores armor")
                .defineInRange("Max Scaled Armor Piercing", 1, 0.0, 1.0);

        BUILDER.pop();
        BUILDER.push("Drops");

        mobDropsRate = BUILDER
                .comment("The decimal amount of increase to mobs' drops per day")
                .defineInRange("Mob Drops Scaling Rate", 0.02, 0.0, Double.POSITIVE_INFINITY);
        mobDropsBase = BUILDER
                .comment("The decimal amount of mobs' drops in the beginning")
                .defineInRange("Mob Drops Base", 1.0, 0.0, Double.POSITIVE_INFINITY);
        maxDrops = BUILDER
                .comment("The maximum amount of increase to mobs' drops")
                .defineInRange("Max Scaled Mob Drops", 400, 0.0, Double.POSITIVE_INFINITY);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    // Getters
    public List<? extends String> getMobWhitelist()
    {   return mobWhitelist.get();
    }

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

    public double getMobSpeedRate()
    {
        return mobSpeedRate.get();
    }

    public double getMobSpeedBase()
    {
        return mobSpeedBase.get();
    }

    public double getMobSpeedMax()
    {
        return maxSpeed.get();
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

    public void setMobSpeedRate(double rate)
    {
        mobSpeedRate.set(rate);
    }
    public void setMobSpeedBase(double base)
    {
        mobSpeedBase.set(base);
    }
    public void setMobSpeedMax(double max)
    {
        maxSpeed.set(max);
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
