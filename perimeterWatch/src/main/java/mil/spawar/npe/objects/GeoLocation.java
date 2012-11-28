package mil.spawar.npe.objects;

public class GeoLocation {
	double lat;
    double lng;
    
    public GeoLocation(double lat, double lng)
    {
    	this.lat=lat;
    	this.lng=lng;
    }
    public double getLatitude()
    {
    	return this.lat;
    }
    public double getLongitude()
    {
    	return this.lng;
    }
}
