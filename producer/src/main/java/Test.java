import com.github.begoodyourself.proxy.CglibProxy;
import com.github.begoodyourself.proxy.JavaDynamicProxy;
import com.github.begoodyourself.sample.pro.*;
import com.github.begoodyourself.sample.pro.CalService;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(((CalService)JavaDynamicProxy.proxy(CalService.class)).aa(
                "javaPrxoy",100,"hhhhaaaaaa",11111211, AddressBookProtos.Person.PhoneType.MOBILE
        ));
        System.out.println(((CalService)CglibProxy.proxy(CalService.class)).aa(
                "cglibProxy",100,"hhhhaaaaaa",11111211, AddressBookProtos.Person.PhoneType.MOBILE
        ));
    }
}
