package owo.com.androidlearn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
      }
    });
    ListView listView = (ListView) findViewById(R.id.id_main_list);
    listView.setAdapter(new ArrayAdapter<>(this,
                                           android.R.layout.simple_expandable_list_item_1,
                                           new String[] {
                                             "app", "appwidget", "bluetooth", "content", "database",
                                             "drm", "gesture", "graphics", "hardware",
                                             "inputmethodservice", "location", "media", "mtp",
                                             "net", "nfc", "opengl", "os", "preference", "print",
                                             "printservice", "provider", "renderscript", "sax",
                                             "security", "service", "speech", "system", "telecom",
                                             "telephony", "test", "text", "transition", "util",
                                             "view", "webkit", "widget",
                                             }));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
