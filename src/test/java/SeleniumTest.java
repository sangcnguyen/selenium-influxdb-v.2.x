import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {
    @Test(description = "Login")
    public void launchApp() throws Exception {
        Thread.sleep(1000);
        Assert.fail();
    }
    @Test(description = "Search")
    public void sort() throws Exception {
        Thread.sleep(1000);
        Assert.fail();
    }
    @Test(description = "Select")
    public void selectAProduct() throws Exception {
        Thread.sleep(1000);

    }
    @Test(description = "Log Out")
    public void goToAboutPage() throws Exception {
        Thread.sleep(1000);
    }
}
