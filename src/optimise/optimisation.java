package optimise;

import messages.errorSuccess;

import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

public class optimisation {

    File watermark;

    public void compress(String path, String new_name) {

        try {
            File input = new File(path);
            BufferedImage image = ImageIO.read(input);

            File output = new File(new_name);
            OutputStream out = new FileOutputStream(output);

            ImageWriter writer =  ImageIO.getImageWritersByFormatName("jpg").next();
            ImageOutputStream ios = ImageIO.createImageOutputStream(out);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()){
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(1.0f);
            }

            writer.write(null, new IIOImage(image, null, null), param);

            out.close();
            ios.close();
            writer.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            errorSuccess errorSuccessObj = new errorSuccess();
            errorSuccessObj.successMessage();
        }
    }

    public void watermark(String path, String newName) {
        try {
//            watermark = new File("../media/watermark.jpg");
            watermark = new File("/home/wangui/Classwork/Multimedia Systems and Applications/Project/optimage/src/media/watermark.jpg");
            File fileToWatermark = new File(path);
            BufferedImage imageToWatermark = ImageIO.read(fileToWatermark);
            BufferedImage watermarkImage = ImageIO.read(watermark);

            // initializes necessary graphic properties
            Graphics2D g2d = (Graphics2D) imageToWatermark.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            g2d.setComposite(alphaChannel);

            // calculates the coordinate where the image is painted
            int topLeftX = (imageToWatermark.getWidth() - watermarkImage.getWidth()) / 2;
            int topLeftY = (imageToWatermark.getHeight() - watermarkImage.getHeight()) / 2;

            // paints the image watermark
            g2d.drawImage(watermarkImage, topLeftX, topLeftY, null);

//            ImageIO.write(imageToWatermark, "jpg", destImageFile);
            File newImagePath = new File(newName);
            ImageIO.write(imageToWatermark, "jpg", newImagePath);
//            ImageIO.write(newImagePath);
            g2d.dispose();

            System.out.println("The image watermark is added to the image.");

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}


