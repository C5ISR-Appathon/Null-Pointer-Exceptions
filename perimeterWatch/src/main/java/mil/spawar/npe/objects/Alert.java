package mil.spawar.npe.objects;

import java.util.Date;

public class Alert {
	String name;
	Date date;
	String associatedClient;
	GeoLocation latLng;
	Image img;
	
	public Alert(String name, Date date,String associatedClient,GeoLocation latLng,Image img)
	{
		this.name=name;
		this.date=date;
		this.associatedClient=associatedClient;
		this.latLng=latLng;
		this.img=img;
	}
	public Alert(String name, Date date,String associatedClient,Image img)
	{
		this.name=name;
		this.date=date;
		this.associatedClient=associatedClient;
		this.latLng=null;
		this.img=img;
	}
	
	public String getName()
	{
		return this.name;
	}
	public String getAssociatedClient()
	{
		return this.associatedClient;
	}
	public Date getDate()
	{
		return this.date;
	}
	public GeoLocation getGeoLocation()
	{
		return this.getGeoLocation();
	}
	public Image getImage()
	{
		return this.img;
	}
	
}
