package com.eyrax.realroosters.client;

import com.eyrax.realroosters.entity.FowlBreed;
import com.eyrax.realroosters.entity.HeritageChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/** Articulated heritage-fowl model with sex, age and breed-specific silhouettes. */
public final class HeritageChickenModel extends EntityModel<HeritageChicken> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart breast;
    private final ModelPart saddle;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart largeComb;
    private final ModelPart smallComb;
    private final ModelPart peaComb;
    private final ModelPart wattles;
    private final ModelPart silkieCrest;
    private final ModelPart silkieBodyFluff;
    private final ModelPart brahmaShoulders;
    private final ModelPart rightSpur;
    private final ModelPart leftSpur;
    private final ModelPart rightLegFeathers;
    private final ModelPart leftLegFeathers;

    private final ModelPart roosterTailCenter;
    private final ModelPart roosterTailLeft;
    private final ModelPart roosterTailRight;
    private final ModelPart roosterTailOuterLeft;
    private final ModelPart roosterTailOuterRight;
    private final ModelPart henTailCenter;
    private final ModelPart henTailLeft;
    private final ModelPart henTailRight;
    private final ModelPart silkieTail;

    private final ModelPart chickBody;
    private final ModelPart chickHead;
    private final ModelPart chickRightWing;
    private final ModelPart chickLeftWing;
    private final ModelPart chickRightLeg;
    private final ModelPart chickLeftLeg;
    private final ModelPart chickTail;

    public HeritageChickenModel(ModelPart root) {
        this.root = root;
        body = root.getChild("body");
        breast = root.getChild("breast");
        saddle = root.getChild("saddle");
        neck = root.getChild("neck");
        head = root.getChild("head");
        rightLeg = root.getChild("right_leg");
        leftLeg = root.getChild("left_leg");
        rightWing = root.getChild("right_wing");
        leftWing = root.getChild("left_wing");
        largeComb = head.getChild("large_comb");
        smallComb = head.getChild("small_comb");
        peaComb = head.getChild("pea_comb");
        wattles = head.getChild("wattles");
        silkieCrest = head.getChild("silkie_crest");
        silkieBodyFluff = root.getChild("silkie_body_fluff");
        brahmaShoulders = root.getChild("brahma_shoulders");
        rightSpur = root.getChild("right_spur");
        leftSpur = root.getChild("left_spur");
        rightLegFeathers = root.getChild("right_leg_feathers");
        leftLegFeathers = root.getChild("left_leg_feathers");
        roosterTailCenter = root.getChild("rooster_tail_center");
        roosterTailLeft = root.getChild("rooster_tail_left");
        roosterTailRight = root.getChild("rooster_tail_right");
        roosterTailOuterLeft = root.getChild("rooster_tail_outer_left");
        roosterTailOuterRight = root.getChild("rooster_tail_outer_right");
        henTailCenter = root.getChild("hen_tail_center");
        henTailLeft = root.getChild("hen_tail_left");
        henTailRight = root.getChild("hen_tail_right");
        silkieTail = root.getChild("silkie_tail");
        chickBody = root.getChild("chick_body");
        chickHead = root.getChild("chick_head");
        chickRightWing = root.getChild("chick_right_wing");
        chickLeftWing = root.getChild("chick_left_wing");
        chickRightLeg = root.getChild("chick_right_leg");
        chickLeftLeg = root.getChild("chick_left_leg");
        chickTail = root.getChild("chick_tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 18)
                        .addBox(-3.75F, -4.0F, -4.5F, 7.5F, 8.0F, 9.0F, new CubeDeformation(0.18F)),
                PartPose.offsetAndRotation(0.0F, 15.2F, 0.4F, 1.34F, 0.0F, 0.0F));
        root.addOrReplaceChild("breast", CubeListBuilder.create().texOffs(0, 18)
                        .addBox(-3.45F, -3.2F, -3.0F, 6.9F, 6.4F, 6.0F, new CubeDeformation(0.26F)),
                PartPose.offsetAndRotation(0.0F, 15.3F, -2.55F, 1.14F, 0.0F, 0.0F));
        root.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(18, 22)
                        .addBox(-3.15F, -2.7F, -3.0F, 6.3F, 5.4F, 6.0F, new CubeDeformation(0.14F)),
                PartPose.offsetAndRotation(0.0F, 14.7F, 2.75F, 1.43F, 0.0F, 0.0F));
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 6)
                        .addBox(-2.35F, -3.2F, -2.25F, 4.7F, 6.4F, 4.5F, new CubeDeformation(0.10F)),
                PartPose.offsetAndRotation(0.0F, 12.0F, -3.1F, -0.20F, 0.0F, 0.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-2.45F, -3.3F, -2.35F, 4.9F, 5.0F, 4.7F, new CubeDeformation(0.05F))
                        .texOffs(18, 0).addBox(-1.55F, -1.15F, -4.25F, 3.1F, 1.45F, 2.0F)
                        .texOffs(18, 4).addBox(-1.15F, 0.25F, -4.0F, 2.3F, 0.65F, 1.65F),
                PartPose.offset(0.0F, 8.8F, -4.55F));
        head.addOrReplaceChild("large_comb", CubeListBuilder.create().texOffs(28, 0)
                        .addBox(-0.55F, -3.1F, -1.45F, 1.1F, 3.1F, 4.6F, new CubeDeformation(0.07F))
                        .texOffs(31, 1).addBox(-0.50F, -4.0F, -0.7F, 1.0F, 1.25F, 1.2F)
                        .texOffs(31, 1).addBox(-0.50F, -3.8F, 0.8F, 1.0F, 1.15F, 1.2F),
                PartPose.offset(0.0F, -3.15F, -0.65F));
        head.addOrReplaceChild("small_comb", CubeListBuilder.create().texOffs(28, 0)
                        .addBox(-0.45F, -1.5F, -0.65F, 0.9F, 1.5F, 3.0F),
                PartPose.offset(0.0F, -3.2F, -0.55F));
        head.addOrReplaceChild("pea_comb", CubeListBuilder.create().texOffs(28, 0)
                        .addBox(-1.25F, -0.8F, -1.4F, 2.5F, 0.8F, 3.2F, new CubeDeformation(0.10F)),
                PartPose.offset(0.0F, -3.25F, -0.2F));
        head.addOrReplaceChild("wattles", CubeListBuilder.create().texOffs(38, 0)
                        .addBox(-1.35F, 0.0F, -0.45F, 1.15F, 2.6F, 0.9F)
                        .addBox(0.20F, 0.0F, -0.45F, 1.15F, 2.6F, 0.9F),
                PartPose.offset(0.0F, 0.65F, -3.25F));
        head.addOrReplaceChild("silkie_crest", CubeListBuilder.create().texOffs(0, 10)
                        .addBox(-2.8F, -1.4F, -2.4F, 5.6F, 2.8F, 4.8F, new CubeDeformation(0.34F)),
                PartPose.offset(0.0F, -3.65F, 0.0F));

        PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 38)
                        .addBox(-0.65F, 0.0F, -0.65F, 1.3F, 5.0F, 1.3F),
                PartPose.offset(-2.0F, 18.6F, 0.75F));
        rightLeg.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(6, 38)
                        .addBox(-0.85F, -0.35F, -2.15F, 1.7F, 0.7F, 3.25F)
                        .addBox(-1.65F, -0.30F, -1.75F, 3.3F, 0.55F, 0.65F),
                PartPose.offset(0.0F, 5.0F, 0.0F));
        PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 38).mirror()
                        .addBox(-0.65F, 0.0F, -0.65F, 1.3F, 5.0F, 1.3F),
                PartPose.offset(2.0F, 18.6F, 0.75F));
        leftLeg.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(6, 38).mirror()
                        .addBox(-0.85F, -0.35F, -2.15F, 1.7F, 0.7F, 3.25F)
                        .addBox(-1.65F, -0.30F, -1.75F, 3.3F, 0.55F, 0.65F),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(34, 18)
                        .addBox(-1.0F, -3.5F, -3.5F, 1.15F, 7.0F, 7.2F, new CubeDeformation(0.07F)),
                PartPose.offsetAndRotation(-3.65F, 14.8F, 0.25F, 0.02F, -0.10F, 0.08F));
        root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(34, 18).mirror()
                        .addBox(-0.15F, -3.5F, -3.5F, 1.15F, 7.0F, 7.2F, new CubeDeformation(0.07F)),
                PartPose.offsetAndRotation(3.65F, 14.8F, 0.25F, 0.02F, 0.10F, -0.08F));

        addRoosterTail(root, "rooster_tail_center", 0.0F, 12.8F, 4.45F, 2.0F, 9.0F, -0.78F, 0.0F);
        addRoosterTail(root, "rooster_tail_left", -1.45F, 13.25F, 4.25F, 1.6F, 8.0F, -0.68F, -0.18F);
        addRoosterTail(root, "rooster_tail_right", 1.45F, 13.25F, 4.25F, 1.6F, 8.0F, -0.68F, 0.18F);
        addRoosterTail(root, "rooster_tail_outer_left", -2.55F, 14.0F, 4.0F, 1.25F, 6.8F, -0.55F, -0.30F);
        addRoosterTail(root, "rooster_tail_outer_right", 2.55F, 14.0F, 4.0F, 1.25F, 6.8F, -0.55F, 0.30F);
        addHenTail(root, "hen_tail_center", 0.0F, 14.3F, 4.3F, 2.4F, -0.46F, 0.0F);
        addHenTail(root, "hen_tail_left", -1.65F, 14.65F, 4.15F, 1.8F, -0.40F, -0.20F);
        addHenTail(root, "hen_tail_right", 1.65F, 14.65F, 4.15F, 1.8F, -0.40F, 0.20F);
        root.addOrReplaceChild("silkie_tail", CubeListBuilder.create().texOffs(40, 38)
                        .addBox(-2.7F, -2.7F, -1.0F, 5.4F, 5.4F, 2.0F, new CubeDeformation(0.35F)),
                PartPose.offsetAndRotation(0.0F, 15.2F, 4.0F, -0.28F, 0.0F, 0.0F));

        root.addOrReplaceChild("right_spur", CubeListBuilder.create().texOffs(10, 40)
                        .addBox(-0.45F, -0.35F, 0.0F, 0.9F, 0.7F, 2.0F),
                PartPose.offsetAndRotation(-2.0F, 21.1F, 1.2F, -0.42F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_spur", CubeListBuilder.create().texOffs(10, 40).mirror()
                        .addBox(-0.45F, -0.35F, 0.0F, 0.9F, 0.7F, 2.0F),
                PartPose.offsetAndRotation(2.0F, 21.1F, 1.2F, -0.42F, 0.0F, 0.0F));
        root.addOrReplaceChild("right_leg_feathers", CubeListBuilder.create().texOffs(14, 38)
                        .addBox(-1.15F, -1.2F, -1.05F, 2.3F, 2.8F, 2.1F, new CubeDeformation(0.20F)),
                PartPose.offset(-2.0F, 19.2F, 0.75F));
        root.addOrReplaceChild("left_leg_feathers", CubeListBuilder.create().texOffs(14, 38).mirror()
                        .addBox(-1.15F, -1.2F, -1.05F, 2.3F, 2.8F, 2.1F, new CubeDeformation(0.20F)),
                PartPose.offset(2.0F, 19.2F, 0.75F));
        root.addOrReplaceChild("silkie_body_fluff", CubeListBuilder.create().texOffs(0, 18)
                        .addBox(-4.0F, -4.3F, -4.5F, 8.0F, 8.6F, 9.0F, new CubeDeformation(0.38F)),
                PartPose.offsetAndRotation(0.0F, 15.4F, 0.45F, 1.34F, 0.0F, 0.0F));
        root.addOrReplaceChild("brahma_shoulders", CubeListBuilder.create().texOffs(18, 22)
                        .addBox(-4.15F, -2.7F, -2.7F, 8.3F, 5.4F, 5.4F, new CubeDeformation(0.20F)),
                PartPose.offsetAndRotation(0.0F, 14.0F, -0.9F, 1.15F, 0.0F, 0.0F));

        addChickParts(root);
        return LayerDefinition.create(mesh, 64, 64);
    }

    private static void addRoosterTail(PartDefinition root, String name, float x, float y, float z,
                                       float width, float height, float xRot, float zRot) {
        root.addOrReplaceChild(name, CubeListBuilder.create().texOffs(24, 38)
                        .addBox(-width / 2.0F, -height + 1.0F, 0.0F, width, height, 1.0F,
                                new CubeDeformation(0.05F)),
                PartPose.offsetAndRotation(x, y, z, xRot, 0.0F, zRot));
    }

    private static void addHenTail(PartDefinition root, String name, float x, float y, float z,
                                   float width, float xRot, float zRot) {
        root.addOrReplaceChild(name, CubeListBuilder.create().texOffs(40, 38)
                        .addBox(-width / 2.0F, -3.2F, 0.0F, width, 4.2F, 1.0F,
                                new CubeDeformation(0.06F)),
                PartPose.offsetAndRotation(x, y, z, xRot, 0.0F, zRot));
    }

    private static void addChickParts(PartDefinition root) {
        root.addOrReplaceChild("chick_body", CubeListBuilder.create().texOffs(0, 18)
                        .addBox(-2.65F, -2.6F, -3.0F, 5.3F, 5.2F, 6.0F, new CubeDeformation(0.22F)),
                PartPose.offsetAndRotation(0.0F, 18.35F, 0.6F, 1.34F, 0.0F, 0.0F));
        root.addOrReplaceChild("chick_head", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-1.9F, -2.25F, -1.9F, 3.8F, 4.0F, 3.8F, new CubeDeformation(0.10F))
                        .texOffs(18, 0).addBox(-1.05F, -0.55F, -3.1F, 2.1F, 1.0F, 1.35F),
                PartPose.offset(0.0F, 15.05F, -2.85F));
        root.addOrReplaceChild("chick_right_wing", CubeListBuilder.create().texOffs(34, 18)
                        .addBox(-0.6F, -1.8F, -1.9F, 0.7F, 3.6F, 3.8F),
                PartPose.offset(-2.65F, 18.1F, 0.45F));
        root.addOrReplaceChild("chick_left_wing", CubeListBuilder.create().texOffs(34, 18).mirror()
                        .addBox(-0.1F, -1.8F, -1.9F, 0.7F, 3.6F, 3.8F),
                PartPose.offset(2.65F, 18.1F, 0.45F));
        root.addOrReplaceChild("chick_right_leg", CubeListBuilder.create().texOffs(0, 38)
                        .addBox(-0.4F, 0.0F, -0.4F, 0.8F, 2.4F, 0.8F)
                        .texOffs(6, 38).addBox(-0.65F, 2.1F, -1.05F, 1.3F, 0.45F, 1.5F),
                PartPose.offset(-1.35F, 21.4F, 0.55F));
        root.addOrReplaceChild("chick_left_leg", CubeListBuilder.create().texOffs(0, 38).mirror()
                        .addBox(-0.4F, 0.0F, -0.4F, 0.8F, 2.4F, 0.8F)
                        .texOffs(6, 38).addBox(-0.65F, 2.1F, -1.05F, 1.3F, 0.45F, 1.5F),
                PartPose.offset(1.35F, 21.4F, 0.55F));
        root.addOrReplaceChild("chick_tail", CubeListBuilder.create().texOffs(40, 38)
                        .addBox(-1.25F, -0.4F, 0.0F, 2.5F, 2.2F, 0.8F),
                PartPose.offsetAndRotation(0.0F, 18.0F, 3.15F, -0.45F, 0.0F, 0.0F));
    }

    @Override
    public void setupAnim(HeritageChicken chicken, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        boolean chick = chicken.isBaby();
        boolean rooster = !chick && chicken.isRooster();
        boolean hen = !chick && chicken.isHen();
        FowlBreed breed = chicken.getBreed();
        boolean silkie = !chick && breed == FowlBreed.SILKIE;
        boolean brahma = !chick && breed == FowlBreed.BRAHMA;
        boolean featheredLegs = silkie || brahma;

        setAdultVisible(!chick);
        setChickVisible(chick);
        silkieBodyFluff.visible = silkie;
        brahmaShoulders.visible = brahma;
        silkieCrest.visible = silkie;
        peaComb.visible = brahma;
        largeComb.visible = rooster && !silkie && !brahma;
        smallComb.visible = hen && !silkie && !brahma;
        wattles.visible = rooster && !silkie;
        rightSpur.visible = rooster;
        leftSpur.visible = rooster;
        rightLegFeathers.visible = featheredLegs;
        leftLegFeathers.visible = featheredLegs;
        setRoosterTailVisible(rooster && !silkie, breed);
        setHenTailVisible(hen && !silkie);
        silkieTail.visible = silkie;
        applyBreedPose(breed, chick);

        float walkRight = Mth.cos(limbSwing * 0.72F) * 1.15F * limbSwingAmount;
        float walkLeft = Mth.cos(limbSwing * 0.72F + Mth.PI) * 1.15F * limbSwingAmount;
        float idle = Mth.sin(ageInTicks * 0.16F) * 0.045F;
        float flap = chicken.onGround()
                ? 0.05F + Mth.sin(ageInTicks * 0.12F) * 0.025F
                : 0.82F + Mth.sin(ageInTicks * 1.8F) * 0.38F;

        if (chick) {
            chickHead.xRot = headPitch * Mth.DEG_TO_RAD + idle;
            chickHead.yRot = netHeadYaw * Mth.DEG_TO_RAD;
            chickRightLeg.xRot = walkRight;
            chickLeftLeg.xRot = walkLeft;
            chickRightWing.zRot = flap * 0.75F;
            chickLeftWing.zRot = -flap * 0.75F;
            chickTail.yRot = Mth.sin(ageInTicks * 0.22F) * 0.08F;
            return;
        }

        head.xRot = headPitch * Mth.DEG_TO_RAD + idle;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        neck.xRot = -0.20F + head.xRot * 0.22F;
        neck.yRot = head.yRot * 0.30F;
        rightLeg.xRot = walkRight;
        leftLeg.xRot = walkLeft;
        rightWing.zRot = 0.08F + flap;
        leftWing.zRot = -0.08F - flap;

        float tailSway = Mth.sin(ageInTicks * 0.11F) * 0.055F;
        roosterTailCenter.yRot = tailSway;
        roosterTailLeft.yRot = tailSway - 0.10F;
        roosterTailRight.yRot = tailSway + 0.10F;
        roosterTailOuterLeft.yRot = tailSway - 0.16F;
        roosterTailOuterRight.yRot = tailSway + 0.16F;
        henTailCenter.yRot = tailSway;
        henTailLeft.yRot = tailSway - 0.08F;
        henTailRight.yRot = tailSway + 0.08F;
        silkieTail.yRot = tailSway;
    }

    private void applyBreedPose(FowlBreed breed, boolean chick) {
        if (chick) {
            chickHead.y = 15.05F;
            chickBody.y = 18.35F;
            return;
        }
        body.y = 15.2F;
        breast.y = 15.3F;
        saddle.y = 14.7F;
        neck.y = 12.0F;
        head.y = 8.8F;
        rightLeg.y = 18.6F;
        leftLeg.y = 18.6F;
        switch (breed) {
            case CRIOLLO_DOMINICANO -> {
                head.y = 8.25F;
                neck.y = 11.55F;
                rightLeg.y = 18.1F;
                leftLeg.y = 18.1F;
            }
            case RHODE_ISLAND_RED, PLYMOUTH_ROCK, SUSSEX -> {
                body.y = 15.55F;
                breast.y = 15.65F;
                head.y = 9.0F;
            }
            case LEGHORN, AYAM_CEMANI -> {
                head.y = 8.15F;
                neck.y = 11.45F;
                breast.y = 15.0F;
            }
            case BRAHMA -> {
                body.y = 15.75F;
                breast.y = 15.8F;
                head.y = 9.15F;
                rightLeg.y = 19.0F;
                leftLeg.y = 19.0F;
            }
            case SILKIE -> {
                body.y = 15.8F;
                breast.y = 15.9F;
                head.y = 9.45F;
                rightLeg.y = 19.15F;
                leftLeg.y = 19.15F;
            }
        }
    }

    private void setAdultVisible(boolean visible) {
        body.visible = visible;
        breast.visible = visible;
        saddle.visible = visible;
        neck.visible = visible;
        head.visible = visible;
        rightLeg.visible = visible;
        leftLeg.visible = visible;
        rightWing.visible = visible;
        leftWing.visible = visible;
    }

    private void setChickVisible(boolean visible) {
        chickBody.visible = visible;
        chickHead.visible = visible;
        chickRightWing.visible = visible;
        chickLeftWing.visible = visible;
        chickRightLeg.visible = visible;
        chickLeftLeg.visible = visible;
        chickTail.visible = visible;
    }

    private void setRoosterTailVisible(boolean visible, FowlBreed breed) {
        roosterTailCenter.visible = visible;
        roosterTailLeft.visible = visible;
        roosterTailRight.visible = visible;
        boolean longTail = breed == FowlBreed.CRIOLLO_DOMINICANO
                || breed == FowlBreed.LEGHORN
                || breed == FowlBreed.AYAM_CEMANI;
        roosterTailOuterLeft.visible = visible && longTail;
        roosterTailOuterRight.visible = visible && longTail;
    }

    private void setHenTailVisible(boolean visible) {
        henTailCenter.visible = visible;
        henTailLeft.visible = visible;
        henTailRight.visible = visible;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int light, int overlay, int color) {
        root.render(poseStack, consumer, light, overlay, color);
    }
}
