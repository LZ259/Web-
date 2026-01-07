package org.example;

public class ImageProcessingLab {

    public static void main(String[] args) {
        System.out.println("🎨 开始图像预处理实验");
        System.out.println("OpenCV 版本: " + org.opencv.core.Core.VERSION);
        System.out.println("==========================================");

        try {
            // 执行所有实验
            ImageUtils.experiment1_BasicImages();    // 实验1
            ImageUtils.experiment2_ArithmeticOperations(); // 实验2
            ImageUtils.experiment3_GrayTransformations(); // 实验3
            ImageUtils.experiment4_HistogramEqualization(); // 实验4
            ImageUtils.experiment5_GeometricTransformations(); // 实验5
            ImageUtils.experiment6_FourierTransform(); // 实验6
            ImageUtils.experiment7_NoiseAndFilter(); // 实验7
            ImageUtils.experiment8_EdgeDetection(); // 实验8

            System.out.println("==========================================");
            System.out.println("🎉 所有实验完成！");
            System.out.println("📁 结果图像保存在: src/main/resources/images/");
            System.out.println("包含:");
            System.out.println("  - 实验1: 基本图像转换（原图、灰度、二值）");
            System.out.println("  - 实验2: 算术运算（加、减、乘、除）");
            System.out.println("  - 实验3: 灰度变换（变亮、变暗、负片等）");
            System.out.println("  - 实验4: 直方图均衡化");
            System.out.println("  - 实验5: 几何变换（缩放、旋转、翻转）");
            System.out.println("  - 实验6: 傅里叶变换");
            System.out.println("  - 实验7: 噪声与滤波");
            System.out.println("  - 实验8: 边缘检测");

        } catch (Exception e) {
            System.err.println("❌ 实验出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}