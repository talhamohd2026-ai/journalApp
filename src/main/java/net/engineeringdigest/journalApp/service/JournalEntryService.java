package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//    public void saveEntry(JournalEntry journalEntry){
//        journalEntryRepository.save(journalEntry);
//
//    }

//    public void saveEntry(JournalEntry journalEntry, String userName){
//            User user = userService.findByUsername(userName);
//            JournalEntry saved = journalEntryRepository.save(journalEntry);
//            user.getJournalEntries().add(saved);
//            userService.saveEntry(user);
//    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUsername(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
            userService.saveJustEntry(user);
        } catch (Exception e) {
            log.error("Error", e);
            throw new RuntimeException("Throws runtime exception",e);
        }


    }
//    this exactly below one saveEntry method is for PUT which is starting from line 148 in
//    JournalEntryControllerV2
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();

    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);

    }

//    public void deleteById(ObjectId id){
//        journalEntryRepository.deleteById(id);
//
//    }

//    public void deleteById(ObjectId id, String userName){
//        User user = userService.findByUsername(userName);
//        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
//        userService.saveEntry(user);
//        journalEntryRepository.deleteById(id);
//
//    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try{
            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveJustEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occoured while deleting the entry",e);
        }
        return removed;



    }


}
