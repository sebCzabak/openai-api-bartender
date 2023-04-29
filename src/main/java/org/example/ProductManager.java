package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ProductManager {
    private final Path productsFilePath;

    public  ProductManager() throws URISyntaxException {
        ClassLoader classLoader = ProductManager.class.getClassLoader();
        productsFilePath = Paths.get(Objects.requireNonNull(classLoader.getResource("products.txt")).toURI());
    }
    public List<String> getAllProducts() throws IOException {

        return Files.readAllLines(productsFilePath, StandardCharsets.UTF_8);

    }
    public void addProduct(String product) throws IOException {
        HashSet<String> products = new HashSet<>(getAllProducts());

        if(!products.contains(product)) {
            Files.writeString(productsFilePath,System.lineSeparator() + product, StandardOpenOption.APPEND);
        }else {
            System.out.println("This product is already on my list ");
        }
    }
    public void deleteProduct(String product) throws IOException {
        HashSet<String> products = new HashSet<>(getAllProducts());

        if(products.contains(product)){
            products.remove(product);
            String productsToSave = String.join(System.lineSeparator(), products);
            Files.writeString(productsFilePath,productsToSave);
        }else{
            System.out.println("There is no such product on my list ");
        }

    }
}
