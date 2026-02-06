package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
//        1st thing we strated to test from here this blow line
//        assertNotNull(userRepository.findByUserName("ram"));
//        2nd we got here to test
        User user = userRepository.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "87, 3, 90",
            "2, 4, 90"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }


    @ParameterizedTest
    @ValueSource(strings =  {
            "ram",
            "shyam",
            "vipul",
            "Alauddin",
            "Talha"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name), "Test filed for: " + name);
//        assertNotNull(userRepository.findByUserName(name), "Test filed for: " + name);
//        dono above 2 line me difference just veh hai ek me hame malum hojaga actual konsa name
//        error dera (obviously jisme message likke ara usme) but vaise bhi nhi bhi likhe to niche
//        o/p ara usme properly dikhjega actual kon error dera ya fail hua all of this was only
//        for knowledge purpose
    }
}
