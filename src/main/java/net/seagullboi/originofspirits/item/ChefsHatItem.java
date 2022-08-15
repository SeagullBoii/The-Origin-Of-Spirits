
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.block.Blocks;

import net.seagullboi.originofspirits.OriginofspiritsModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OriginofspiritsModElements.ModElement.Tag
public class ChefsHatItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:chefs_hat_helmet")
	public static final Item helmet = null;
	@ObjectHolder("originofspirits:chefs_hat_chestplate")
	public static final Item body = null;
	@ObjectHolder("originofspirits:chefs_hat_leggings")
	public static final Item legs = null;
	@ObjectHolder("originofspirits:chefs_hat_boots")
	public static final Item boots = null;

	public ChefsHatItem(OriginofspiritsModElements instance) {
		super(instance, 103);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 20;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 9;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Blocks.WHITE_WOOL));
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "chefs_hat";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(null)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedHead = new Modelvindicator_chef_hat().chef_hat;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "originofspirits:textures/vinicator_chef_hat_model.png";
			}
		}.setRegistryName("chefs_hat_helmet"));
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelvindicator_chef_hat extends EntityModel<Entity> {
		private final ModelRenderer chef_hat;

		public Modelvindicator_chef_hat() {
			textureWidth = 128;
			textureHeight = 128;
			chef_hat = new ModelRenderer(this);
			chef_hat.setRotationPoint(0.0F, 0.0F, 0.0F);
			chef_hat.setTextureOffset(76, 94).addBox(-6.5F, -16.0F, -6.5F, 13.0F, 7.0F, 13.0F, 0.0F, false);
			chef_hat.setTextureOffset(76, 114).addBox(-5.5F, -9.0F, -5.5F, 11.0F, 3.0F, 11.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			chef_hat.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

		}
	}

}
