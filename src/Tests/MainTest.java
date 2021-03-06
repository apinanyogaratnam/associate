package Tests;

import com.apinanyogaratnam.Company;
import com.apinanyogaratnam.Main;
import com.apinanyogaratnam.MainHelper;
import com.apinanyogaratnam.User;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static MainHelper mainHelperMethod = new MainHelper();
    static Main mainMethod = new Main();

    @Test
    public void duplicateUsernameTest() {
        LinkedList<User> allUsers = new LinkedList<>();

        // create new user
        User apinan = Main.createNewUser("Apinan", "Yogaratnam", "apinanyogaratnam", allUsers, false);

        // create duplicate user
        User apinanCopy = Main.createNewUser("Apinan", "Yogaratnam", "apinanyogaratnam", allUsers, false);

        assertEquals(null, apinanCopy);
    }

    @Test
    public void duplicateCompanyTest() {
        LinkedList<Company> allCompanies = new LinkedList<>();

        // creates new companies
        Company timhortons = Main.createNewCompany("Tim Hortons", allCompanies, false);
        Company mcdonald = Main.createNewCompany("McDonald's", allCompanies, false);

        // create duplicate company
        Company timhortonsCopy = Main.createNewCompany("Tim Hortons", allCompanies, false);

        assertEquals(null, timhortonsCopy);
    }

    @Test
    public void userInAllUsersTest() {
        LinkedList<User> allUsers = new LinkedList<>();

        // create new user
        User apinan = Main.createNewUser("Apinan", "Yogaratnam", "apinanyogaratnam", allUsers, false);

        // search through allUsers for new user
        boolean foundUser = false;
        for (User user : allUsers) {
            if (user.getUsername().equals(apinan.getUsername())) {
                foundUser = true;
                break;
            }
        }

        assertEquals(true, foundUser);
    }

    @Test
    public void companyInAllCompaniesTest() {
        LinkedList<Company> allCompanies = new LinkedList<>();

        // create new company
        Company mcdonald = Main.createNewCompany("McDonald's", allCompanies, false);

        // search through allCompanies for new company
        boolean foundCompany = false;
        for (Company company : allCompanies) {
            if (company.getName().equals(mcdonald.getName())) {
                foundCompany = true;
                break;
            }
        }

        assertEquals(true, foundCompany);
    }

}