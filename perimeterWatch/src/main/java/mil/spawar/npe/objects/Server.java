package mil.spawar.npe.objects;

public class Server {
	String name;
	String ipAddress;
	
	public Server(String name,String ipAddress)
	{
		this.name=name;
		this.ipAddress=ipAddress;
	}
	public String getName()
	{
		return name;
	}
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress=ipAddress;;
	}
}
