import com.github.begoodyourself.proxy.JavaDynamicProxy;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class Test {

    public static void main(String[] args) {
       CalSer c = (CalSer) JavaDynamicProxy.proxy(CalSer.class);
        c.aa();
    }
}
