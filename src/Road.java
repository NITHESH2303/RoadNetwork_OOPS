import java.util.*;

class Pair{
    Location location;
    int distance;
    public Pair(Location location, int distance) {
        this.location = location;
        this.distance = distance;
    }
}
public class Road {
    String road_name;
    List<Pair> locations;
    static Map<String, Road> roads = new HashMap<>();

    public Road(String road_name) {
        this.road_name = road_name;
        this.locations = new ArrayList<>();
        roads.put(road_name, this);
    }

    public static void addRoad(String road_name, Road road) {
        roads.put(road_name, road);
    }

    public void addlocation(Location location, Road road, int distance) {
        Pair pair = new Pair(location, distance);
        if (locations.contains(pair)) {
            System.out.println("Location: " + location + " " + "is already in the road " + road.road_name);
        } else {
            locations.add(pair);
        }
    }

    public static void NewRoad(String name){
        if(roads.containsKey(name)) System.out.println("Road: " + name + " " + "is already exists ");
        Road road = new Road(name);
        addRoad(name, road);
    }

    public static Road getRoad(String road_name){
        if(!roads.containsKey(road_name)) System.out.println("Road: " + road_name + " " + "not found");
        return roads.get(road_name);
    }

    public static Boolean removeLocation(Location location){
        boolean found = false;
        for(Road road : roads.values()){
            for(int i=0; i<road.locations.size(); i++){
                if(road.locations.get(i).location.equals(location)){
                    int temp_distance = road.locations.get(i).distance;
                    road.locations.remove(i);
                    found = true;
                    if(i<road.locations.size()-1){
                        road.locations.get(i+1).distance+=temp_distance;
                    }
                }
            }
        }
        return found;
    }

    public static Road InsertnewLocation(Location loci1, Location loci2, String locationName, int dist1, int dist2){
        boolean found = false;
        for(Road road: roads.values()) {
            for(int i=0; i<road.locations.size()-1; i++){
                if(road.locations.get(i).location.equals(loci1) && road.locations.get(i+1).location.equals(loci2)){
                    Location location=Location.insertLocation(road, locationName, dist1);
                    road.locations.get(i+1).distance = dist2;
                    road.locations.add(new Pair(location, dist1));
                    found=true;
                    return road;
                }
            }
        }
        return null;
    }

    public static String getRoadInfo(Road road){
        String road_name = road.road_name;
        List<Pair> locations = road.locations;
        int total_distance = 0;
        for(int i=0; i<road.locations.size(); i++){
            total_distance +=road.locations.get(i).distance;
        }
        System.out.println("Road_name: " + road_name + "\n" + "Total Distance: " + total_distance + "\n" + "locations: ");
        for(int i=0; i<road.locations.size(); i++){
            System.out.println(locations.get(i).location.name);
        }
        return "Road info provided Successfully";
    }

    public static String findShortestPath(Location startLocation, Location endLocation, String path, int distance) {
        path += startLocation.getlocationName() + " -> ";
        if (startLocation.equals(endLocation)) {
            return "Shortest distance: " + distance + ", Route: " + path.substring(0, path.length() - 4);
        } else {
            String shortestPath = null;
            int shortestDistance = Integer.MAX_VALUE;
            for (Map.Entry<Location, Integer> entry : startLocation.adjacentLocationsVsDistance.entrySet()) {
                Location nextLocation = entry.getKey();
                int nextDistance = entry.getValue();
                if (!path.contains(nextLocation.getlocationName())) {
                    String newPath = findShortestPath(nextLocation, endLocation, path, distance + nextDistance);
                    if (newPath != null) {
                        int newDistance = Integer.parseInt(newPath.split(": ")[1].split(", ")[0]);
                        if (newDistance < shortestDistance) {
                            shortestPath = newPath;
                            shortestDistance = newDistance;
                        }
                    }
                }
            }
            return shortestPath;
        }
    }
}