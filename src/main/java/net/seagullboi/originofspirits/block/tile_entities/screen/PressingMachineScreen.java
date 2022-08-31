package net.seagullboi.originofspirits.block.tile_entities.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.tile_entities.container.PressingMachineContainer;

public class PressingMachineScreen extends ContainerScreen<PressingMachineContainer> {
    private final ResourceLocation GUI = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/gui/pressing_machine/pressing_machine_gui.png");
    private final ResourceLocation PROGRESS_GUI = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/gui/pressing_machine/pressing_machine_gui_progress_4.png");

    public PressingMachineScreen(PressingMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        if (container.isPowered()) {
            this.minecraft.getTextureManager().bindTexture(PROGRESS_GUI);
        } else {
            this.minecraft.getTextureManager().bindTexture(GUI);
        }
        int i = this.guiLeft;
        int j = this.guiTop;
        this.ySize = 168;
        this.blit(matrixStack, i, j, 0, -2, this.xSize, this.ySize);
    }
}