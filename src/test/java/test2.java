import org.testng.annotations.Test;

public class test2 {
    @Test
    public void teststringbuilder() {
        String strjson = "http://www.baidu.com";
        StringBuilder stringBuilder = new StringBuilder(strjson);
//        stringBuilder.append(strjson).append("?");
        System.out.println(stringBuilder);
    }
}
