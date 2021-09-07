package nl.minor.clsd.data;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class WordRepository {
    public HashMap<String, Integer> requestedTexts = new HashMap<>();
}
