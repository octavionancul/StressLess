package cl.octavionancul.stressless.views.main.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    private ArrayAdapter<String> adapter ;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (SearchListener) context;
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
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, names);

        completeTextView.setAdapter(adapter);


        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = completeTextView.getText().toString();
                listener.searched(name);

                //TODO: actuazalizar las sugerencias cuando se acaba de crear un nuevo pending
                /*
                *  El MainActivity crea el nuevo pending y puede acceder al fragmento hijo searchFragment
                *  El searchFragment puede actualizar el adaptador del completetextview
                *  Convertir el adaptador y la lista en variables de clase para que se puedan acceder desde otro metodo.
                *  y cuando el el mainActivity se crea un nuevo pending le pasamos el nombre al fragmento hijo
                * */


               // Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void updateListSearch(String name){
    names.add(name);
      //  names.clear();
    // names.addAll(  new Queries().names());
        adapter.add(name);
     adapter.notifyDataSetChanged();

       // Toast.makeText(getContext(), String.valueOf(names.size()), Toast.LENGTH_SHORT).show();
    }
}
