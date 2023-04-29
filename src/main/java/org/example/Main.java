package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        Scanner scan =new Scanner(System.in);
        boolean isTrue=true;

        System.out.println("Hello, I'm your virtual bartender:)");
        while (isTrue){


            System.out.println();
            System.out.println("1. Display products");
            System.out.println("2. Add product");
            System.out.println("3. Delete product");
            System.out.println("4. Give me idea for 3 drinks");
            System.out.println("5. Give me idea for 3 non-alcoholic drinks");
            System.out.println("6. Give me 3 drinks ideas with specific liquor");
            System.out.println("7. Exit");

            System.out.println();
            System.out.println("Please select one of the options");

            int choice= Integer.parseInt(scan.nextLine());
            ProductManager productManager = new ProductManager();
            ChatGPTHelper chatGPTHelper = new ChatGPTHelper();
            switch (choice){
                case 1->{
                    System.out.println("Products: ");
                    productManager.getAllProducts().forEach(System.out::println);
                }
                case 2->{
                    System.out.println("What product you wanna add?");
                    String product = scan.nextLine();
                    productManager.addProduct(product);
                }
                case 3->{
                    System.out.println("Which product you wanna delete?");
                    String product = scan.nextLine();
                    productManager.deleteProduct(product);
                }
                case 4->{
                    System.out.println("ChatGPT's ideas for drinks: ");
                    String drinkIdea = chatGPTHelper.getDrinkIdea(productManager.getAllProducts());
                    System.out.println(drinkIdea);
                }
                case 5->{
                    System.out.println("ChatGPT's ideas for non-alcoholic drinks: ");
                    String nonAlcoholicDrinkIdea = chatGPTHelper.getNonAlcoholicDrinkIdea(productManager.getAllProducts());
                    System.out.println(nonAlcoholicDrinkIdea);
                }
                case 6->{
                    System.out.println("An idea with a specific liquor");
                    String specificProduct = scan.nextLine();
                    String specificDrinkIdea = chatGPTHelper.specificDrinkIdea(specificProduct);
                    System.out.println("ChatGPT's ideas for your specific liquor");
                    System.out.println(specificDrinkIdea);

                }
                case 7->{
                    System.out.println("Good bye :) ");
                    isTrue=false;
                    System.exit(0);

                }
            }

        }

    }
}