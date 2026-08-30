package com.eyrax.realroosters.entity;

import com.eyrax.realroosters.config.RoostersConfig;
import com.eyrax.realroosters.item.BreedAnalyzerItem;
import com.eyrax.realroosters.item.TrainingWhistleItem;
import com.eyrax.realroosters.registry.ModEntities;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class HeritageChicken extends TamableAnimal {
    private static final EntityDataAccessor<Integer> SEX =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BREED =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PLUMAGE =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VITALITY =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> AGILITY =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> POWER =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FERTILITY =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TEMPERAMENT =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STANCE =
            SynchedEntityData.defineId(HeritageChicken.class, EntityDataSerializers.INT);

    private int eggTimer = 6000;

    public HeritageChicken(EntityType<? extends HeritageChicken> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
                .add(Attributes.FOLLOW_RANGE, 18.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SEX, FowlSex.HEN.ordinal());
        builder.define(BREED, FowlBreed.CRIOLLO_DOMINICANO.ordinal());
        builder.define(PLUMAGE, PlumagePattern.SOLID.ordinal());
        builder.define(VITALITY, 50);
        builder.define(AGILITY, 50);
        builder.define(POWER, 50);
        builder.define(FERTILITY, 50);
        builder.define(TEMPERAMENT, 50);
        builder.define(STANCE, CombatStance.PASSIVE.ordinal());
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3D, true) {
            @Override
            public boolean canUse() {
                return canFight() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return canFight() && super.canContinueToUse();
            }
        });
        goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(4, new TemptGoal(this, 1.1D,
                Ingredient.of(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS, Items.BEETROOT_SEEDS), false));
        goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        targetSelector.addGoal(1, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return canFight() && super.canUse();
            }
        });
        targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return canGuard() && super.canUse();
            }
        });
        targetSelector.addGoal(3, new OwnerHurtTargetGoal(this) {
            @Override
            public boolean canUse() {
                return canGuard() && super.canUse();
            }
        });
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType,
                                        @Nullable SpawnGroupData spawnData) {
        SpawnGroupData result = super.finalizeSpawn(level, difficulty, spawnType, spawnData);
        RandomSource random = level.getRandom();
        setSex(random.nextBoolean() ? FowlSex.ROOSTER : FowlSex.HEN);
        setBreed(FowlBreed.random(random));
        setPlumage(PlumagePattern.random(random));
        setVitality(randomTrait(random));
        setAgility(randomTrait(random));
        setPower(randomTrait(random));
        setFertility(randomTrait(random));
        setTemperament(randomTrait(random));
        setStance(CombatStance.PASSIVE);
        refreshDerivedAttributes();
        setHealth(getMaxHealth());
        eggTimer = randomEggTimer();
        return result;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        if (!(otherParent instanceof HeritageChicken other)) {
            return null;
        }

        HeritageChicken child = ModEntities.HERITAGE_CHICKEN.get().create(level);
        if (child == null) {
            return null;
        }

        RandomSource random = level.getRandom();
        child.setSex(random.nextBoolean() ? FowlSex.ROOSTER : FowlSex.HEN);
        child.setBreed(random.nextFloat() < 0.08F
                ? FowlBreed.random(random)
                : (random.nextBoolean() ? getBreed() : other.getBreed()));
        child.setPlumage(random.nextFloat() < 0.12F
                ? PlumagePattern.random(random)
                : (random.nextBoolean() ? getPlumage() : other.getPlumage()));
        child.setVitality(inheritTrait(getVitality(), other.getVitality(), random));
        child.setAgility(inheritTrait(getAgility(), other.getAgility(), random));
        child.setPower(inheritTrait(getPower(), other.getPower(), random));
        child.setFertility(inheritTrait(getFertility(), other.getFertility(), random));
        child.setTemperament(inheritTrait(getTemperament(), other.getTemperament(), random));
        child.setStance(CombatStance.PASSIVE);
        child.setBaby(true);

        if (isTame() && other.isTame() && Objects.equals(getOwnerUUID(), other.getOwnerUUID())) {
            child.setOwnerUUID(getOwnerUUID());
            child.setTame(true, true);
        }

        child.refreshDerivedAttributes();
        child.setHealth(child.getMaxHealth());
        return child;
    }

    @Override
    public boolean canMate(Animal other) {
        return other instanceof HeritageChicken chicken
                && chicken != this
                && getSex() != chicken.getSex()
                && isInLove()
                && chicken.isInLove();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.WHEAT_SEEDS)
                || stack.is(Items.PUMPKIN_SEEDS)
                || stack.is(Items.MELON_SEEDS)
                || stack.is(Items.BEETROOT_SEEDS);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() instanceof BreedAnalyzerItem analyzer) {
            return analyzer.analyze(player, this);
        }
        if (stack.getItem() instanceof TrainingWhistleItem whistle) {
            return whistle.command(player, this);
        }

        if (!isTame() && isFood(stack)) {
            if (!level().isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                if (random.nextInt(3) == 0) {
                    tame(player);
                    navigation.stop();
                    setTarget(null);
                    level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    level().broadcastEntityEvent(this, (byte) 6);
                }
            }
            return InteractionResult.sidedSuccess(level().isClientSide);
        }

        if (isTame() && isOwnedBy(player) && player.isShiftKeyDown() && stack.isEmpty()) {
            if (!level().isClientSide) {
                setOrderedToSit(!isOrderedToSit());
                navigation.stop();
                setTarget(null);
            }
            return InteractionResult.sidedSuccess(level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!onGround() && getDeltaMovement().y < 0.0D) {
            setDeltaMovement(getDeltaMovement().multiply(1.0D, 0.62D, 1.0D));
        }

        if (!level().isClientSide && isAlive() && !isBaby() && isHen()) {
            if (--eggTimer <= 0) {
                playSound(SoundEvents.CHICKEN_EGG, 1.0F, 0.9F + random.nextFloat() * 0.2F);
                spawnAtLocation(Items.EGG);
                eggTimer = randomEggTimer();
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.CHICKEN_STEP, 0.15F, 0.9F);
    }

    @Override
    public float getVoicePitch() {
        float base = isRooster() ? 0.72F : 1.08F;
        return isBaby() ? base + 0.35F : base;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Sex", getSex().serializedName());
        tag.putInt("Breed", getBreed().ordinal());
        tag.putInt("Plumage", getPlumage().ordinal());
        tag.putInt("Vitality", getVitality());
        tag.putInt("Agility", getAgility());
        tag.putInt("Power", getPower());
        tag.putInt("Fertility", getFertility());
        tag.putInt("Temperament", getTemperament());
        tag.putInt("Stance", getStance().ordinal());
        tag.putInt("EggTimer", eggTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSex("rooster".equals(tag.getString("Sex")) ? FowlSex.ROOSTER : FowlSex.HEN);
        setBreed(FowlBreed.byId(tag.getInt("Breed")));
        setPlumage(PlumagePattern.byId(tag.getInt("Plumage")));
        setVitality(tag.contains("Vitality") ? tag.getInt("Vitality") : 50);
        setAgility(tag.contains("Agility") ? tag.getInt("Agility") : 50);
        setPower(tag.contains("Power") ? tag.getInt("Power") : 50);
        setFertility(tag.contains("Fertility") ? tag.getInt("Fertility") : 50);
        setTemperament(tag.contains("Temperament") ? tag.getInt("Temperament") : 50);
        setStance(CombatStance.byId(tag.getInt("Stance")));
        eggTimer = tag.contains("EggTimer") ? tag.getInt("EggTimer") : randomEggTimer();
        refreshDerivedAttributes();
        setHealth(Math.min(getHealth(), getMaxHealth()));
    }

    public void refreshDerivedAttributes() {
        FowlBreed breed = getBreed();
        double maxHealth = (8.0D + getVitality() * 0.14D) * breed.vitalityScale();
        double speed = (0.18D + getAgility() * 0.0015D) * breed.agilityScale();
        double attack = isRooster() ? (1.0D + getPower() * 0.055D) * breed.powerScale() : 0.5D;
        double armor = Math.max(0.0D, (getVitality() - 35) * 0.035D);
        double knockback = Mth.clamp((getTemperament() - 25) / 500.0D, 0.0D, 0.15D);

        setBaseValue(Attributes.MAX_HEALTH, maxHealth);
        setBaseValue(Attributes.MOVEMENT_SPEED, speed);
        setBaseValue(Attributes.ATTACK_DAMAGE, attack);
        setBaseValue(Attributes.ARMOR, armor);
        setBaseValue(Attributes.KNOCKBACK_RESISTANCE, knockback);
    }

    private void setBaseValue(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, double value) {
        AttributeInstance instance = getAttribute(attribute);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }

    private int randomEggTimer() {
        int min = RoostersConfig.MIN_EGG_TICKS.get();
        int max = Math.max(min, RoostersConfig.MAX_EGG_TICKS.get());
        double fertilityBonus = 1.15D - (getFertility() / 250.0D);
        double breedBonus = 1.0D / getBreed().fertilityScale();
        return Math.max(600, (int) (Mth.nextInt(random, min, max) * fertilityBonus * breedBonus));
    }

    private static int randomTrait(RandomSource random) {
        return Mth.clamp(35 + random.nextInt(31), 1, 100);
    }

    private static int inheritTrait(int first, int second, RandomSource random) {
        return Mth.clamp((first + second) / 2 + random.nextInt(17) - 8, 1, 100);
    }

    private boolean canFight() {
        return RoostersConfig.ROOSTER_COMBAT.get() && isRooster() && !isBaby() && getStance() != CombatStance.PASSIVE;
    }

    private boolean canGuard() {
        return canFight() && isTame() && getStance() == CombatStance.GUARD;
    }

    public boolean isHen() { return getSex() == FowlSex.HEN; }
    public boolean isRooster() { return getSex() == FowlSex.ROOSTER; }
    public FowlSex getSex() { return FowlSex.byId(entityData.get(SEX)); }
    public FowlBreed getBreed() { return FowlBreed.byId(entityData.get(BREED)); }
    public PlumagePattern getPlumage() { return PlumagePattern.byId(entityData.get(PLUMAGE)); }
    public CombatStance getStance() { return CombatStance.byId(entityData.get(STANCE)); }
    public int getVitality() { return entityData.get(VITALITY); }
    public int getAgility() { return entityData.get(AGILITY); }
    public int getPower() { return entityData.get(POWER); }
    public int getFertility() { return entityData.get(FERTILITY); }
    public int getTemperament() { return entityData.get(TEMPERAMENT); }

    public void setSex(FowlSex value) { entityData.set(SEX, value.ordinal()); }
    public void setBreed(FowlBreed value) { entityData.set(BREED, value.ordinal()); }
    public void setPlumage(PlumagePattern value) { entityData.set(PLUMAGE, value.ordinal()); }
    public void setStance(CombatStance value) { entityData.set(STANCE, value.ordinal()); }
    public void setVitality(int value) { entityData.set(VITALITY, Mth.clamp(value, 1, 100)); }
    public void setAgility(int value) { entityData.set(AGILITY, Mth.clamp(value, 1, 100)); }
    public void setPower(int value) { entityData.set(POWER, Mth.clamp(value, 1, 100)); }
    public void setFertility(int value) { entityData.set(FERTILITY, Mth.clamp(value, 1, 100)); }
    public void setTemperament(int value) { entityData.set(TEMPERAMENT, Mth.clamp(value, 1, 100)); }
}
