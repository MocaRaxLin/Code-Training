package OOD;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;



public class ParkingLot {
	
	public class RegisterData{
		public String licensePlateName;
		public int stallId;
		public long startTime;
		public RegisterData(String lpn, int i, long t) {
			this.licensePlateName = lpn;
			this.stallId = i;
			this.startTime = t;
		}
	}
	
	Date clock;
	double feePerHour;
	double income;
	final int largeCount, mediumCount, smallCount;
	Queue<Integer> L, M, S;
	Map<String, RegisterData> data;
	
	public ParkingLot(int l, int m, int s) {
		clock = new Date();
		feePerHour = 10;
		income = 0;
		
		largeCount = l;
		mediumCount= m;
		smallCount = s;
		
		L = new LinkedList<Integer>();
		M = new LinkedList<Integer>();
		S = new LinkedList<Integer>();
		
		int id = 0;
		for(int i = 0; i < largeCount; i++) L.offer(id++);
		for(int i = 0; i < mediumCount; i++) M.offer(id++);
		for(int i = 0; i < smallCount; i++) S.offer(id++);
		
		data = new HashMap<String, RegisterData>();
	}
	
	public void showLeftSpots() {
		System.out.println("Large: " + L.size());
		System.out.println("Medium: " + M.size());
		System.out.println("Small: " + S.size());
	}
	
	public boolean getVehicle(Vehicle v) {
		RegisterData vehicleData = data.get(v.licensePlateName);
		if(vehicleData == null) {
			System.out.println("Your car is not in this parking lots!");
			return false;
		}
		long duration = clock.getTime() - vehicleData.startTime;
		float d = duration;
		double fee = d/1000.0/60.0/60.0*feePerHour;
		
		// Assume users will pay parking fee
		income += fee;
		System.out.println("Your fee: " + fee);
		
		data.remove(v.licensePlateName);
		switch(v.type) {
		case Large:
			L.offer(vehicleData.stallId);
			break;
		case Medium:
			M.offer(vehicleData.stallId);
			break;
		case Small:
			S.offer(vehicleData.stallId);
			break;
		}
		return true;
	}
	
	public boolean putVehicle(Vehicle v) {
		if(data.containsKey(v.licensePlateName)) {
			System.out.println("Your car is already in this parking lots!");
			return false;
		}
		switch(v.type) {
		case Large:
			if(L.size() == 0) {
				System.out.println("no space for you!");
				return false;
			}else{
				int id = L.poll();
				RegisterData rd = new RegisterData(v.licensePlateName, id, clock.getTime());
				data.put(v.licensePlateName, rd);
				return true;
			}
		case Medium:
			if(M.size() == 0) {
				System.out.println("no space for you!");
				return false;
			}else{
				int id = M.poll();
				RegisterData rd = new RegisterData(v.licensePlateName, id, clock.getTime());
				data.put(v.licensePlateName, rd);
				return true;
			}
		case Small:
			if(S.size() == 0) {
				System.out.println("no space for you!");
				return false;
			}else{
				int id = S.poll();
				RegisterData rd = new RegisterData(v.licensePlateName, id, clock.getTime());
				data.put(v.licensePlateName, rd);
				return true;
			}
		default:
			System.out.println("unknown type of vehicle!");
			return false;
		}		
	}
	
	public static void main(String[] args) {
		Vehicle car1 = new Vehicle("3297f2", Type.Large);
		Vehicle car2 = new Vehicle("2rf2-4fqrg", Type.Large);
		Vehicle car3 = new Vehicle("8888", Type.Small);
		Vehicle car4 = new Vehicle("2fqc", Type.Medium);
		
		ParkingLot PL = new ParkingLot(1, 1, 1);
		System.out.println("PL.putVehicle(car1): " + PL.putVehicle(car1));
		System.out.println("PL.putVehicle(car2): " + PL.putVehicle(car2));
		System.out.println("PL.putVehicle(car3): " + PL.putVehicle(car3));
		System.out.println("PL.putVehicle(car4): " + PL.putVehicle(car4));
		
		PL.showLeftSpots();
		System.out.println("PL.getVehicle(car1): " + PL.getVehicle(car1));
		System.out.println("PL.getVehicle(car2): " + PL.getVehicle(car2));
		System.out.println("PL.getVehicle(car3): " + PL.getVehicle(car3));
		System.out.println("PL.getVehicle(car4): " + PL.getVehicle(car4));
		
	}
	
	
	
}

enum Type{
	Large, Medium, Small
}

class Vehicle{
	public String licensePlateName;
	public Type type;
	public Vehicle(String licensePlateName, Type t) {
		this.licensePlateName = licensePlateName;
		this.type = t;
	}
}

