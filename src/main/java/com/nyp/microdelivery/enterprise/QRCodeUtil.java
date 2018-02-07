package com.nyp.microdelivery.enterprise;

import com.swetake.util.Qrcode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

public class QRCodeUtil {


    public BufferedImage createQrCode(String content,Image logo) {
        try {
            Qrcode logoQrCode = new Qrcode();
            logoQrCode.setQrcodeErrorCorrect('M'); //L(7%)、M(15%)、Q(25%)、H(30%)
            logoQrCode.setQrcodeEncodeMode('B');//n number ,b ->other,a alphabet
            logoQrCode.setQrcodeVersion(6);//bigger hold more info,  1-40

            BufferedImage bufferedImage = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufferedImage.createGraphics();
            gs.setBackground(Color.WHITE);
            gs.setColor(Color.BLACK);
            gs.clearRect(0, 0, 139, 139);

            byte[] contents = content.getBytes("utf-8");

            int pixoff = 2;//for analysis
            if (contents.length > 0 && contents.length < 130) {
                boolean[][] codeOut = logoQrCode.calQrcode(contents);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }

            }
            //logo part


            /*int widthLogo = logo.getWidth(null) > bufferedImage.getWidth() * 2 / 10 ? (bufferedImage.getWidth() * 2 / 10) : logo.getWidth(null);
            int heightLogo = logo.getHeight(null) > bufferedImage.getHeight() * 2 / 10 ? (bufferedImage.getHeight() * 2 / 10) : logo.getWidth(null);

            int x = (bufferedImage.getWidth() - widthLogo) / 2;
            int y = (bufferedImage.getHeight() - heightLogo) / 2;

            gs.drawImage(logo,x,y,widthLogo,heightLogo,null);*/

            gs.dispose();
            bufferedImage.flush();

            return bufferedImage;


        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();

        }
        return null;


    }

}
