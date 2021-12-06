package bg.coffeshop.coffeeShop.util.email;

import java.io.FileNotFoundException;

public interface MessageEmailService {

    String getMessage();

    void readFromFile() throws FileNotFoundException;
}
