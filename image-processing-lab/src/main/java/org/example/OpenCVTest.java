
package org.example;

import nu.pattern.OpenCV;

public class OpenCVTest {
    static {
        // 加载 OpenCV 本地库
        OpenCV.loadLocally();
    }

    public static void main(String[] args) {
        System.out.println("=== OpenCV 测试 ===");

        try {
            // 测试 OpenCV 核心功能
            System.out.println("OpenCV 版本: " + org.opencv.core.Core.VERSION);

            // 创建一个简单的矩阵
            org.opencv.core.Mat mat = org.opencv.core.Mat.eye(3, 3, org.opencv.core.CvType.CV_8UC1);
            System.out.println("测试矩阵创建成功:");
            System.out.println(mat.dump());

            System.out.println("✅ OpenCV 配置成功！");

        } catch (Exception e) {
            System.err.println("❌ OpenCV 配置失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}