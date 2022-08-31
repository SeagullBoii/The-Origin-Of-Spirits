package net.seagullboi.originofspirits.registry;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.tile_entities.container.GunsmithingTableContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.block.tile_entities.container.PressingMachineContainer;

public class TOOSContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, OriginOfSpirits.MOD_ID);

    public static final RegistryObject<ContainerType<GunsmithingTableContainer>> GUNSMITHING_TABLE_CONTAINER = CONTAINERS.register("gunsmithing_table_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new GunsmithingTableContainer(windowId, world, pos, inv, inv.player);
            })));
    public static final RegistryObject<ContainerType<PressingMachineContainer>> PRESSING_MACHINE_CONTAINER = CONTAINERS.register("pressing_machine_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new PressingMachineContainer(windowId, world, pos, inv, inv.player);
            })));


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
