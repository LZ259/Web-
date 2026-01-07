package org.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageUtils {

    static {
        nu.pattern.OpenCV.loadLocally();
    }

    // 实验1：读取并显示原图、灰度图、二值图
    public static void experiment1_BasicImages() {
        System.out.println("=== 实验1：基本图像转换 ===");

        // 创建测试图像
        Mat colorImage = createTestImage();

        // 转换为灰度图
        Mat grayImage = new Mat();
        Imgproc.cvtColor(colorImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // 转换为二值图
        Mat binaryImage = new Mat();
        Imgproc.threshold(grayImage, binaryImage, 127, 255, Imgproc.THRESH_BINARY);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/1_original.jpg", colorImage);
        Imgcodecs.imwrite("src/main/resources/images/1_gray.jpg", grayImage);
        Imgcodecs.imwrite("src/main/resources/images/1_binary.jpg", binaryImage);

        System.out.println("✅ 实验1完成 - 原图、灰度图、二值图已保存");
    }

    // 实验2：图像算术运算
    public static void experiment2_ArithmeticOperations() {
        System.out.println("=== 实验2：图像算术运算 ===");

        Mat img1 = createTestImage();
        Mat img2 = createDifferentImage();

        // 调整大小一致
        Imgproc.resize(img2, img2, img1.size());

        Mat add = new Mat(), sub = new Mat(), mul = new Mat(), div = new Mat();
        Core.add(img1, img2, add);
        Core.subtract(img1, img2, sub);
        Core.multiply(img1, img2, mul);
        Core.divide(img1, img2, div);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/2_add.jpg", add);
        Imgcodecs.imwrite("src/main/resources/images/2_subtract.jpg", sub);
        Imgcodecs.imwrite("src/main/resources/images/2_multiply.jpg", mul);
        Imgcodecs.imwrite("src/main/resources/images/2_divide.jpg", div);

        System.out.println("✅ 实验2完成 - 加、减、乘、除运算结果已保存");
    }

    // 实验3：灰度变换
    public static void experiment3_GrayTransformations() {
        System.out.println("=== 实验3：灰度变换 ===");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(createTestImage(), grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat bright = new Mat();
        Mat dark = new Mat();
        Mat negative = new Mat();
        Mat logTrans = new Mat();
        Mat powTrans = new Mat();

        // 线性变换 - 修正 Scalar 构造函数
        Core.multiply(grayImage, new Scalar(1.8), bright); // 变亮
        Core.multiply(grayImage, new Scalar(0.4), dark);   // 变暗

        // 负片效果 - 修正 subtract 方法
        Mat scalar255 = new Mat(grayImage.size(), grayImage.type(), new Scalar(255));
        Core.subtract(scalar255, grayImage, negative); // 负片

        // 对数变换 - 修正方法名和类型
        grayImage.convertTo(logTrans, CvType.CV_32F);
        Core.add(logTrans, new Scalar(1), logTrans);
        Core.log(logTrans, logTrans);
        Core.normalize(logTrans, logTrans, 0, 255, Core.NORM_MINMAX);
        logTrans.convertTo(logTrans, CvType.CV_8UC1);

        // 幂次变换
        grayImage.convertTo(powTrans, CvType.CV_32F);
        Core.normalize(powTrans, powTrans, 0, 1, Core.NORM_MINMAX);
        Core.pow(powTrans, 0.5, powTrans); // gamma=0.5
        Core.normalize(powTrans, powTrans, 0, 255, Core.NORM_MINMAX);
        powTrans.convertTo(powTrans, CvType.CV_8UC1);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/3_bright.jpg", bright);
        Imgcodecs.imwrite("src/main/resources/images/3_dark.jpg", dark);
        Imgcodecs.imwrite("src/main/resources/images/3_negative.jpg", negative);
        Imgcodecs.imwrite("src/main/resources/images/3_log.jpg", logTrans);
        Imgcodecs.imwrite("src/main/resources/images/3_power.jpg", powTrans);

        System.out.println("✅ 实验3完成 - 灰度变换结果已保存");
    }

    // 实验4：直方图均衡化
    public static void experiment4_HistogramEqualization() {
        System.out.println("=== 实验4：直方图均衡化 ===");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(createTestImage(), grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat equalized = new Mat();
        Imgproc.equalizeHist(grayImage, equalized);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/4_original_gray.jpg", grayImage);
        Imgcodecs.imwrite("src/main/resources/images/4_equalized.jpg", equalized);

        System.out.println("✅ 实验4完成 - 直方图均衡化结果已保存");
    }

    // 实验5：几何变换
    public static void experiment5_GeometricTransformations() {
        System.out.println("=== 实验5：几何变换 ===");

        Mat image = createTestImage();

        Mat resized = new Mat();
        Mat rotated = new Mat();
        Mat flipped = new Mat();

        // 缩放
        Imgproc.resize(image, resized, new Size(image.cols()/2, image.rows()/2));

        // 旋转
        Mat rotateMatrix = Imgproc.getRotationMatrix2D(
                new Point(image.cols()/2, image.rows()/2), 45, 1);
        Imgproc.warpAffine(image, rotated, rotateMatrix, image.size());

        // 翻转
        Core.flip(image, flipped, 1); // 水平翻转

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/5_resized.jpg", resized);
        Imgcodecs.imwrite("src/main/resources/images/5_rotated.jpg", rotated);
        Imgcodecs.imwrite("src/main/resources/images/5_flipped.jpg", flipped);

        System.out.println("✅ 实验5完成 - 几何变换结果已保存");
    }

    // 实验6：傅里叶变换
    public static void experiment6_FourierTransform() {
        System.out.println("=== 实验6：傅里叶变换 ===");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(createTestImage(), grayImage, Imgproc.COLOR_BGR2GRAY);

        // 傅里叶变换
        Mat spectrum = fourierTransform(grayImage);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/6_spectrum.jpg", spectrum);

        System.out.println("✅ 实验6完成 - 傅里叶频谱图已保存");
    }

    // 实验7：噪声与滤波
    public static void experiment7_NoiseAndFilter() {
        System.out.println("=== 实验7：噪声与滤波 ===");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(createTestImage(), grayImage, Imgproc.COLOR_BGR2GRAY);

        // 添加椒盐噪声
        Mat noisy = addSaltAndPepper(grayImage, 0.05);

        // 均值滤波
        Mat meanFiltered = new Mat();
        Imgproc.blur(noisy, meanFiltered, new Size(3, 3));

        // 中值滤波
        Mat medianFiltered = new Mat();
        Imgproc.medianBlur(noisy, medianFiltered, 3);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/7_noisy.jpg", noisy);
        Imgcodecs.imwrite("src/main/resources/images/7_mean_filtered.jpg", meanFiltered);
        Imgcodecs.imwrite("src/main/resources/images/7_median_filtered.jpg", medianFiltered);

        System.out.println("✅ 实验7完成 - 噪声与滤波结果已保存");
    }

    // 实验8：边缘检测
    public static void experiment8_EdgeDetection() {
        System.out.println("=== 实验8：边缘检测 ===");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(createTestImage(), grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat sobel = new Mat();
        Mat laplacian = new Mat();

        // Sobel 算子
        Imgproc.Sobel(grayImage, sobel, CvType.CV_16S, 1, 1);
        Core.convertScaleAbs(sobel, sobel);

        // 拉普拉斯算子
        Imgproc.Laplacian(grayImage, laplacian, CvType.CV_16S);
        Core.convertScaleAbs(laplacian, laplacian);

        // 保存结果
        Imgcodecs.imwrite("src/main/resources/images/8_sobel.jpg", sobel);
        Imgcodecs.imwrite("src/main/resources/images/8_laplacian.jpg", laplacian);

        System.out.println("✅ 实验8完成 - 边缘检测结果已保存");
    }

    // 傅里叶变换实现
    private static Mat fourierTransform(Mat grayImage) {
        // 调整大小为2的幂次
        int rows = Core.getOptimalDFTSize(grayImage.rows());
        int cols = Core.getOptimalDFTSize(grayImage.cols());

        Mat padded = new Mat();
        Core.copyMakeBorder(grayImage, padded, 0, rows - grayImage.rows(),
                0, cols - grayImage.cols(), Core.BORDER_CONSTANT, Scalar.all(0));

        padded.convertTo(padded, CvType.CV_32F);

        List<Mat> planes = new ArrayList<>();
        planes.add(padded);
        planes.add(Mat.zeros(padded.size(), CvType.CV_32F));

        Mat complexI = new Mat();
        Core.merge(planes, complexI);
        Core.dft(complexI, complexI);

        Core.split(complexI, planes);
        Mat magnitude = new Mat();
        Core.magnitude(planes.get(0), planes.get(1), magnitude);

        Core.add(magnitude, new Scalar(1), magnitude);
        Core.log(magnitude, magnitude);

        // 频谱中心化
        Mat spectrum = shiftDFT(magnitude);
        Core.normalize(spectrum, spectrum, 0, 255, Core.NORM_MINMAX);
        spectrum.convertTo(spectrum, CvType.CV_8UC1);

        return spectrum;
    }

    private static Mat shiftDFT(Mat mag) {
        int cx = mag.cols() / 2;
        int cy = mag.rows() / 2;

        Mat q0 = new Mat(mag, new Rect(0, 0, cx, cy));
        Mat q1 = new Mat(mag, new Rect(cx, 0, cx, cy));
        Mat q2 = new Mat(mag, new Rect(0, cy, cx, cy));
        Mat q3 = new Mat(mag, new Rect(cx, cy, cx, cy));

        Mat tmp = new Mat();
        q0.copyTo(tmp);
        q3.copyTo(q0);
        tmp.copyTo(q3);

        q1.copyTo(tmp);
        q2.copyTo(q1);
        tmp.copyTo(q2);

        return mag;
    }

    // 创建测试图像
    private static Mat createTestImage() {
        Mat image = new Mat(300, 400, CvType.CV_8UC3, new Scalar(100, 100, 255));

        // 画各种图形
        Imgproc.rectangle(image, new Point(50, 50), new Point(150, 150), new Scalar(255, 0, 0), -1);
        Imgproc.circle(image, new Point(250, 100), 40, new Scalar(0, 255, 0), -1);

        List<Point> triangle = new ArrayList<>();
        triangle.add(new Point(300, 200));
        triangle.add(new Point(350, 250));
        triangle.add(new Point(250, 250));
        MatOfPoint matTriangle = new MatOfPoint();
        matTriangle.fromList(triangle);
        List<MatOfPoint> triangles = new ArrayList<>();
        triangles.add(matTriangle);
        Imgproc.fillPoly(image, triangles, new Scalar(0, 0, 255));

        Imgproc.line(image, new Point(50, 200), new Point(350, 200), new Scalar(0, 255, 255), 3);

        return image;
    }

    // 创建不同的测试图像用于算术运算
    private static Mat createDifferentImage() {
        Mat image = new Mat(300, 400, CvType.CV_8UC3, new Scalar(50, 150, 50));

        Imgproc.rectangle(image, new Point(100, 100), new Point(200, 200), new Scalar(0, 0, 255), -1);
        Imgproc.circle(image, new Point(300, 150), 30, new Scalar(255, 255, 0), -1);

        return image;
    }

    // 添加椒盐噪声
    private static Mat addSaltAndPepper(Mat image, double noiseRatio) {
        Mat result = image.clone();
        int noisePixels = (int) (image.total() * noiseRatio);
        Random random = new Random();

        for (int i = 0; i < noisePixels; i++) {
            int row = random.nextInt(image.rows());
            int col = random.nextInt(image.cols());

            if (random.nextDouble() < 0.5) {
                result.put(row, col, 0);
            } else {
                result.put(row, col, 255);
            }
        }
        return result;
    }
}