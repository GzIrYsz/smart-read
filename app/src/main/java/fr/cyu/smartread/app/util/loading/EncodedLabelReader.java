package fr.cyu.smartread.app.util.loading;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class EncodedLabelReader {
    public static HashMap<Integer, Character> getDecoderLabelFromCsv(String labelEncodedCsvPath) {
        HashMap<Integer, Character> decoderLabelHM = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(labelEncodedCsvPath))) {

            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                Character label = nextLine[1].charAt(0);
                Integer encodedLabel = Integer.valueOf(nextLine[2]);

                decoderLabelHM.put(encodedLabel, label);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return decoderLabelHM;
    }
}
