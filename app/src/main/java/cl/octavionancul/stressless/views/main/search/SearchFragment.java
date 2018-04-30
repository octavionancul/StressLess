package cl.octavionancul.stressless.views.main.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cl.octavionancul.stressless.R;
import cl.octavionancul.stressless.data.Queries;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private SearchListener listener;

    private List<String> names = new Queries().names();
    private ArrayAdapter<String> adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (SearchListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final AutoCompleteTextView completeTextView = (AutoCompleteTextView) view;
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);

        completeTextView.setAdapter(adapter);

        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = completeTextView.getText().toString();
                listener.searched(name);

                // actuazalizar las sugerencias cuando se acaba de crear un nuevo pending
                /*
                 *  El MainActivity crea el nuevo pending y puede acceder al fragmento hijo searchFragment
                 *  El searchFragment puede actualizar el adaptador del completetextview
                 *  Convertir el adaptador y la lista en variables de clase para que se puedan acceder desde otro metodo.
                 *  cuando mainActivity crea un nuevo pending pasamos el nombre al fragmento hijo para actualizar adapter
                 *  Ademas listar todos los pendings cuando el autocompletetextview quede vacio
                 * */

                // Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();

            }
        });

        completeTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 0) {
                    listener.listAll();
                    //Toast.makeText(getContext(), s.toString().length() + " " + String.valueOf(count), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void updateListSearch(String name) {
        names.add(name);
        adapter.add(name);
        adapter.notifyDataSetChanged();

        // Toast.makeText(getContext(), String.valueOf(names.size()), Toast.LENGTH_SHORT).show();
    }
}
