import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.write.Point;

public class InfluxDBHelper {
    private static final InfluxDBClient influxDBClient = InfluxDBClientFactory.create(
            "http://localhost:8086",
            "XyIDsQ2VVyqyn3f2LDLnM1NDM81LbdTqUJq4zyKv30mYG6dCJR297HQ6pqqHDSTnALZBk3F2Kc2mm1gPOE45gQ==".toCharArray(),
            "sang",
            "selenium");

    public static void send(final Point point){
        var writeApi = influxDBClient.getWriteApiBlocking();
        // create a single data point and insert it into InfluxDB
        writeApi.writePoint(point);
    }
}
