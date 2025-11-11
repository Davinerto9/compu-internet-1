package co.icesi.buscaminas.services;

import com.zeroc.Ice.Current;

import Game.CellDTO;
import Game.GameServices;
import co.icesi.buscaminas.model.Cell;

public class ServiceIceImpl implements GameServices {

    private ServicesImpl servicesImpl;

    private SubjectImpl subject;

    public ServiceIceImpl(ServicesImpl service, SubjectImpl sub) {
        servicesImpl = service;
        subject = sub;
    }

    @Override
    public boolean selectCell(int i, int j, Current current) {
        boolean ret = servicesImpl.selectCell(i, j);
        subject.notifyObs(getBoard(current));
        return ret;
    }

    @Override
    public CellDTO[][] getBoard(Current current) {
        Cell[][] cells = servicesImpl.printBoard();
        CellDTO[][] dtos = new CellDTO[cells.length][cells[0].length];

        for (int i = 0; i < dtos.length; i++) {
            for (int j = 0; j < dtos[0].length; j++) {
                Cell cell = cells[i][j];
                CellDTO dto = new CellDTO(cell.isLandMine(), cell.getValue(), cell.isHide(), cell.isShowAll(),
                        cell.isMarked());
                dtos[i][j] = dto;
            }
        }

        return dtos;
    }

    public void resetGame(Current current){
        servicesImpl.initGame(8, 8, 10);
    }   



}
