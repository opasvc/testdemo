import cn.hutool.core.date.LocalDateTimeUtil;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String s1 = "hello";
        String s2 = "world";
        String s3 = s1 + s2;
        String s4 = "helloworld";
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
    }

    public static void main(String[] args) {
//        new DemoApplicationTests().contextLoads();
//        String s1 = "hello";
//        String s2 = s1;
//        System.out.println(s1 == s2);
//        System.out.println(s1.equals(s2));
//        s2 = "world";
//        System.out.println(s1 == s2);
//        System.out.println(s1.equals(s2));
        System.out.println(new LocalDateTimeUtil().now());
        new DemoApplicationTests().http();
        System.out.println(new LocalDateTimeUtil().now());
    }

    void http() {
        HashMap<String, String> result = new HashMap<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("https://tlgafzxc.wuaze.com/")
                .get()
                .build();
        for (int i = 0; i < 200; i++) {
            Call call = client.newCall(request);
            Object obj = null;
            try {

                Response execute = call.execute();
                obj = execute.body().string();
                result.put(i + "", obj.toString());
                System.out.println(i + ":" + obj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        result.forEach((s, s2) -> System.out.println(s + ":" + s2));

    }
}
