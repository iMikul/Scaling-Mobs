package com.momosoftworks.scalingmobs.events;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.momosoftworks.scalingmobs.config.ScalingMobsConfig;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

@Mod.EventBusSubscriber
public class MonsterEvents
{
    @SubscribeEvent
    public static void onMobSpawn(EntityJoinLevelEvent event)
    {
        if (event.getEntity() instanceof Monster mob)
        {
            int currentDay = (int) (event.getLevel().getDayTime() / 24000L);

            AttributeInstance maxHealth = mob.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance damage = mob.getAttribute(Attributes.ATTACK_DAMAGE);
            AttributeInstance speed = mob.getAttribute(Attributes.MOVEMENT_SPEED);

            float currentHealthPercent = mob.getHealth() / mob.getMaxHealth();

            if (damage != null && maxHealth != null && speed != null)
            {
                double baseHealth = ScalingMobsConfig.getInstance().getMobHealthBase();
                double healthRate = ScalingMobsConfig.getInstance().getMobHealthRate();
                double healthMax = ScalingMobsConfig.getInstance().getMobHealthMax();

                double damageRate = ScalingMobsConfig.getInstance().getMobDamageRate();
                double damageMax = ScalingMobsConfig.getInstance().getMobDamageMax();
                double baseDamage = ScalingMobsConfig.getInstance().getMobDamageBase();

                double speedRate = ScalingMobsConfig.getInstance().getMobSpeedRate();
                double speedMax = ScalingMobsConfig.getInstance().getMobSpeedMax();
                double baseSpeed = ScalingMobsConfig.getInstance().getMobSpeedBase();

                boolean exponential = ScalingMobsConfig.getInstance().areStatsExponential();

                maxHealth.addTransientModifier(new AttributeModifier("ScalingMobs:HealthBase", baseHealth - 1, AttributeModifier.Operation.MULTIPLY_BASE));
                maxHealth.addTransientModifier(new AttributeModifier("ScalingMobs:Health", Math.min(healthMax - 1, getStatIncrease(healthRate, currentDay, exponential)), AttributeModifier.Operation.MULTIPLY_TOTAL));

                damage.addTransientModifier(new AttributeModifier("ScalingMobs:DamageBase", baseDamage - 1, AttributeModifier.Operation.MULTIPLY_BASE));
                damage.addTransientModifier(new AttributeModifier("ScalingMobs:Damage", Math.min(damageMax - 1, getStatIncrease(damageRate, currentDay, exponential)), AttributeModifier.Operation.MULTIPLY_TOTAL));

                speed.addTransientModifier(new AttributeModifier("ScalingMobs:SpeedBase", baseSpeed - 1, AttributeModifier.Operation.MULTIPLY_BASE));
                speed.addTransientModifier(new AttributeModifier("ScalingMobs:Speed", Math.min(speedMax - 1, getStatIncrease(speedRate, currentDay, exponential)), AttributeModifier.Operation.MULTIPLY_TOTAL));

                mob.setHealth(mob.getMaxHealth() * currentHealthPercent);
            }
        }
    }

    public static double getMultipliedStat(double stat, double base, double rate, double max, int day, boolean exponential)
    {
        if (exponential)
        {
            return Math.min(max, (stat * base) * Math.pow(1 + rate, day));
        }
        else
        {
            return Math.min(max, (stat * base) * (1 + (day * rate)));
        }
    }

    // This method returns the increase for a stat, meant for attribute modifiers
    // i.e. if the method returns 0.5, that's a 50% increase
    public static double getStatIncrease(double rate, int day, boolean exponential)
    {
        if (exponential)
        {
            return Math.pow(1 + rate, day) - 1;
        }
        else
        {
            return (day * rate);
        }
    }

    // Piercing Damage
    @SubscribeEvent
    public static void onMobDamage(LivingDamageEvent event)
    {
        if (event.getEntity() instanceof Player && event.getSource().getEntity() instanceof Monster)
        {
            int currentDay = (int) (event.getEntity().level().getDayTime() / 24000L);
            double scaleRate = ScalingMobsConfig.getInstance().getPiercingRate();
            double maxPiercing = ScalingMobsConfig.getInstance().getMaxPiercing();
            float damage = event.getAmount();

            float normalDamage = (float) (damage * Math.max(0, 1 - currentDay * Math.min(scaleRate, maxPiercing)));
            float armorPierceDamage = (float) Math.min(damage, damage * (currentDay * Math.min(scaleRate, maxPiercing)));

            event.setAmount(normalDamage);
            event.getEntity().setHealth(event.getEntity().getHealth() - armorPierceDamage);
        }
    }

    // Multiply mob drops
    @SubscribeEvent
    public static void onMobDrop(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof Monster)
        {
            double dropRate = ScalingMobsConfig.getInstance().getMobDropsRate();
            double dropBase = ScalingMobsConfig.getInstance().getMobDropsBase();
            double maxDrops = ScalingMobsConfig.getInstance().getMobDropsMax();
            int currentDay = (int) (event.getEntity().level().getDayTime() / 24000L);

            double multiplier = getMultipliedStat(1 + event.getLootingLevel(), dropBase, dropRate, maxDrops, currentDay, false);
            int repetitions = (int) Math.floor(multiplier);
            double remainder = multiplier - repetitions;

            if (Math.random() < remainder) repetitions++;

            try
            {
                Method dropLoot = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "func_213354_a", DamageSource.class, boolean.class);
                Method dropSpecialItems = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "func_213333_a", DamageSource.class, int.class, boolean.class);

                for (int i = 0; i < repetitions; i++)
                {
                    dropLoot.invoke(event.getEntity(), event.getSource(), event.isRecentlyHit());
                    dropSpecialItems.invoke(event.getEntity(), event.getSource(), event.getLootingLevel(), event.isRecentlyHit());
                }
            }
            catch (Exception e) {}
        }
    }
}
