package cl.octavionancul.stressless.views.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cl.octavionancul.stressless.R;
import cl.octavionancul.stressless.adapters.PendingClickListener;
import cl.octavionancul.stressless.adapters.PendingsAdapter;
import cl.octavionancul.stressless.models.Pending;
import cl.octavionancul.stressless.views.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */

public class PendingsFragment extends Fragment implements PendingClickListener {
    public static final String PENDING_ID = "com.example.adacher.stressless.views.KEY.PENDING_ID";
    private PendingsAdapter adapter;

    public PendingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.pendindRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        //Creado para pasar la lista directamente al adapter sin guardar en la bd
     /*   List<Pending> pendings = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Pending pending = new Pending();
            pending.setName(String.valueOf(i));
            pending.setDescription(String.valueOf(i));
            pending.setDone(false);
            *//*
            pending.save();*//*
           pendings.add(pending);
        }*/

        //Se agrega la lista al adapter
        adapter = new PendingsAdapter(this);

        recyclerView.setAdapter(adapter);


    }

    public void updateList(Pending pending) {
        adapter.update(pending);
        Log.d("save", pending.getName());

    }

    @Override
    public void clickedID(long id) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(PENDING_ID,id);
        startActivity(intent);

   //     Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}
