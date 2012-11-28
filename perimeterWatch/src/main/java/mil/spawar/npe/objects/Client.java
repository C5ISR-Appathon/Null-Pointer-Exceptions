package mil.spawar.npe.objects;

import java.util.ArrayList;
import java.util.List;

public class Client extends Server {

	List<Alert> alerts;
	public Client(String name, String ipAddress) {
		super(name, ipAddress);
		alerts=new ArrayList<Alert>();
	}
	public void addAlert(Alert alert)
	{
		alerts.add(alert);
	}
	public void clearAlerts()
	{
		alerts.clear();
	}
	public int alertSize()
	{
		return alerts.size();
	}
	public List<Alert> getAlerts()
	{
		return alerts;
	}

}
