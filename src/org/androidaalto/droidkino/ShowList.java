/*******************************************************************************

   Copyright: 2011 Android Aalto Community

   This file is part of SoundFused.

   SoundFused is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.

   SoundFused is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with SoundFused; if not, write to the Free Software
   Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 ******************************************************************************/
package org.androidaalto.droidkino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.provider.Contacts;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ShowList extends Activity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Creating a static list of shows
        List<Show> showList = null;
		try {
			showList = Parser.retrieveShows();
			
		} catch (Exception e) {
			// TODO Show the error in a toast alert
			e.printStackTrace();
		}
		if (showList!=null)
		{
			ListView lv= (ListView)findViewById(R.id.listview);
	    	
	    	// create the grid item mapping
	    	String[] from = new String[] {"title", "time", "teathre"};
	    	int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3};
		 
	        // prepare the list of all records
		    Show show = null;
	        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
	        for(int i = 0; i < showList.size(); i++){
	        	show = showList.get(i);
	            HashMap<String, String> map = new HashMap<String, String>();
	            map.put("title", show.getTitle());
	            map.put("time", show.getDttmShowStart().toString());
	            map.put("teathre", show.getTheatre());
	            fillMaps.add(map);
	        }
	 
	        // fill in the grid_item layout
	        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
	        lv.setAdapter(adapter);
	    }
	}
}