package co.icesi.buscaminas.services;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Current;

import Game.CellDTO;
import Game.ObserverPrx;
import Game.Subject;

public class SubjectImpl implements Subject {

    private List<ObserverPrx> list;

    public SubjectImpl() {
        list = new ArrayList<>();
    }

    @Override
    public void attachObserver(ObserverPrx objs, Current current) {
        System.out.println("Attach");
        System.out.println("Nuevo observer conectado: " + objs.ice_getIdentity());
        ObserverPrx proxy = objs.ice_fixed(current.con);
        list.add(proxy);

        // Asociar el callback a la conexión
        if (current.con != null) {
            current.con.setCloseCallback(connection -> {
                System.out.println("Conexión cerrada, eliminando observer: " + objs.ice_getIdentity());
                list.remove(proxy);
            });
        }
    }

    @Override
    public void deAttach(ObserverPrx obs, Current current) {
        // TODO Auto-generated method stub
        
    }

    public void notifyObs(CellDTO[][] data) {
        System.out.println("notify: " + list.size());
        List<ObserverPrx> disconnected = new ArrayList<Game.ObserverPrx>();
        for (ObserverPrx prx : list) {
            try {
                prx.updateData(data);
            } catch (Exception e) {
                disconnected.add(prx);
                e.printStackTrace();
            }
        }
        list.removeAll(disconnected);
    }

}
