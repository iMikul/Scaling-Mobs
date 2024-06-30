package com.momosoftworks.scalingmobs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.momosoftworks.scalingmobs.config.ScalingMobsConfig;
import com.momosoftworks.scalingmobs.init.CommandInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("scalingmobs")
public class ScalingMobs
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public ScalingMobs()
    {
        MinecraftForge.EVENT_BUS.register(this);
        ScalingMobsConfig.setup();
    }

    @SubscribeEvent
    public void onCommandRegister(final RegisterCommandsEvent event)
    {
        CommandInit.registerCommands(event);
    }
}
