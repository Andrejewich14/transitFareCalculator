public class TransitCalculator {
    String city;
    int numberOfDays;
    int numberOfRides;
    int ageOfPassenger;
    FareOption[] currentFares;
    String[] options = {"Pay-per-ride", "7-day Unlimited", "30-day Unlimited"};


    public TransitCalculator (String city, int days, int rides, int age){
        numberOfDays = days;
        numberOfRides = rides;
        ageOfPassenger = age;
        this.city = city;

        
    if (city.equals("New York")) {
        currentFares = new FareOption[]{
            new FareOption("Pay-per-ride", 2.75, 1.35),
            new FareOption("7-day Unlimited", 33.00, 16.50),
            new FareOption("30-day Unlimited", 127.00, 63.50),
        };
    }
    if (city.equals("Madrid")) {
        currentFares = new FareOption[]{
            new FareOption("Pay-per-ride", 1.50, 0.75),
            new FareOption("7-day Unlimited", 23.00, 11.50),
            new FareOption("30-day Unlimited", 97.00, 48.50),
        };
    }
    if (city.equals("New Delhi")) {
        currentFares = new FareOption[]{
            new FareOption("Pay-per-ride", 2.00, 1.00),
            new FareOption("7-day Unlimited", 27.00, 13.50),
            new FareOption("30-day Unlimited", 105.00, 52.50),
        };

    }
    }

    public double[] getRidePrices() {
        double[] ridePrices = new double[currentFares.length];
        for (int i = 0; i < currentFares.length; i++) {
            double price = (ageOfPassenger >= 65) ? currentFares[i].reducedPrice : currentFares[i].regularPrice;
            
            if (i == 1) { // 7-day Unlimited
                int numberOfWeeks = (int) Math.ceil((double) numberOfDays / 7);
                price = numberOfWeeks * price;
                ridePrices[i] = price / numberOfRides;
            } else if (i == 2) { // 30-day Unlimited
                ridePrices[i] = price / numberOfRides;
            } else { // Pay-per-ride
                ridePrices[i] = price; // Directly use the per ride price
            }
        }
        return ridePrices;
    }

    public String getBestFare(){
        
        
        double[] ridePrices = getRidePrices();
        int bestIndex = 0;
        double lowestPrice = ridePrices[0];
        
        for (int i = 1; i < ridePrices.length; i ++ ){
            if (ridePrices[i] < lowestPrice){
                lowestPrice = ridePrices[i];
                bestIndex = i;
            }
        }
    
        return "For your stay in " + this.city + " you should get the " + options[bestIndex] + " option which will cost you $" + Math.round(lowestPrice * 100.0) / 100.0 + " per ride.";

        }


    public static void main(String[] args) {

        TransitCalculator calculator = new TransitCalculator("New York", 7, 5, 64);
        System.out.println(calculator.getBestFare());

    }
}

