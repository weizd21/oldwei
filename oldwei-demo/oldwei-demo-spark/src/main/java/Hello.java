import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:weizd
 * @Date:19-11-27
 */
public class Hello {


    public static void main(String[] args) throws InterruptedException {


        System.out.println("launcher enter time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        for(int i = 0;i<1000;i++){
            System.out.println("line--------> "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            Thread.sleep(1000);
        }

        System.out.println("launcher leave time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


    }


}
