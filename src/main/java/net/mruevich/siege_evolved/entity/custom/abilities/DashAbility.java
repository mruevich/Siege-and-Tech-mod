package net.mruevich.siege_evolved.entity.custom.abilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.mruevich.siege_evolved.entity.custom.Ability;

public class DashAbility extends Ability {
    public DashAbility(LivingEntity ent) {
        super(ent, 10, 0);
        this.entity = ent;
        this.cooldown = 30;
        this.type = 0;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void UseAbility() {
        if (timeSinceUsed < cooldown) { return; }
        timeSinceUsed = 0;

        Vec3 viewVector = entity.getViewVector(1);
        Vec3 tanVector = viewVector.cross(new Vec3(0,1,0));
        entity.setDeltaMovement(tanVector);
        super.UseAbility();
    }
}
