package net.mruevich.siege_evolved.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public abstract class Ability {
    public LivingEntity entity;
    public AnimationState anim;
    public int cooldown;
    public int timeSinceUsed = 0;
    public int type;

    public Ability(LivingEntity entity, AnimationState anim,int cooldown, int type) {
        this.entity = entity;
        this.anim = anim;
        this.cooldown = cooldown;
        this.type = type;
    }

    public abstract boolean canUse();

    public void TickAbility() {
        timeSinceUsed++;
    }

    public void UseAbility() {
        if (timeSinceUsed < cooldown) { return; }
        timeSinceUsed = 0;
    }
}
