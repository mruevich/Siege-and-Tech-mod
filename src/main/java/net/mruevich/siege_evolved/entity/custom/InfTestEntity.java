package net.mruevich.siege_evolved.entity.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.sensing.NearestLivingEntitySensor;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.mruevich.siege_evolved.entity.custom.abilities.DashAbility;
import net.mruevich.siege_evolved.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Attr;

public class InfTestEntity extends Monster {

    public InfTestEntity leader;
    private static final double packSearchDistance = 32D;

    public final AnimationState dashAnimState = new AnimationState();
    private int dashAnimTimeout = 0;

    private final AbilityManager abilityManager = new AbilityManager();
    private final Ability dash = new DashAbility(this, dashAnimState);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.1D, true));
        //this.goalSelector.addGoal(2, new FollowMobGoal(this, 1.2D, 64, 64));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1, 4));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Skeleton.class, true));

    }

    @Override
    public void tick() {
        abilityManager.UpdateAbilities();
        super.tick();

        if (this.level().isClientSide()){
            setupAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        dash.UseAbility();

        // Find a pack if infected doesn't have a leader
        if (leader == null){
            
        }
        // Follow the leader otherwise
        else {

        }

        super.aiStep();
    }

    private void setupAnimationStates(){
        if (dashAnimTimeout <= 0){
            dashAnimTimeout = this.random.nextInt(400) + 800;
            dashAnimState.start(this.tickCount);
        }else{
            --this.dashAnimTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6F, 1f);
        }else{
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.FOLLOW_RANGE, 45.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D)
                .add(Attributes.ARMOR, 2.0D);
    }

    public InfTestEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        abilityManager.AddAbility(dash);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        //return ModSounds.SOUND_INF_AMBIENT.get();
        return SoundEvents.WOLF_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        //return ModSounds.SOUND_INF_HURT.get();
        return SoundEvents.CAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        //return ModSounds.SOUND_INF_DEATH.get();
        return SoundEvents.DROWNED_DEATH;
    }
}
