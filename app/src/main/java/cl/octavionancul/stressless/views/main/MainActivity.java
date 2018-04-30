package cl.octavionancul.stressless.views.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cl.octavionancul.stressless.R;
import cl.octavionancul.stressless.models.Pending;
import cl.octavionancul.stressless.views.main.search.SearchFragment;
import cl.octavionancul.stressless.views.main.search.SearchListener;

public class MainActivity extends AppCompatActivity implements SearchListener {

    private PendingsFragment pendingsFragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pendingsFragment = (PendingsFragment) getSupportFragmentManager().findFragmentById(R.id.pendingsFragment);
        searchFragment= (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.searchFragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_pending);
                ImageButton button = dialog.findViewById(R.id.savePendingBtn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText input = dialog.findViewById(R.id.pendingEt);
                        String name = input.getText().toString();

                        if (name.trim().length() > 0) {
                            Pending pending = new Pending();
                            pending.setName(name);
                            pending.setDone(false);
                            pending.save();
                            Log.d("save", String.valueOf(pending));
                            pendingsFragment.updateList(pending);
                            searchFragment.updateListSearch(name);

                        }
                        dialog.dismiss();

                    }
                });
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();


            }
        });
    }


    @Override
    public void searched(String name) {
        pendingsFragment.updateListNames(name);
      //  Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
    }
}
