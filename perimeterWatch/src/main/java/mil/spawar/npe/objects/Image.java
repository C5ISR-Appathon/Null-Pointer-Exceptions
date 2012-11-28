package mil.spawar.npe.objects;

import android.net.Uri;

public class Image {
	String name;
	
	//replace Uri filePath with whatever we are actually storing images with in dougs stuff
	Uri filePath;
	
	public Image(String name,Uri filePath)
	{
		this.name=name;
		this.filePath=filePath;
	}
	public String getName()
	{
		return name;
	}
	public Uri getFilePath()
	{
		return this.filePath;
	}
}
