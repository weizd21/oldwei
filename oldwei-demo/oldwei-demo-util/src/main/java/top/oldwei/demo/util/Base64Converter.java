package top.oldwei.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @Author:weizd
 * @Date:20-7-27
 */
@Slf4j
public class Base64Converter {

    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();

    public static void main(String[] args) throws Exception {

        String content = "import time\n" +
                "a = 1\n" +
                "if a == 1:\n" +
                "\tprint(\"aaaaaaa\")\n" +
                "    pr你好";

        System.out.println(encode(content));

        System.out.println(decode(encode(content)));


        String cypher = "match(n) where n.name contains '书院' return n limit 20";

        log.info(encode(URLEncoder.encode(cypher,"UTF-8")));

        log.info(decode(encode(cypher)));


        log.info(decode("TUFUQ0ggKG4pIFJFVFVSTiBuIExJTUlUIDI1Ow=="));


        log.info(URLDecoder.decode(decode("bWF0Y2gobiklMjB3aGVyZSUyMG4ubmFtZSUyMCUzRCUyMCUyMiVFNiVCNSU4QiVFOCVBRiU5NSUyMiUyMHJldHVybiUyMG4lMjBsaW1pdCUyMDIw"),"UTF-8"));




        log.info(decode("bWF0Y2gobikgd2hlcmUgbi5uYW1lID0gIua1i+ivlSIgcmV0dXJuIG4gbGltaXQgMjA="));



        log.info(encode("bWF0Y2gobikgd2hlcmUgbi5uYW1lID0gIua1i+ivlSIgcmV0dXJuIG4gbGltaXQgMjA="));


    }




    /**
     * 给字符串加密
     * @param text
     * @return
     */
    public static String encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }



    /**
     * 将加密后的字符串进行解密
     * @param encodedText
     * @return
     */
    public static String decode(String encodedText) {
        String text = null;
        try {
            text = new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }



}
