package race;

import distributions.Gamma;
import distributions.Normal;
import distributions.Rayleigh;

public class Weather {
    private double precipitation;
    private double temperatureDegree;
    private double windSpeed;
    private double humidityPercentage;

    public Weather() {
        precipitation = Gamma.generateRandom(0.5, 5.0, 1.5);
        temperatureDegree = Normal.generateRandom(22.5, 7.5);
        windSpeed = Rayleigh.generateRandom(5.0,5.0);
        humidityPercentage = calculateRelativeHumidity();
    }

    private double calculateSaturationVaporPressure() {
        double temperatureCelsius = this.temperatureDegree;
        double es = 6.11 * Math.pow(10, (7.5 * temperatureCelsius) / (237.7 + temperatureCelsius));
        return es;
    }

    private double calculateRelativeHumidity() {
        double es = calculateSaturationVaporPressure();
        double estimatedActualPressure = 0.6 * this.precipitation + 0.4 * this.windSpeed;
        double rh = (estimatedActualPressure / es) * 100;
        return rh;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getTemperatureDegree() {
        return temperatureDegree;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getHumidityPercentage() {
        return humidityPercentage;
    }
}
