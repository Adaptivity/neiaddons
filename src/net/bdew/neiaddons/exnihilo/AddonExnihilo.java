/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/neiaddons
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/neiaddons/master/MMPL-1.0.txt
 */

package net.bdew.neiaddons.exnihilo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.bdew.neiaddons.BaseAddon;
import net.bdew.neiaddons.NEIAddons;
import net.bdew.neiaddons.Utils;
import net.bdew.neiaddons.exnihilo.proxies.HammerRegistryProxy;
import net.bdew.neiaddons.exnihilo.proxies.SmashableProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = NEIAddons.modid + "|ExNihilo", name = "NEI Addons: Ex Nihilo", version = "NEIADDONS_VER", dependencies = "after:crowley.skyblock")
public class AddonExnihilo extends BaseAddon {

    public static List<ItemStack> hammers = new ArrayList<ItemStack>();
    public static Class<? extends Item> clsBaseHammer;

    @Mod.Instance(NEIAddons.modid + "|ExNihilo")
    public static AddonExnihilo instance;

    @Override
    public String getName() {
        return "Ex Nihilo";
    }

    @Override
    public boolean checkSide(Side side) {
        return side.isClient();
    }

    @Override
    public String[] getDependencies() {
        return new String[]{"crowley.skyblock"};
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        doPreInit(ev);
    }

    @Override
    public void init(Side side) throws Exception {
        SmashableProxy.init();
        HammerRegistryProxy.init();

        clsBaseHammer = Utils.getAndCheckClass("exnihilo.items.hammers.ItemHammerBase", Item.class);

        hammers.add(new ItemStack(Utils.getAndCheckStaicField("exnihilo.Items", "HammerWood", Item.class)));
        hammers.add(new ItemStack(Utils.getAndCheckStaicField("exnihilo.Items", "HammerStone", Item.class)));
        hammers.add(new ItemStack(Utils.getAndCheckStaicField("exnihilo.Items", "HammerIron", Item.class)));
        hammers.add(new ItemStack(Utils.getAndCheckStaicField("exnihilo.Items", "HammerGold", Item.class)));
        hammers.add(new ItemStack(Utils.getAndCheckStaicField("exnihilo.Items", "HammerDiamond", Item.class)));

        active = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient() {
        AddonExnihiloClient.load();
    }
}
