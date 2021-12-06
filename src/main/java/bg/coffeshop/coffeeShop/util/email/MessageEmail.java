package bg.coffeshop.coffeeShop.util.email;

import bg.coffeshop.coffeeShop.constant.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MessageEmail implements MessageEmailService {
    private String fileContent;

    public MessageEmail() {
        this.fileContent = "";
    }

    public String getMessage() {
        return this.fileContent;
    }

    public void readFromFile() throws FileNotFoundException {
        File myObj = new File(Constant.PATH_FILE);
        Scanner myReader = new Scanner(myObj);
        StringBuilder result = new StringBuilder();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            result.append(data);
        }
        myReader.close();
        this.fileContent = result.toString().replaceAll("\\^", "\n");
    }

}
