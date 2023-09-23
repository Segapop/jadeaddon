package com.lsb.entity.entities;

import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.util.GemPlacements;
import com.lsb.entity.abilities.ModelAbility;
import com.lsb.entity.abilities.SupervisorAbility;
import com.lsb.init.AddonItems;
import com.lsb.lsb;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class EntitySbOpal extends EntityVaryingGem {

    //This gem was copied from topaz
    public EntitySbOpal(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public int[] NeglectedColors() {
        return new int[] {
                17,16
        };
    }

    @Override
    public String getModID() {
        //THIS DEFINES THE GEM AS BEING AN ADDON GEM
        return lsb.MODID;
    }

    @Override
    public Class getItemRegister() {
        //THIS TOO
        return AddonItems.class;
    }

    @Override
    public boolean isPopular() {
        return true;
    }

    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_CHIME.get();
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowGarnet(this, 0.7D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Mob.class, 6.0F, 1.0D, 1.2D, (mob)-> mob.getClassification(true)== MobCategory.MONSTER));
        this.goalSelector.addGoal(1, new EntityAISludged(this, 0.6));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkBothSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 1, false, false, this::checkSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityGem.class, 6.0F, 1.0D, 1.2D, this::checkElseSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityCrawler.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityShambler.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityAbomination.class, 6.0F, 1.0D, 1.2D));
    }

    @Override
    public Float baseXScale() {
        return .85F;
    }

    @Override
    public Float baseYScale() {
        return .9F;
    }

    @Override
    public Float baseZScale() {
        return .85F;
    }

    public int generateHardness() {
        return 5;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.TOP_OF_HEAD, GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.NOSE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR, GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY,
        };
    }

    @Override
    public int generateHairVariant() { return this.random.nextInt(7); }

    @Override
    public int exitHoleSize() {
        return 3;
    }

    @Override
    public int generateOutfitVariant() {
        return this.random.nextInt(13);
    }

    @Override
    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
    }

    public int generateRebelInsigniaVariant() {
        return this.getRebelOutfitVariant();
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(2);
    }

    @Override
    public int generateSkinColorVariant() {
        return this.random.nextInt(1);
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

    @Override
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "slovakian";
                break;
            case 2:
                name = "peruvian";
                break;
            case 3:
                name = "ethiopian";
                break;
            case 4:
                name = "fire";
                break;
            case 5:
                name = "tanzanian";
                break;
            case 6:
                name = "rose_water";
                break;
            case 7:
                name = "mintabie";
                break;
            case 8:
                name = "crystal";
                break;
            case 9:
                name = "contraluz";
                break;
            case 10:
                name = "morado";
                break;
            case 11:
                name = "harlequin";
                break;
            case 12:
                name = "boulder";
                break;
            case 13:
                name = "andamooka";
                break;
            case 14:
                name = "cherry";
                break;
            case 15:
                name = "honduran";
                break;
            default:
                name = "coober_pedy";
                break;
        }
        return name;
    }

    @Override
    public boolean hasSkinColorVariant() {
        return true;
    }

    @Override
    public boolean generateIsEmotional() {
        return true;
    }

    @Override
    public byte EmotionThreshold() {
        return 4;
    }

    @Override
    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    @Override
    public boolean canChangeInsigniaColorByDefault() {
        return true;
    }

    public ArrayList<Ability> possibleAbilities() {
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityZilch());
        arrayList.add(new AbilityTank());
        arrayList.add(new AbilityBeefcake());
        arrayList.add(new AbilityUnhinged());
        arrayList.add(new AbilityAerokinesis());
        return arrayList;
    }

    public ArrayList<Ability> definiteAbilities() {
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new ModelAbility());
        return arrayList;
    }

    @Override
    public int baseFocus() {
        return 3;
    }

    public boolean getIsCut()
    {
        return false;
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return false;
    }

    @Override
    public int[] outfitPlacementVariants() {
        return new int[]{
        };
    }

    @Override
    public boolean hasWings() {
        return true;
    }

    @Override
    public int generateWingVariant()  {
        return this.random.nextInt(9);
    }

    @Override
    public boolean hasMarkings() {
        return true;
    }

    @Override
    public boolean hasMarkings2() {
        return true;
    }
}
