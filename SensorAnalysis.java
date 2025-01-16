package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class BadSensorException extends Exception {
    String sensorId;

    public BadSensorException(String sensorId) {
        super(String.format("No readings for sensor: %s", sensorId));
        this.sensorId = sensorId;
    }
}

class BadMeasureException extends Exception {
    int timestamp;
    String sensorId;

    public BadMeasureException(int timestamp, String sensorId) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getMessage() {
        return String.format("Error in timestamp: %d from sensor: %s", timestamp, sensorId);
    }
}

interface IGeo {
    double getLatitude();
    double getLongitude();

    default double distance(IGeo other) {
        return Math.sqrt(Math.pow(this.getLatitude() - other.getLatitude(), 2) + Math.pow(this.getLongitude() - other.getLongitude(), 2));
    }
}

class Measurement {
    int timeStamp;
    double value;

    public Measurement(int timeStamp, double value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public static Measurement createMeasurement (String data, String sensorId) throws BadMeasureException {
        String [] parts = data.split(":");
        int timestamp = Integer.parseInt(parts[0]);
        double value = Double.parseDouble(parts[1]);
        if (value < 0) {
            throw new BadMeasureException(timestamp, sensorId);
        }
        return new Measurement(timestamp, value);
    }
}

class Sensor {
    String sensorID;
    IGeo location;
    List<Measurement> measurements;

    public Sensor(String sensorID, IGeo location, List<Measurement> measurements) {
        this.sensorID = sensorID;
        this.location = location;
        this.measurements = measurements;
    }

    public static Sensor createSensor (String line) throws BadMeasureException, BadSensorException {
        String[] parts = line.split("\\s+");
        String sensorId = parts[0];
        if (parts.length == 3) {
            throw new BadSensorException(sensorId);
        }

        IGeo location = new IGeo() {

            @Override
            public double getLatitude() {
                return Double.parseDouble(parts[1]);
            }

            @Override
            public double getLongitude() {
                return Double.parseDouble(parts[2]);
            }
        };

        List<Measurement> measurements = new ArrayList<>();
        long toSkip = 3;
        for (String measurementString : parts) {
            if (toSkip > 0) {
                toSkip--;
                continue;
            }
            Measurement measurement = Measurement.createMeasurement(measurementString, sensorId);
            measurements.add(measurement);
        }

        return new Sensor(sensorId, location, measurements);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorID='" + sensorID + '\'' +
                '}';
    }
}

class GeoSensorApplication {
    List<Sensor> sensors;

    public GeoSensorApplication(List<Sensor> sensors) {
        sensors = new ArrayList<>();
    }

    void readGeoSensors (Scanner scanner) throws BadSensorException, BadMeasureException {
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sensors.add(Sensor.createSensor(line));
        }
    }

    public List<Sensor> inRange(IGeo location, double distance) {
        return sensors.stream()
                .filter(sensor -> sensor.location.distance(location) < distance)
                .collect(Collectors.toList());
    }
}

public class SensorAnalysis {
}
