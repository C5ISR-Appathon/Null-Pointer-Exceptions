package mil.spawar.npe;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ClientModeActivity extends ListActivity {
	 private static String TAG = "testApp";
	
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MUCH TIMES BUTTON WAS CLICKED
    int clickCounter=0;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
		Log.i(TAG, "onCreate");
		
        setContentView(R.layout.clientview_findhost);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);
        
        Button quit=(Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				finish();
	            System.exit(0);
			}
        	
        });
        
        listItems.add("Server 1");
        listItems.add("Server 2");
        listItems.add("Server 3");
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        listItems.add("Clicked : "+clickCounter++);
        adapter.notifyDataSetChanged();
    }
}
