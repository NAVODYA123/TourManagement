package model;

/**
 * Created by Gayan Kalinga on 06-May-17.
 */
public class Destination {
    private int cityCode;
    private String cityName;
    private float pricePerDay;
    private int minDays;

    //Destination Constructor
    Destination(String cn, float ppd, int mind){
        cityName=cn;
        pricePerDay=ppd;
        minDays=mind;
    }

    //Mutator Methods


    //Accessor Methods
    void setCityName(String varCN){this.cityName =varCN;}

    void setPricePerDay(float varPPD){this.pricePerDay=varPPD;}

    void setMinDays(int varMIND){this.minDays=varMIND;}

}
