import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void set(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Restaurant mockRestaurant = Mockito.spy(restaurant);

        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00"));

        assertTrue(mockRestaurant.isRestaurantOpen());

        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("12:00:00"));

        assertTrue(mockRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant mockRestaurant = Mockito.spy(restaurant);

        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:00:00"));

        assertFalse(mockRestaurant.isRestaurantOpen());

        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));

        assertFalse(mockRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER_VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void getOrderValue_should_return_total_amount_of_the_order(){
        ArrayList<Item> orderItems = new ArrayList<Item>();
        Item item1 = new Item("Sweet corn soup",119);
        Item item2 = new Item("Vegetable lasagne", 269);
        orderItems.add(item1);
        orderItems.add(item2);

        int orderValue = restaurant.getOrderValue(orderItems);

        assertEquals(388,orderValue);

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<ORDER_VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}