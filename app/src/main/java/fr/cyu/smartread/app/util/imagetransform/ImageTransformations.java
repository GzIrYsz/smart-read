package fr.cyu.smartread.app.util.imagetransform;

import org.ejml.data.DMatrixRMaj;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageTransformations {
    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        try {
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            return resizedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DMatrixRMaj imageToMatrix(BufferedImage image) {
        double[] imgData = imageToDoubleArray(image);
        return new DMatrixRMaj(1, imgData.length, true, imgData);
    }

    public static double[] imageToDoubleArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double[] pixelValues = new double[width * height];

        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                double gray = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());
                pixelValues[index++] = gray;
            }
        }

        return pixelValues;
    }
}
