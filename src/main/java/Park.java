package main.java;

public class Park {
    private String name;

    public Park(String name) {
        this.name = name;
    }
    public class Attraction{
        private String name;
        private String time;
        private int price;

        public Attraction(String name, String time, int price) {
            this.name = name;
            this.time = time;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public int getPrice() {
            return price;
        }
    }
}
