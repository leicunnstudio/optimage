package optimise;

//import org.xerial.snappy.Snappy;

import messages.errorSuccess;

import java.awt.*;
import java.io.*;
import java.awt.image.*;
import java.net.URISyntaxException;
import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
//import javax.imageio.stream.ImageOutputStream;

import java.io.IOException;
import java.io.File;
import java.lang.Object;
import java.util.Iterator;

public class optimisation {

    File watermark;

    public void compress(String path, String newName) {


//        public class HuffmanEncodingTest extends TestCase {

//        public boolean isSameFile(fileCharIterator it, fileCharIterator it2) {
//            while (it.hasNext() && it2.hasNext()) {
//                String char1 = it.next();
//                String char2 = it2.next();
//
//                if (!char1.equals(char2))
//                    return false;
//            }
//            return true;
//        }


//        public void testImageFile() {
            // encode
//            huffmanEncoding.encode(path, newName, 0);

            // decode
//            huffmanEncoding.decode("HangInThere.jpg.huffman", "myHangInThere.jpg");

            // check if these two files are the same
//            boolean equals = isSameFile(new fileCharIterator("myHangInThere.jpg"),
//                    new fileCharIterator("HangInThere.jpg"));
//            assertEquals(true, equals);
//        }

        try {
            File input = new File(path);
            BufferedImage image = ImageIO.read(input);

            File output = new File(newName);
            OutputStream out = new FileOutputStream(output);

            ImageWriter writer =  ImageIO.getImageWritersByFormatName("jpg").next();
            ImageOutputStream ios = ImageIO.createImageOutputStream(out);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()){
                param.setCompressionMode(ImageWriteParam.MODE_COPY_FROM_METADATA);
                param.setCompressionType("LZW");
                param.setCompressionQuality(1f);
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



        // String fileName = "4958813_1";
//        String fileName = "4848970_1";
//        String inFileType = ".PNG";
//        String outFileType = ".TIFF";

//        try {
//            ImageIO.scanForPlugins();
//
//            File fInputFile = new File(path);
//            InputStream fis = new BufferedInputStream(new FileInputStream(
//                    fInputFile));
//            PNGImageReaderSpi spi = new PNGImageReaderSpi();
//            ImageReader reader = spi.createReaderInstance();
//
//            ImageInputStream iis = ImageIO.createImageInputStream(fis);
//            reader.setInput(iis, true);
//            BufferedImage bi = reader.read(0);
//
//            TIFFImageWriterSpi tiffspi = new TIFFImageWriterSpi();
//            ImageWriter writer = tiffspi.createWriterInstance();
            //Iterator<ImageWriter> iter =  ImageIO.getImageWritersByFormatName("TIFF");
            //ImageWriter writer = iter.next();
//
//            ImageWriteParam param = writer.getDefaultWriteParam();
//            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//
//            param.setCompressionType("LZW");
//            param.setCompressionQuality(1f);
//            File fOutputFile = new File(newName);
//            ImageOutputStream ios = ImageIO.createImageOutputStream(fOutputFile);
//            writer.setOutput(ios);
//            writer.write(bi);
//            writer.write(null, new IIOImage(bi, null, null), param);
//        } catch (IOException ioex) {
//            System.err.println(ioex);
//        }

//        ImageIO.scanForPlugins();
//
//        File fInputFile = new File(path);
//        InputStream fis = new BufferedInputStream(new FileInputStream(
//                fInputFile));
//        PNGImageReaderSpi spi = new PNGImageReaderSpi();
//        ImageReader reader = spi.createReaderInstance();
//
//        ImageInputStream iis = ImageIO.createImageInputStream(fis);
//        reader.setInput(iis, true);
//        BufferedImage bi = reader.read(0);
//
//        TIFFImageWriterSpi tiffspi = new TIFFImageWriterSpi();
//        ImageWriter writer = tiffspi.createWriterInstance();
//        //Iterator<ImageWriter> iter =  ImageIO.getImageWritersByFormatName("TIFF");
//        //ImageWriter writer = iter.next();
//
//        ImageWriteParam param = writer.getDefaultWriteParam();
//        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//
//        param.setCompressionType("LZW");
//        param.setCompressionQuality(0.5f);
//        File fOutputFile = new File(newName);
//        ImageOutputStream ios = ImageIO.createImageOutputStream(fOutputFile);
//        writer.setOutput(ios);
//        writer.write(bi);



    }

    public void watermark(String path, String newName) {
        try {
            watermark = new File(getClass().getResource("../media/watermark.jpg").toURI());
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

            File newImagePath = new File(newName);
            ImageIO.write(imageToWatermark, "jpg", newImagePath);
            g2d.dispose();

            System.out.println("The image watermark is added to the image.");

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (URISyntaxException synex) {
            System.err.println(synex);
        }
        finally {
            errorSuccess errorSuccessObj = new errorSuccess();
            errorSuccessObj.successMessage();
        }
    }
}


