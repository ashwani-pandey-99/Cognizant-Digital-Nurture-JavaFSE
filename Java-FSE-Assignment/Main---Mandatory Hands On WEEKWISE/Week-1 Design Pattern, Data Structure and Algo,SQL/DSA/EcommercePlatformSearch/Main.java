public class Main {
    
    public static void main(String[] args) {
        
        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Phone", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Bag", "Fashion")
        };
        
        Product p1 = LinearSearch.search(products, 104);
        
        if (p1 != null) {
            System.out.println("Linear Search:");
            System.out.println(p1.productId + " " + p1.productName + " " + p1.category);
        }
        
        Product p2 = BinarySearch.search(products, 104);
        
        if (p2 != null) {
            System.out.println("Binary Search:");
            System.out.println(p2.productId + " " + p2.productName + " " + p2.category);
        }
    }
}
