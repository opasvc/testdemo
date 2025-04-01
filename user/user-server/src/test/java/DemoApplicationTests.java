import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        String s1 = "hello";
        String s2 = s1;
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        s2 = "world";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
