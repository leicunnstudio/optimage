package optimise;

import messages.errorSuccess;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

public class optimisation {

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

    }
}
