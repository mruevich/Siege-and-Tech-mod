package net.mruevich.siege_evolved.entity.custom.abilities;

import net.minecraft.world.entity.LivingEntity;
import net.mruevich.siege_evolved.entity.custom.Ability;

public class DashAbility extends Ability {
    public DashAbility(LivingEntity ent) {
        super(ent, 10, 0);
        this.entity = ent;
        this.cooldown = 10;
        this.type = 0;
    }

    @Override
    public void UseAbility() {
        if (timeSinceUsed < cooldown) { return; }
        timeSinceUsed = 0;

        entity.setDeltaMovement(2, 0, 0);
        super.UseAbility();
    }
}
