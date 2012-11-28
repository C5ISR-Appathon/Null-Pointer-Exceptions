package mil.spawar.npe.networking.interfaces;

import java.util.List;

import mil.spawar.npe.objects.Server;

public interface ClientNetworkInterface {
	public List<Server> getAllServers();
	public void connectToServer(Server server);
	public void disconnectFromServer();
	
}
