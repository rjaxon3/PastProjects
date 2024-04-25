public class Driver {
    public static void main(String[] args) {
    	Dairy[] yuh = new Dairy[4];
    	Bakery[] yuhh = new Bakery[2];
    	Dairy obj1 = new Dairy("milk" , 5, 5.59, 7);
    	Dairy obj2 = new Dairy("cheese", 1, 2.5, 12);
    	Dairy obj3 = new Dairy("butter" , 2, 9.99, 29);
    	Bakery obj4 = new Bakery("croissant" , 2, 3.99);
    	Bakery obj5 = new Bakery("donut", 3, 2.99);
    	Dairy obj6 = new Dairy("Cheese", 2, 3.99,12);
    	yuh[0] = obj1;
    	yuh[1] = obj2;
    	yuh[2] = obj3;
    	yuh[3] = null;
    	yuhh[0] = obj4;
    	yuhh[1] = obj5;
    	ShoppingCart test = new ShoppingCart(yuh,yuhh);

    	//test.displayItems();
    	test.removeBakeryItemAtIndex(0);
    	//test.addDairyItemAtIndex(1,obj6);
    }
	
}