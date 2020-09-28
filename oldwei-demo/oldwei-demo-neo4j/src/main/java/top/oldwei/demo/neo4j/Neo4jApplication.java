package top.oldwei.demo.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-6-3
 */

//@SpringBootApplication
@Slf4j
public class Neo4jApplication {


    public static void main(String[] args) {


    }




    public static void word2007ToHtml() throws Exception {
        String filePath = "/home/weizd/Desktop/20200601-20200605/minsheng_poc/";
        String fileName = "人民币个人银行结算账户分类介绍(1).docx";
        String htmlName = "人民币个人银行结算账户分类介绍(1).html";
        final String file = filePath + fileName;
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            /* 判断是否为docx文件 */
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
                // 1)加载word文档生成XWPFDocument对象
                FileInputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);
                // 2)解析XHTML配置（这里设置IURIResolver来设置图片存放的目录）
                File imageFolderFile = new File(filePath);
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                options.setIgnoreStylesIfUnused(false);
                options.setFragment(true);
                // 3)将XWPFDocument转换成XHTML
                FileOutputStream out = new FileOutputStream(new File(filePath + htmlName));
                XHTMLConverter.getInstance().convert(document, out, options);
                //也可以使用字符数组流获取解析的内容
                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //XHTMLConverter.getInstance().convert(document, baos, options);
                //String content = baos.toString();
                //System.out.println(content);
                //baos.close();
            } else {
                System.out.println("Enter only as MS Office 2007+ files");
            }
        }
    }


}
