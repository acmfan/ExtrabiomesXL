package extrabiomes;

import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public enum Extrabiomes {
	INSTANCE;

	private static final String NAME = "Extrabiomes XL";
	private static final String VERSION = "2.0.4";
	private static Item scarecrow = null;

	public int addFuel(int id, int damage) {
		return BlockControl.INSTANCE.getFuelValue(id, damage);
	}

	private void addScarecrow() {
		scarecrow = (new ItemScarecrow(
				Options.INSTANCE.getId(CustomItem.SCARECROW))).setIconIndex(96)
				.setItemName("extrabiomes.scarecrow");
		ModLoader.addName(scarecrow, "Scarecrow");
		ModLoader.addRecipe(new ItemStack(scarecrow, 1),
				new Object[] { " a ", "cbc", " c ", Character.valueOf('a'),
						Block.pumpkin, Character.valueOf('b'), Block.melon,
						Character.valueOf('c'), Item.stick });
		ModLoader.registerEntityID(EntityScarecrow.class, "scarecrow",
				ModLoader.getUniqueEntityId());
	}

	public String getVersion() {
		return VERSION;
	}
	
	public void addRenderer(Map map) {
		map.put(EntityScarecrow.class, new RenderScarecrow(
				new ModelScarecrow(), 0.4F));
	}

	public void onModsLoaded() {

		AddOnControl.INSTANCE.initialize();
		BiomeControl.configureVanillaBiomes();
		AddOnControl.doDisableComponents();
		BiomeControl.INSTANCE.initialize();
		AddOnControl.INSTANCE.notifyAPIInitialized();
		BlockControl.INSTANCE.doAddOnsBlockSubstitutions(); // Allow AddOns to
															// substitute blocks
		BiomeControl.doAddOnsAddBiomes();
		addScarecrow();
	}

	public void onModLoad() {
		Options.INSTANCE.initialize();
		BlockControl.INSTANCE.initialize();
	}
}
