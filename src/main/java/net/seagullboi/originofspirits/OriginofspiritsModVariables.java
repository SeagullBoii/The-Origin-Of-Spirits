package net.seagullboi.originofspirits;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class OriginofspiritsModVariables {
	public OriginofspiritsModVariables(OriginofspiritsModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null) {
				OriginOfSpirits.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new WorldSavedDataSyncMessage(0, mapdata));
			}
			if (worlddata != null) {
				OriginOfSpirits.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new WorldSavedDataSyncMessage(1, worlddata));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				OriginOfSpirits.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "originofspirits_worldvars";
		public boolean MoreIllagers = false;
		public boolean WorldSetup = false;
		public boolean Spite = false;
		public boolean AbyssalFishing = false;
		public boolean BagConfig = false;

		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			MoreIllagers = nbt.getBoolean("MoreIllagers");
			WorldSetup = nbt.getBoolean("WorldSetup");
			Spite = nbt.getBoolean("Spite");
			AbyssalFishing = nbt.getBoolean("AbyssalFishing");
			BagConfig = nbt.getBoolean("BagConfig");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putBoolean("MoreIllagers", MoreIllagers);
			nbt.putBoolean("WorldSetup", WorldSetup);
			nbt.putBoolean("Spite", Spite);
			nbt.putBoolean("AbyssalFishing", AbyssalFishing);
			nbt.putBoolean("BagConfig", BagConfig);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				OriginOfSpirits.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(((World) world)::getDimensionKey),
						new WorldSavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(IWorld world) {
			if (world instanceof ServerWorld) {
				return ((ServerWorld) world).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "originofspirits_mapvars";
		public boolean hasAccessories = false;
		public boolean ConsumeFairyBloodEffect = false;

		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			hasAccessories = nbt.getBoolean("hasAccessories");
			ConsumeFairyBloodEffect = nbt.getBoolean("ConsumeFairyBloodEffect");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putBoolean("hasAccessories", hasAccessories);
			nbt.putBoolean("ConsumeFairyBloodEffect", ConsumeFairyBloodEffect);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				OriginOfSpirits.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(IWorld world) {
			if (world instanceof IServerWorld) {
				return ((IServerWorld) world).getWorld().getServer().getWorld(World.OVERWORLD).getSavedData().getOrCreate(MapVariables::new,
						DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;

		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;

	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("originofspirits", "player_variables"), new PlayerVariablesProvider());
	}

	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("Mana", instance.Mana);
			nbt.putDouble("MaxMana", instance.MaxMana);
			nbt.put("MiscSlot1", instance.MiscSlot1.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot1Count", instance.MiscSlot1Count);
			nbt.put("MiscSlot1Backup", instance.MiscSlot1Backup.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot1CountBackup", instance.MiscSlot1CountBackup);
			nbt.put("MiscSlot2", instance.MiscSlot2.write(new CompoundNBT()));
			nbt.put("MiscSlot2Backup", instance.MiscSlot2Backup.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot2Count", instance.MiscSlot2Count);
			nbt.putDouble("MiscSlot2CountBackup", instance.MiscSlot2CountBackup);
			nbt.put("MiscSlot3", instance.MiscSlot3.write(new CompoundNBT()));
			nbt.put("MiscSlot3Backup", instance.MiscSlot3Backup.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot3Count", instance.MiscSlot3Count);
			nbt.putDouble("MiscSlot3CountBackup", instance.MiscSlot3CountBackup);
			nbt.put("MiscSlot4", instance.MiscSlot4.write(new CompoundNBT()));
			nbt.put("MiscSlot4Backup", instance.MiscSlot4Backup.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot4Count", instance.MiscSlot4Count);
			nbt.putDouble("MiscSlot4CountBackup", instance.MiscSlot4CountBackup);
			nbt.put("MiscSlot5", instance.MiscSlot5.write(new CompoundNBT()));
			nbt.put("MiscSlot5Backup", instance.MiscSlot5Backup.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot5Count", instance.MiscSlot5Count);
			nbt.putDouble("MiscSlot5CountBackup", instance.MiscSlot5CountBackup);
			nbt.put("MiscSlot6", instance.MiscSlot6.write(new CompoundNBT()));
			nbt.put("MiscSlot6Backup", instance.MiscSlot6Backup.write(new CompoundNBT()));
			nbt.putDouble("MiscSlot6Count", instance.MiscSlot6Count);
			nbt.putDouble("MiscSlot6CountBackup", instance.MiscSlot6CountBackup);
			nbt.putBoolean("gotBag", instance.gotBag);
			nbt.putDouble("ManaCooldown", instance.ManaCooldown);
			nbt.putBoolean("DidDoubleJump", instance.DidDoubleJump);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.Mana = nbt.getDouble("Mana");
			instance.MaxMana = nbt.getDouble("MaxMana");
			instance.MiscSlot1 = ItemStack.read(nbt.getCompound("MiscSlot1"));
			instance.MiscSlot1Count = nbt.getDouble("MiscSlot1Count");
			instance.MiscSlot1Backup = ItemStack.read(nbt.getCompound("MiscSlot1Backup"));
			instance.MiscSlot1CountBackup = nbt.getDouble("MiscSlot1CountBackup");
			instance.MiscSlot2 = ItemStack.read(nbt.getCompound("MiscSlot2"));
			instance.MiscSlot2Backup = ItemStack.read(nbt.getCompound("MiscSlot2Backup"));
			instance.MiscSlot2Count = nbt.getDouble("MiscSlot2Count");
			instance.MiscSlot2CountBackup = nbt.getDouble("MiscSlot2CountBackup");
			instance.MiscSlot3 = ItemStack.read(nbt.getCompound("MiscSlot3"));
			instance.MiscSlot3Backup = ItemStack.read(nbt.getCompound("MiscSlot3Backup"));
			instance.MiscSlot3Count = nbt.getDouble("MiscSlot3Count");
			instance.MiscSlot3CountBackup = nbt.getDouble("MiscSlot3CountBackup");
			instance.MiscSlot4 = ItemStack.read(nbt.getCompound("MiscSlot4"));
			instance.MiscSlot4Backup = ItemStack.read(nbt.getCompound("MiscSlot4Backup"));
			instance.MiscSlot4Count = nbt.getDouble("MiscSlot4Count");
			instance.MiscSlot4CountBackup = nbt.getDouble("MiscSlot4CountBackup");
			instance.MiscSlot5 = ItemStack.read(nbt.getCompound("MiscSlot5"));
			instance.MiscSlot5Backup = ItemStack.read(nbt.getCompound("MiscSlot5Backup"));
			instance.MiscSlot5Count = nbt.getDouble("MiscSlot5Count");
			instance.MiscSlot5CountBackup = nbt.getDouble("MiscSlot5CountBackup");
			instance.MiscSlot6 = ItemStack.read(nbt.getCompound("MiscSlot6"));
			instance.MiscSlot6Backup = ItemStack.read(nbt.getCompound("MiscSlot6Backup"));
			instance.MiscSlot6Count = nbt.getDouble("MiscSlot6Count");
			instance.MiscSlot6CountBackup = nbt.getDouble("MiscSlot6CountBackup");
			instance.gotBag = nbt.getBoolean("gotBag");
			instance.ManaCooldown = nbt.getDouble("ManaCooldown");
			instance.DidDoubleJump = nbt.getBoolean("DidDoubleJump");
		}
	}

	public static class PlayerVariables {
		public double Mana = 0;
		public double MaxMana = 10.0;
		public ItemStack MiscSlot1 = ItemStack.EMPTY;
		public double MiscSlot1Count = 0;
		public ItemStack MiscSlot1Backup = ItemStack.EMPTY;
		public double MiscSlot1CountBackup = 0;
		public ItemStack MiscSlot2 = ItemStack.EMPTY;
		public ItemStack MiscSlot2Backup = ItemStack.EMPTY;
		public double MiscSlot2Count = 0;
		public double MiscSlot2CountBackup = 0;
		public ItemStack MiscSlot3 = ItemStack.EMPTY;
		public ItemStack MiscSlot3Backup = ItemStack.EMPTY;
		public double MiscSlot3Count = 0;
		public double MiscSlot3CountBackup = 0;
		public ItemStack MiscSlot4 = ItemStack.EMPTY;
		public ItemStack MiscSlot4Backup = ItemStack.EMPTY;
		public double MiscSlot4Count = 0;
		public double MiscSlot4CountBackup = 0;
		public ItemStack MiscSlot5 = ItemStack.EMPTY;
		public ItemStack MiscSlot5Backup = ItemStack.EMPTY;
		public double MiscSlot5Count = 0;
		public double MiscSlot5CountBackup = 0;
		public ItemStack MiscSlot6 = ItemStack.EMPTY;
		public ItemStack MiscSlot6Backup = ItemStack.EMPTY;
		public double MiscSlot6Count = 0;
		public double MiscSlot6CountBackup = 0;
		public boolean gotBag = false;
		public double ManaCooldown = 0;
		public boolean DidDoubleJump = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				OriginOfSpirits.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.MaxMana = original.MaxMana;
		clone.MiscSlot1 = original.MiscSlot1;
		clone.MiscSlot1Count = original.MiscSlot1Count;
		clone.MiscSlot1Backup = original.MiscSlot1Backup;
		clone.MiscSlot1CountBackup = original.MiscSlot1CountBackup;
		clone.MiscSlot2 = original.MiscSlot2;
		clone.MiscSlot2Backup = original.MiscSlot2Backup;
		clone.MiscSlot2Count = original.MiscSlot2Count;
		clone.MiscSlot2CountBackup = original.MiscSlot2CountBackup;
		clone.MiscSlot3 = original.MiscSlot3;
		clone.MiscSlot3Backup = original.MiscSlot3Backup;
		clone.MiscSlot3Count = original.MiscSlot3Count;
		clone.MiscSlot3CountBackup = original.MiscSlot3CountBackup;
		clone.MiscSlot4 = original.MiscSlot4;
		clone.MiscSlot4Backup = original.MiscSlot4Backup;
		clone.MiscSlot4Count = original.MiscSlot4Count;
		clone.MiscSlot4CountBackup = original.MiscSlot4CountBackup;
		clone.MiscSlot5 = original.MiscSlot5;
		clone.MiscSlot5Backup = original.MiscSlot5Backup;
		clone.MiscSlot5CountBackup = original.MiscSlot5CountBackup;
		clone.MiscSlot6 = original.MiscSlot6;
		clone.MiscSlot6Backup = original.MiscSlot6Backup;
		clone.MiscSlot6Count = original.MiscSlot6Count;
		clone.MiscSlot6CountBackup = original.MiscSlot6CountBackup;
		clone.gotBag = original.gotBag;
		if (!event.isWasDeath()) {
			clone.Mana = original.Mana;
			clone.MiscSlot5Count = original.MiscSlot5Count;
			clone.ManaCooldown = original.ManaCooldown;
			clone.DidDoubleJump = original.DidDoubleJump;
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.Mana = message.data.Mana;
					variables.MaxMana = message.data.MaxMana;
					variables.MiscSlot1 = message.data.MiscSlot1;
					variables.MiscSlot1Count = message.data.MiscSlot1Count;
					variables.MiscSlot1Backup = message.data.MiscSlot1Backup;
					variables.MiscSlot1CountBackup = message.data.MiscSlot1CountBackup;
					variables.MiscSlot2 = message.data.MiscSlot2;
					variables.MiscSlot2Backup = message.data.MiscSlot2Backup;
					variables.MiscSlot2Count = message.data.MiscSlot2Count;
					variables.MiscSlot2CountBackup = message.data.MiscSlot2CountBackup;
					variables.MiscSlot3 = message.data.MiscSlot3;
					variables.MiscSlot3Backup = message.data.MiscSlot3Backup;
					variables.MiscSlot3Count = message.data.MiscSlot3Count;
					variables.MiscSlot3CountBackup = message.data.MiscSlot3CountBackup;
					variables.MiscSlot4 = message.data.MiscSlot4;
					variables.MiscSlot4Backup = message.data.MiscSlot4Backup;
					variables.MiscSlot4Count = message.data.MiscSlot4Count;
					variables.MiscSlot4CountBackup = message.data.MiscSlot4CountBackup;
					variables.MiscSlot5 = message.data.MiscSlot5;
					variables.MiscSlot5Backup = message.data.MiscSlot5Backup;
					variables.MiscSlot5Count = message.data.MiscSlot5Count;
					variables.MiscSlot5CountBackup = message.data.MiscSlot5CountBackup;
					variables.MiscSlot6 = message.data.MiscSlot6;
					variables.MiscSlot6Backup = message.data.MiscSlot6Backup;
					variables.MiscSlot6Count = message.data.MiscSlot6Count;
					variables.MiscSlot6CountBackup = message.data.MiscSlot6CountBackup;
					variables.gotBag = message.data.gotBag;
					variables.ManaCooldown = message.data.ManaCooldown;
					variables.DidDoubleJump = message.data.DidDoubleJump;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
