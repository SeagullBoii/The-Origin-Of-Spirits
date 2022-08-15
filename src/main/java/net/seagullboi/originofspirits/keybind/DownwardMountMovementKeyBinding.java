
package net.seagullboi.originofspirits.keybind;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.entity.CabadorEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.network.NetworkEvent;
import org.lwjgl.glfw.GLFW;

import java.util.function.Supplier;

@OriginofspiritsModElements.ModElement.Tag
public class DownwardMountMovementKeyBinding extends OriginofspiritsModElements.ModElement {
	@OnlyIn(Dist.CLIENT)
	private KeyBinding keys;
	private long lastpress = 0;

	public DownwardMountMovementKeyBinding(OriginofspiritsModElements instance) {
		super(instance, 960);
		elements.addNetworkMessage(KeyBindingPressedMessage.class, KeyBindingPressedMessage::buffer, KeyBindingPressedMessage::new,
				KeyBindingPressedMessage::handler);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		keys = new KeyBinding("key.originofspirits.downward_mount_movement", GLFW.GLFW_KEY_LEFT_CONTROL, "key.categories.toos");
		ClientRegistry.registerKeyBinding(keys);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Minecraft.getInstance().currentScreen == null) {
			if (event.getKey() == keys.getKey().getKeyCode()) {
				if (event.getAction() == GLFW.GLFW_PRESS) {
					OriginOfSpirits.PACKET_HANDLER.sendToServer(new KeyBindingPressedMessage(0, 0));
					pressAction(Minecraft.getInstance().player, 0, 0);
					lastpress = System.currentTimeMillis();
				} else if (event.getAction() == GLFW.GLFW_RELEASE) {
					int dt = (int) (System.currentTimeMillis() - lastpress);
					OriginOfSpirits.PACKET_HANDLER.sendToServer(new KeyBindingPressedMessage(1, dt));
					pressAction(Minecraft.getInstance().player, 1, dt);
				}
			}
		}
	}

	public static class KeyBindingPressedMessage {
		int type, pressedms;

		public KeyBindingPressedMessage(int type, int pressedms) {
			this.type = type;
			this.pressedms = pressedms;
		}

		public KeyBindingPressedMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.pressedms = buffer.readInt();
		}

		public static void buffer(KeyBindingPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeInt(message.pressedms);
		}

		public static void handler(KeyBindingPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				pressAction(context.getSender(), message.type, message.pressedms);
			});
			context.setPacketHandled(true);
		}
	}

	private static void pressAction(PlayerEntity entity, int type, int pressedms) {
		World world = entity.world;
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (type == 0) {
			if (entity.getRidingEntity() instanceof CabadorEntity) {
				entity.getRidingEntity().getPersistentData().putInt("verticalMovement", -1);
			}
		}
		if (type == 1) {
			if (entity.getRidingEntity() instanceof CabadorEntity) {
				entity.getRidingEntity().getPersistentData().putInt("verticalMovement", 0);
			}
		}
	}
}
