package com.tencent.user.utlis;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class ImageCodeUtil {
    private final static String[] code = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            , "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"
            , "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V"
            , "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g"
            , "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u"
            , "v", "w", "x", "y", "z"};

//  TODO 将验证码放入缓存
    public static String getImageCode() {
        Random r = new Random();
        String res = null;
//        图片的宽高
        int width = 100;
        int height = 50;
//        参数：宽、高、类型
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        获取画笔对象
        Graphics graphics = bufferedImage.getGraphics();
//        设置画笔颜色
        graphics.setColor(Color.BLUE);
//        填充画布
        graphics.fillRect(0, 0, width, height);
//设置字体
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 16));
        int x = 15, y = 30;
        for (int i = 0; i < 4; i++) {
            String index = code[r.nextInt(code.length)];
            graphics.setColor(Color.RED);
            graphics.drawString(index, x, y);
            x += 15;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "png", stream);
            byte[] byteArray = stream.toByteArray();
            res = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}
