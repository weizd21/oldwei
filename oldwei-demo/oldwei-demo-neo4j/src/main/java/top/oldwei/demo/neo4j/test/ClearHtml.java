package top.oldwei.demo.neo4j.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HtmlUtil;

import java.io.File;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-6-8
 */
public class ClearHtml {


    public static void main(String[] args) {
        String htmlPath = "/home/weizd/Desktop/20200608-20200613/minsheng_poc/html_txt/上海分行TOM联名卡.html";

        String reg = "/<[^>]+>/g";

        reg = "</?[^>]+>";

        htmlPath = "/home/weizd/Desktop/20200608-20200613/minsheng_poc/html_txt";

        List<File> fileList = FileUtil.loopFiles(htmlPath);
        for (File file:fileList){
//            System.out.println(HtmlUtil.cleanHtmlTag(FileUtil.readString(file,"utf-8")).replace("&nbsp;",""));
            System.out.println(HtmlUtil.cleanHtmlTag(FileUtil.readString(file,"utf-8")));
        }


//        for (File file:fileList){
//            System.out.println(FileUtil.readString(file,"utf-8").replace(reg,"").replace("&nbsp;",""));
//        }






    }
}
