package net.newfrontiercraft.nfc.compat.ami;

import net.glasslauncher.mods.alwaysmoreitems.api.*;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.compat.ami.brickoven.BrickOvenShapedRecipeCategory;
import net.newfrontiercraft.nfc.compat.ami.brickoven.BrickOvenShapedRecipeHander;
import net.newfrontiercraft.nfc.compat.ami.brickoven.BrickOvenShapelessRecipeCategory;
import net.newfrontiercraft.nfc.compat.ami.brickoven.BrickOvenShapelessRecipeHandler;
import net.newfrontiercraft.nfc.compat.ami.carpentry.CarpentryRecipeCategory;
import net.newfrontiercraft.nfc.compat.ami.carpentry.CarpentryRecipeHandler;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.registry.BrickOvenManager;
import net.newfrontiercraft.nfc.registry.CarpentryRecipes;

public class AMICompat implements ModPluginProvider {

    @Override
    public String getName() {
        return "New Frontier Craft";
    }

    @Override
    public Identifier getId() {
        return BlockListener.MOD_ID.id("nfc");
    }

    @Override
    public void onAMIHelpersAvailable(AMIHelpers amiHelpers) {

    }

    @Override
    public void onItemRegistryAvailable(ItemRegistry itemRegistry) {

    }

    @Override
    public void register(ModRegistry registry) {
        registry.addRecipeCategories(new BrickOvenShapelessRecipeCategory());
        registry.addRecipeCategories(new BrickOvenShapedRecipeCategory());
        registry.addRecipeHandlers(new BrickOvenShapelessRecipeHandler());
        registry.addRecipeHandlers(new BrickOvenShapedRecipeHander());
        registry.addRecipes(BrickOvenManager.getInstance().getShapelessRecipes());
        registry.addRecipes(BrickOvenManager.getInstance().getShapedRecipes());

        registry.addRecipeCategories(new CarpentryRecipeCategory());
        registry.addRecipeHandlers(new CarpentryRecipeHandler());
        registry.addRecipes(CarpentryRecipes.carpentry().getRecipes());
    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry) {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound recipe) {
        return null;
    }

    @Override
    public void updateBlacklist(AMIHelpers amiHelpers) {

    }
}
