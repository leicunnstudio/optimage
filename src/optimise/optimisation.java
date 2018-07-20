package optimise;

import messages.errorSuccess;

import java.awt.*;
import javaQuery.j2ee.ImageResize;
import java.awt.image.*;
import java.net.URISyntaxException;
import javax.imageio.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class optimisation {

    private Boolean success;

    public void compress(String path, String newName) {

        try {
            ImageResize ir = new ImageResize();
            ir.compressImage(path, newName, 1280, 720);

            // This will be used to ensure a success message shows ONLY IF no exception is thrown
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getMessage(success);
        }

    }

    private void getMessage(Boolean checkSuccess) {
        if (success) {
            errorSuccess errorSuccessObj = new errorSuccess();
            errorSuccessObj.successMessage();
        } else {
            errorSuccess errorSuccessObj = new errorSuccess();
            errorSuccessObj.errorMessage();
        }
    }

    public void watermark(String path, String newName) {

        File watermark;
        File newResized;
        BufferedImage resizedWatermark;

        try {

            //Get the watermark image to be used
            watermark = new File(getClass().getResource("../media/watermark.jpg").toURI());
            File fileToWatermark = new File(path);
            BufferedImage imageToWatermark = ImageIO.read(fileToWatermark);
            BufferedImage watermarkImage = ImageIO.read(watermark);

            // This sets the height and width to use when resizing
            BufferedImage resized = resize(watermarkImage, imageToWatermark.getHeight(), imageToWatermark.getWidth());

            // This saves the resized watermark image
            newResized = new File(".watermark-resized.png");
            ImageIO.write(resized, "png", newResized);

            //re-assign the watermark to the new image
            resizedWatermark = ImageIO.read(newResized);

            // initializes necessary graphic properties
            Graphics2D g2d = (Graphics2D) imageToWatermark.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
            g2d.setComposite(alphaChannel);

            // paints the image watermark
            g2d.drawImage(imageToWatermark, 0, 0, null);
            g2d.drawImage(resizedWatermark, 0, 0, null);

            File newImagePath = new File(newName);
            ImageIO.write(imageToWatermark, "jpg", newImagePath);
            g2d.dispose();

            System.out.println("The image watermark is added to the image.");

            // Delete the stretched new watermark
            Files.deleteIfExists(Paths.get(newResized.toURI()));

            // This will be used to ensure a success message shows ONLY IF no exception is thrown
            success = true;

        } catch (IOException | URISyntaxException ex) {
            System.err.println(ex);
//            ex.printStackTrace();
        } finally {
            getMessage(success);
        }

    }

//  RESIZES THE WATERMARK WITH REFERENCE TO THE IMAGES TO BE WATERMARKED
    private static BufferedImage resize(BufferedImage img, int height, int width) {

        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;

    }


}


