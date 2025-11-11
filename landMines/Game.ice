module Game{

    struct CellDTO{
        bool isLandMine;
        int value;

        bool hide;

        bool showAll;

        bool isMarked;
    }

    sequence<CellDTO> PixelArray;
    sequence<PixelArray> PixelMatrix;

    interface GameServices{
        bool selectCell(int i, int j);
        PixelMatrix getBoard();
        void resetGame();
    }

    interface Observer{
        void updateData(PixelMatrix newMatrix);
    }
    
    interface Subject{
        void attachObserver(Observer* objs);
        void deAttach(Observer* obs);
    }

}