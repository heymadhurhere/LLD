package ATM.database;

import java.util.HashMap;
import java.util.Map;

import ATM.entity.AtmData;

public class AtmRepository {
    private final Map<String, AtmData> atmDatabase;

    public AtmRepository() {
        this.atmDatabase = new HashMap<>();
    }

    public void save (AtmData atmData) {
        atmDatabase.put(atmData.getAtmId(), atmData);
    }

    public AtmData getAtmDetails(String atmId) {
        if (!atmDatabase.containsKey(atmId)) {
            throw new IllegalArgumentException("Atm with ID " + atmId + " wad not found");
        }
        return atmDatabase.get(atmId);
    }
}
