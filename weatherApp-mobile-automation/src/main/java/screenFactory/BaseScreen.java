package screenFactory;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseScreen {

    protected final AppiumDriver mobileObject;

    public BaseScreen(AppiumDriver<?> appium){
        this.mobileObject=appium;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(appium)), this);

    }
}
