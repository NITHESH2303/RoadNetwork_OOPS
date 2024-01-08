import java.util.*;

class Location {
        String name;
        Map<Location, Integer> adjacentLocationsVsDistance;
        static Map<String, Location> locations = new HashMap<>();
        public Location(String location) {
                this.name = location;
                this.adjacentLocationsVsDistance = new HashMap<>();
                locations.put(location, this);
        }

        public static void newLocation(Road road, String locationName, int distance) {
                Location location;
                if (Location.locations.containsKey(locationName)) {
                        location = Location.locations.get(locationName);
                } else {
                        location = new Location(locationName);
                }
                if (!road.locations.isEmpty()) {
                        Pair lastPair = road.locations.get(road.locations.size() - 1);
                        Location lastLocation = lastPair.location;
                        location.adddistance(lastLocation, distance);
                        lastLocation.adddistance(location, distance);
                }
                road.addlocation(location, road, distance);
        }

        public static Location insertLocation(Road road, String locationName, int distance) {
                Location location = new Location(locationName);
                return location;
        }

        public void adddistance(Location location, int distance) {
                adjacentLocationsVsDistance.put(location, distance);
        }

        public String removeLocation(Location location) {
//                if (!adjacentLocationsVsDistance.containsKey(location)) {
//                        return ("the location" + location.name + "was not found");
//                }
                Boolean flag = Road.removeLocation(location);
                if(flag) {
                        locations.remove(location.name);
                        adjacentLocationsVsDistance.remove(location);
                        return "Location: " + location.name + " " + "removed Successfully";
                }
                else return "the location" + location.name + " was not removed";
        }

        public String InsertnewLocation(Location loci1, Location loci2, String location, int dist1, int dist2) {
                if (!locations.containsKey(loci1.name) || !locations.containsKey(loci2.name)) {
                        return "Either: " + loci1.name + "or " + loci2.name + "not found";
                }
                if (locations.containsKey(location)) return "location " + location + " is already present";
                else {
                        Road road = Road.InsertnewLocation(loci1, loci2, location, dist1, dist2);
                        if (road != null) {
//                                Pair prevPair = road.getPairByLocation(loci1);
                                return "Location: " + location + " " + "inserted Successfully";
                        }
                }return location;
        }

        public String getlocationName() {
                return name;
        }
}
