import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Restaurant searchedRestaurant = null;

        for(Restaurant restaurant : restaurants){
            if(restaurant.getName() == restaurantName){
                searchedRestaurant = restaurant;
            }
        }

        if(searchedRestaurant == null)
            throw new restaurantNotFoundException("Restaurant not founded");

        return searchedRestaurant;
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
