/**
 * Runs all threads
 */

public class BridgeRunner {
	

	public static void main(String[] args) {
		int num_cars;
		int limit;

		// TODO - check command line inputs
		if(args.length != 2){
			System.out.println("Usage: javac BridgeRunner <bridge limit> <num cars>");
			return;
		} else{
			limit = Integer.valueOf(args[0]);
			num_cars = Integer.valueOf(args[1]);
		}
		if(limit <= 0 || num_cars <= 0){
			System.err.println("Error: bridge limit and/or num cars must be positive.");
			return;
		} else try{
			
		
		// TODO - instantiate the bridge
		OneLaneBridge bridge = new OneLaneBridge(limit);
		
		// TODO - allocate space for threads
		// TODO - start then join the threads
		Thread[] cars = new Thread[num_cars];
 		for (int i = 0; i < num_cars; i++) {
      		cars[i] = new Thread(new Car(i, bridge));
			cars[i].start();
    	}
		for (int i = 0; i < num_cars; i++) {
			try {
				cars[i].join();
			} catch (InterruptedException e) {
				
			}
		}

		System.out.println("All cars have crossed!!");
		}
	}

}