import java.io.Serializable;

public class Model implements Serializable {
    private String name;
    private Double humidity;
    private Double temp;
    private String icon;
    private String main;


    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Model(String name, Double humidity, Double temp, String icon, String main) {
        this.name = name;
        this.humidity = humidity;
        this.temp = temp;
        this.icon = icon;
        this.main = main;
    }

    public Model() {
    }
}
