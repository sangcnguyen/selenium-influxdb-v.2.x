import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.Instant;
import java.util.HashMap;

public class InfluxDBListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "PASS");
    }

    public void onTestFailure(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "FAIL");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        this.sendTestClassStatus(iTestContext);
    }

    private void sendTestMethodStatus(ITestResult iTestResult, String status) {
        var point = Point.measurement("testmethod")
                .time(Instant.now(), WritePrecision.MS)
                .addTags( new HashMap<>(){{
                    put( "testclass", iTestResult.getTestClass().getName());
                    put("name", iTestResult.getName());
                    put("description", iTestResult.getMethod().getDescription());
                    put("result", status);
                }})
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis()));
        InfluxDBHelper.send(point);
    }

    private void sendTestClassStatus(ITestContext iTestContext) {
        var point = Point.measurement("testclass")
                .time(Instant.now(), WritePrecision.MS)
                .addTag("name", iTestContext.getAllTestMethods()[0].getTestClass().getName())
                .addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()));
        InfluxDBHelper.send(point);
    }
}
