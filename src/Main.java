import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String road_name;
        String location_name;
        Road road;
        System.out.println("Choose one \n " +
                "1.Create a new Road \n " +
                "2.Add locations to an existing road \n " +
                "3.Get the road info \n " +
                "4.Remove location \n " +
                "5.Insert location between two locatī̄ons in a road" +
                "6.Get shortest Path between two locations \n " +
                "7.exit");
        int n = sc.nextInt();
        while(n!=7){
            switch(n){
                case 1:
                    System.out.println("Enter the name of the road");
                    road_name = sc.next();
                    Road.NewRoad(road_name);
                    System.out.println("Road named " + road_name + " has been created");
                    break;
                case 2:
                    System.out.println("Choose One \n 1.Enter the name of the road you want to add new location and distance \n 2.exit");
                    road_name = sc.next();
                    while(true){
                        if(!Objects.equals(road_name, "exit")) {
                            road = Road.getRoad(road_name);
                            System.out.println("Enter the location and distance");
                            String loci = sc.next();
                            if(loci.equals("-1")) break;
                            int dist = sc.nextInt();
                            if(dist==-1) break;
                            Location.newLocation(road, loci, dist);
                        }
                        else {
                            System.out.println("Exited Successfully");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter the name of the road you want to get information about");
                    road_name = sc.next();
                    road = Road.getRoad(road_name);
                    System.out.println(Road.getRoadInfo(road));
                    break;
                case 4:
                    System.out.println("Enter the name of the location you want to remove");
                    location_name = sc.next();
                    Location location = Location.locations.get(location_name);
                    System.out.println(location.removeLocation(location));
                    break;
                case 5:
                    System.out.println("Enter the name of the road you want to insert the new location");
                    road_name = sc.next();
                    road = Road.getRoad(road_name);
                    System.out.println("Enter the name of the locations you want to add the new location in between and distance between them");
                    String loci1 = sc.next();
                    int dist1 = sc.nextInt();
                    String loci2 = sc.next();
                    int dist2 = sc.nextInt();
                    Location location1 = Location.locations.get(loci1);
                    Location location2 = Location.locations.get(loci2);
                    System.out.println("Enter the name of the location you want to insert");
                    location_name = sc.next();
                    System.out.println();
                case 6:
                    System.out.println("Enter the starting location");
                    String start = sc.next();
                    System.out.println("Enter the ending location");
                    String end = sc.next();
                    Location startLocation = Location.locations.get(start);
                    Location endLocation = Location.locations.get(end);
                    System.out.println(Road.findShortestPath(startLocation, endLocation, "",0));
                    System.out.println("Shortest path found Successfully");
                    break;
                default:
                    System.out.println("Please enter Valid input");
            }
            n = sc.nextInt();
        }
    }
}