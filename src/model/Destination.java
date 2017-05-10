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
    public Destination(int cCode,String cn, float ppd, int mind){
        cityCode=cCode;
        cityName=cn;
        pricePerDay=ppd;
        minDays=mind;
    }

    //Mutator Methods
    public int getCityCode(){return cityCode;}
    public String getCityName(){return cityName;}
    public float getPricePerDay(){return pricePerDay;}
    public int getMinDays(){return minDays;}


    //Accessor Methods
    public void setCityName(String varCN){this.cityName =varCN;}
    public void setPricePerDay(float varPPD){this.pricePerDay=varPPD;}
    public void setMinDays(int varMIND){this.minDays=varMIND;}

}
