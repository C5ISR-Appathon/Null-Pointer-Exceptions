package mil.spawar.npe.networking.interfaces;

import java.util.List;

import mil.spawar.npe.objects.Client;

public interface ServerNetworkInterface {
	public List<Client> getAllConnectedClients();
	public void disconnectAllClients();
	public void disconnectClient(Client client);
}
