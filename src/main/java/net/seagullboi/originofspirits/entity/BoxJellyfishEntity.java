
package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.seagullboi.originofspirits.potion.VenomPotionEffect;

public class BoxJellyfishEntity extends JellyfishEntity{
	public BoxJellyfishEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1, 40));
		this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, true));
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 2)
				.createMutableAttribute(Attributes.MAX_HEALTH, 35)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1)
				.createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 0.4)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 15);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(VenomPotionEffect.potion, 100, 0));
		}
		return super.attackEntityAsMob(entityIn);
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

}
