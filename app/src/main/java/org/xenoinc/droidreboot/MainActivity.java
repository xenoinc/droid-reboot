package org.xenoinc.droidreboot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
// import android.support.v7.app.ActionBar;    // Remvoed, NOt used
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
// import android.os.Build;       // Removed, not used
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

  // <editor-fold defaultstate="collapsed" desc="Default Shit">
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // This isn't really needed
    if (savedInstanceState == null) {
      // Add this other layer ontop?
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment())
          .commit();
    }

    // Call Reboot method here
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Button Handler">

  public void btnHeloClick(View v)
  {
    Button btn = (Button) v;
    String txt = (String) btn.getText();
    Toast.makeText(getApplicationContext(), "Hello there!", Toast.LENGTH_SHORT).show();

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder
        .setTitle("Test Title")
        .setMessage("Are you sure?")
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int which)
          {
            // do something you need
          }
        });
    builder.setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int which)
      {
        dialog.dismiss();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  public void btnRebootClick(View v)
  {
    try
    {
      String[] strExec = new String[3];
      strExec[0] = "su";
      strExec[1] = "-c";
      strExec[2] = "killall system_server";  // "recovery" or "bootloader"
      Runtime.getRuntime().exec(strExec);

      Toast.makeText(getApplicationContext(), "Rebooting!", Toast.LENGTH_SHORT).show();

    } catch (IOException e) {
      Toast.makeText(getApplicationContext(), "Error: " + (String)e.getMessage(), Toast.LENGTH_LONG).show();
      return;
    }
  }

  public void btnExitClick(View v)
  {
    System.exit(0);
  }

  // </editor-fold>


  // <editor-fold defaultstate="collapsed" desc="Extra Crap">
  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

  // </editor-fold>
}
