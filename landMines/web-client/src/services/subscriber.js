
class Subscriber extends Game.Observer{

    constructor(observer){
        super()
        this.observer = observer;
    }
    updateData(msg){
        this.observer.update(msg)
    }

}

export default Subscriber;