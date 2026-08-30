package com.eyrax.realroosters.client;

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

public final class HeritageChickenModel extends EntityModel<HeritageChicken> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart roosterTail;
    private final ModelPart henTail;
    private final ModelPart largeComb;
    private final ModelPart smallComb;
    private final ModelPart wattles;
    private final ModelPart spurs;

    public HeritageChickenModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
        this.roosterTail = root.getChild("rooster_tail");
        this.henTail = root.getChild("hen_tail");
        this.largeComb = head.getChild("large_comb");
        this.smallComb = head.getChild("small_comb");
        this.wattles = head.getChild("wattles");
        this.spurs = root.getChild("spurs");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 18)
                .addBox(-3.5F, -4.5F, -5.0F, 7.0F, 9.0F, 10.0F, new CubeDeformation(0.15F)),
                PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, Mth.HALF_PI, 0.0F, 0.0F));
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0)
                .addBox(-2.25F, -4.0F, -3.0F, 4.5F, 6.0F, 4.5F)
                .texOffs(18, 0).addBox(-2.0F, -1.5F, -5.0F, 4.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, 11.5F, -4.0F));
        head.addOrReplaceChild("large_comb", CubeListBuilder.create().texOffs(28, 0)
                .addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 4.0F), PartPose.offset(0.0F, -4.0F, -1.5F));
        head.addOrReplaceChild("small_comb", CubeListBuilder.create().texOffs(28, 0)
                .addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.5F, 3.0F), PartPose.offset(0.0F, -4.0F, -1.5F));
        head.addOrReplaceChild("wattles", CubeListBuilder.create().texOffs(38, 0)
                .addBox(-1.5F, 0.0F, -0.5F, 3.0F, 3.0F, 1.0F), PartPose.offset(0.0F, 1.2F, -3.6F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 38)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(-2.0F, 18.0F, 1.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 38).mirror()
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(2.0F, 18.0F, 1.0F));
        root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(34, 18)
                .addBox(-1.0F, 0.0F, -4.0F, 1.0F, 7.0F, 8.0F), PartPose.offset(-3.5F, 13.0F, 0.0F));
        root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(34, 18).mirror()
                .addBox(0.0F, 0.0F, -4.0F, 1.0F, 7.0F, 8.0F), PartPose.offset(3.5F, 13.0F, 0.0F));
        root.addOrReplaceChild("rooster_tail", CubeListBuilder.create().texOffs(24, 38)
                .addBox(-3.0F, -1.0F, 0.0F, 6.0F, 8.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 12.5F, 4.0F, -0.65F, 0.0F, 0.0F));
        root.addOrReplaceChild("hen_tail", CubeListBuilder.create().texOffs(40, 38)
                .addBox(-2.5F, -1.0F, 0.0F, 5.0F, 5.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 14.0F, 4.0F, -0.4F, 0.0F, 0.0F));
        root.addOrReplaceChild("spurs", CubeListBuilder.create().texOffs(10, 40)
                .addBox(-3.1F, 1.0F, 1.0F, 6.2F, 1.0F, 2.0F), PartPose.offset(0.0F, 19.0F, 1.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(HeritageChicken chicken, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
        float flap = chicken.onGround() ? 0.08F : 0.75F + Mth.sin(ageInTicks * 1.8F) * 0.35F;
        rightWing.zRot = flap;
        leftWing.zRot = -flap;
        boolean rooster = chicken.isRooster() && !chicken.isBaby();
        roosterTail.visible = rooster;
        largeComb.visible = rooster;
        wattles.visible = rooster;
        spurs.visible = rooster;
        henTail.visible = !rooster;
        smallComb.visible = !rooster;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int light, int overlay, int color) {
        root.render(poseStack, consumer, light, overlay, color);
    }
}
