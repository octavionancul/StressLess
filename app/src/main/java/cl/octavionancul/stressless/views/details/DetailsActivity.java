package cl.octavionancul.stressless.views.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import cl.octavionancul.stressless.R;
import cl.octavionancul.stressless.models.Pending;
import cl.octavionancul.stressless.views.main.PendingsFragment;

public class DetailsActivity extends AppCompatActivity {
    private Pending pending;
    private EditText detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        long id = getIntent().getLongExtra(PendingsFragment.PENDING_ID, 0);

        pending = Pending.findById(Pending.class, id);

        getSupportActionBar().setTitle(pending.getName());

        detail = findViewById(R.id.detailEt);

        // Toast.makeText(this, pending.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (pending.getDescription() != null){

            String description = pending.getDescription();
            detail.setText(description);
            detail.setSelection(description.length());

           // Toast.makeText(this, String.valueOf(description.length()), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        pending.setDescription(detail.getText().toString());
        pending.save();
    }


}
