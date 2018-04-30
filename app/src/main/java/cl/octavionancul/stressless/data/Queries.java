package cl.octavionancul.stressless.data;

import java.util.ArrayList;
import java.util.List;

import cl.octavionancul.stressless.models.Pending;

public class Queries {

    public List<Pending> pendings(){

    List<Pending> pendings = new ArrayList<>();
    List<Pending> pendingList = Pending.find(Pending.class,"done = ?","0");
    if(pendingList != null && pendingList.size()>0){
    pendings.addAll(pendingList);
    }
    return  pendings;
    }

    //Lista de nombres del autocompletetextview
    public List<String> names(){
        List<String> names = new ArrayList<>();
        List<Pending>  pendings = pendings();

        for (int i = 0 ; i<pendings.size();i++){
            names.add(pendings.get(i).getName());
        }
        return names;
    }

    //Filtra los pendientes por nombre
    public List<Pending> findByName(String name){

        List<Pending> pendings = new ArrayList<>();
        String query  = "done = 0 and name like '%"+name+"%' ";
        List<Pending> pendingList = Pending.find(Pending.class,query);

        if(pendingList!=null && pendingList.size()>0){
        pendings.addAll(pendingList);
        }
        return pendings;
    }
}
