package com.wujue.es.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Hashtable;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/12/2 上午10:39
 * @since jdk11
 */
public class QrCodeUtil {
    private static float jPEGcompression = 0.75f;// 图片清晰比率
    /**
     * 二维码图片的生成
     * @param content			链接
     * @param qrcode_width		二维码宽
     * @param qrcode_height		二维码高
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String content, int qrcode_width, int qrcode_height) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, qrcode_width, qrcode_height, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        return image;
    }
    /**
     * 图片制作
     * @param backImage 背景图地址
     * @param srcImage  二维码地址
     * @param descImage //生成后的图片
     * @param descImageLeft
     * @param descImageTop
     */
    public static void makeImage(String backImage, BufferedImage srcImage , String descImage,
                                 int descImageLeft,int descImageTop) {
        try {
            int offset = 0;
            BufferedImage backBufferedImage = ImageIO.read(new File(backImage));
            // 输出图片宽度
            int width = backBufferedImage.getWidth() + offset;
            // 输出图片高度
            int height = backBufferedImage.getHeight() + offset;
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // 往画布上添加图片,并设置边距
            graphics2d.drawImage(backBufferedImage, null, 0, 0);
            graphics2d.drawImage(srcImage, null, descImageLeft, descImageTop);
            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, backImage.substring(backImage.lastIndexOf(".")+1, backImage.length()), new File(descImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description : 将二维码图片和文字生成到一张图片上
     * @Param : originalImg 原图
     * @Param : qrCodeImg 二维码地址
     * @Param : shareDesc 图片文字
     * @return : java.lang.String
     * @Author : houzhenghai
     * @Date : 2018/8/15
     */
    public static String generateImg(String originalImg, BufferedImage qrCodeImg, String shareDesc) throws Exception {
        // 加载原图图片
        BufferedImage imageLocal = ImageIO.read(new File(originalImg));
        // 加载用户的二维码
        BufferedImage imageCode = qrCodeImg;
        // 以原图片为模板
        Graphics2D g = imageLocal.createGraphics();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setComposite(ac);
        g.setBackground(Color.WHITE);
        // 在模板上添加用户二维码(地址,左边距,上边距,图片宽度,图片高度,未知)
        //g.drawImage(imageCode, 100, imageLocal.getHeight() - 190, 160, 158, null);
        //g.drawImage(imageCode, 100, imageLocal.getHeight() - 50, 43, 43, null);
        g.drawImage(imageCode, 760, imageLocal.getHeight() - 340, 300, 300, null);
        // 设置文本样式
        g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        g.setColor(Color.red);
        // 计算文字长度，计算居中的x点坐标
      /*
      g.drawString(shareDesc, imageLocal.getWidth() - 330, imageLocal.getHeight() - 530);
      */
        // 设置文本样式
        g.setFont(new Font("微软雅黑", Font.PLAIN + Font.BOLD, 16));
        g.setColor(Color.WHITE);
        // 计算文字长度，计算居中的x点坐标
      /*
      String caewm = "长按二维码";
      g.drawString(caewm, 105, imageLocal.getHeight() - 10);
      */
        ByteArrayOutputStream out = new ByteArrayOutputStream();





        saveAsJPEG(imageLocal, out);

        FileOutputStream fileOutputStream = new FileOutputStream( new File( "/d/ideaWorkspace/es/src/main/resources/static/img/test.jpg" ) ) ;

        out.writeTo(fileOutputStream) ;

        fileOutputStream.flush();
        out.close();
        //return urlImgDownInputStream(FileUtils.parse(out));
        return null;
    }
    /**
     * 以JPEG编码保存图片
     *
     * @param image_to_save
     *            要处理的图像图片
     * @param fos
     *            文件输出流
     * @throws IOException
     */
    private static void saveAsJPEG(BufferedImage imageToSave, ByteArrayOutputStream fos) throws IOException {
        ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
        imageWriter.setOutput(ios);
        if (jPEGcompression >= 0 && jPEGcompression <= 1f) {
            // new Compression
            JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(jPEGcompression);

        }
        // new Write and clean up
        ImageIO.setUseCache(false);
        imageWriter.write(new IIOImage(imageToSave, null, null));
        ios.close();
        imageWriter.dispose();
    }
    public static void main(String[] args) throws Exception {
        BufferedImage src = createImage("http://www.baidu.com",100,100);
        String img = generateImg("/d/ideaWorkspace/es/src/main/resources/static/img/timg.jpg",src,"");
        System.out.println(img);
    }
}
