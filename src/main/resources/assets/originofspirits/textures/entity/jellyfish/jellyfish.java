// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class jellyfish extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer leg_back;
	private final ModelRenderer leg_front;
	private final ModelRenderer leg_right;
	private final ModelRenderer leg_left;

	public jellyfish() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.setTextureOffset(0, 31).addBox(-5.5F, -8.0F, -5.5333F, 11.0F, 8.0F, 11.0F, 0.0F, false);

		leg_back = new ModelRenderer(this);
		leg_back.setRotationPoint(0.0F, 0.0F, 3.3F);
		body.addChild(leg_back);
		leg_back.setTextureOffset(0, 15).addBox(-4.5F, 0.0F, -0.1333F, 9.0F, 16.0F, 0.0F, 0.0F, false);

		leg_front = new ModelRenderer(this);
		leg_front.setRotationPoint(0.0F, -0.3F, -3.1F);
		body.addChild(leg_front);
		leg_front.setTextureOffset(0, 15).addBox(-4.5F, 0.3F, -0.0333F, 9.0F, 16.0F, 0.0F, 0.0F, false);

		leg_right = new ModelRenderer(this);
		leg_right.setRotationPoint(3.8F, -0.3F, -0.1F);
		body.addChild(leg_right);
		setRotationAngle(leg_right, 0.0F, -1.5708F, 0.0F);
		leg_right.setTextureOffset(0, 15).addBox(-4.5F, 0.3F, -0.0333F, 9.0F, 16.0F, 0.0F, 0.0F, false);

		leg_left = new ModelRenderer(this);
		leg_left.setRotationPoint(-3.8F, 0.0F, 0.0F);
		body.addChild(leg_left);
		setRotationAngle(leg_left, 0.0F, -1.5708F, 0.0F);
		leg_left.setTextureOffset(0, 15).addBox(-4.5F, 0.0F, -0.0333F, 9.0F, 16.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}