package com.example.a2chign70.mapping2;



        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.preference.PreferenceManager;

        import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
        import org.osmdroid.views.MapView;
        import org.osmdroid.util.GeoPoint;
        import org.osmdroid.config.Configuration;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.content.Intent;
public class MainActivity extends Activity
{

    MapView mv;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
    }


    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(resultCode==RESULT_OK)
        {
            if (requestCode == 0)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            // react to the menu item being selected...

            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;


        } else if(item.getItemId() == R.id.setlocation)
        return false;
    }




}
