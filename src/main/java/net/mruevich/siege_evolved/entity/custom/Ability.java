package net.mruevich.siege_evolved.entity.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public abstract class Ability {
    public LivingEntity entity;
    public int cooldown;
    public int timeSinceUsed = 0;
    public int type;

    public Ability(LivingEntity entity,int cooldown, int type) {
        this.entity = entity;
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
