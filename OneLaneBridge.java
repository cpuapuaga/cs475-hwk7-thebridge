public class OneLaneBridge extends Bridge {

    private int limit;
    private Object access = new Object();x

    public OneLaneBridge(int val){
        limit = val;
    }

    /**
    * This method has to determine whether the thread which called it must wait, 
    * or is allowed to proceed on to the bridge.
    */
    @Override
    public void arrive(Car car) throws InterruptedException{
        synchronized (access) {
            try{
               while(bridge.size() == limit || car.getDirection() != direction){
                access.wait();
                } 
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            car.setEntryTime(currentTime);
            bridge.add(car);
            currentTime++;
            System.out.println("Bridge (dir="+ direction + "): " + bridge);
        }
    }

    /**
    * This Bridge method is called by a car when it wants to exit the bridge.
    */
    @Override
     public void exit(Car car) throws InterruptedException {
        synchronized (access) {

            if (car.equals(bridge.get(0))) {
                bridge.remove(0);
                System.out.println("Bridge (dir=" + direction + "): " + bridge);
                access.notifyAll();
            }
            else {
                    access.wait()
            }
            System.out.println("Bridge (dir=" + direction + "): " + bridge);
            if(limit > bridge.size()){
                access.notifyAll();
            }
            if (bridge.isEmpty()) {
                direction = !direction;
            }
        }
    }
}