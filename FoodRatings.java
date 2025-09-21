import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class FoodRatings {
    private Map<String, Integer> foodRatings;
    private Map<String, String> foodCuisines;
    private Map<String, PriorityQueue<Food>> cuisineRatings;
    private class Food {
        String name;
        String cuisine;
        int rating;

        public Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodRatings = new HashMap<>();
        foodCuisines = new HashMap<>();
        cuisineRatings = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            foodRatings.put(food, rating);
            foodCuisines.put(food, cuisine);
            cuisineRatings.putIfAbsent(cuisine, new PriorityQueue<>((a, b) -> {
                if (a.rating != b.rating) {
                    return b.rating - a.rating;
                }
                return a.name.compareTo(b.name);
            }));
            cuisineRatings.get(cuisine).add(new Food(food, cuisine, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodCuisines.get(food);
        foodRatings.put(food, newRating);
        cuisineRatings.get(cuisine).add(new Food(food, cuisine, newRating));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineRatings.get(cuisine);
        while (pq.peek().rating != foodRatings.get(pq.peek().name)) {
            pq.poll();
        }
        return pq.peek().name;
    }

    public static void main(String[] args) {
        String[] foods = {"sushi", "burger", "pizza"};
        String[] cuisines = {"japanese", "american", "italian"};
        int[] ratings = {5, 7, 8};
        FoodRatings fr = new FoodRatings(foods, cuisines, ratings);

        System.out.println(fr.highestRated("italian"));
        fr.changeRating("pizza", 6);
        System.out.println(fr.highestRated("italian"));
        fr.changeRating("pizza", 4);
        System.out.println(fr.highestRated("italian"));
    }
}