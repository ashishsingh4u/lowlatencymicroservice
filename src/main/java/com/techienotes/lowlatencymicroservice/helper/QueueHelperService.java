package com.techienotes.lowlatencymicroservice.helper;

import com.techienotes.lowlatencymicroservice.model.Price;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QueueHelperService {

    Chronicle chronicle;

    @PostConstruct
    void init() throws IOException {
        File queueDir = Files.createTempDirectory("chronicle-queue").toFile();
//        File queueDir = Paths.get("/var/folders/wv/_2ys8rcj7_13rgzlhz69tnrh0000gn/T/chronicle-queue1417388675518435102").toFile();
        log.info("Off-Heap data is written at: {}", queueDir.getAbsolutePath());
        chronicle = ChronicleQueueBuilder.indexed(queueDir).build();
    }

    public void saveObject(Price price) {
        try (ExcerptAppender appender = chronicle.createAppender()) {
            appender.startExcerpt();

            appender.writeUTF(price.getCurrencyPair());
            appender.writeDouble(price.getAsk());
            appender.writeDouble(price.getBid());
            appender.writeDouble(price.getSpot());
            appender.finish();
        } catch (IOException e) {
            log.error("Error occurred while writing object to Off-Heap", e);
        }
    }

    public List<Price> getObjects() {
        List<Price> prices = new ArrayList<>();
        try (ExcerptTailer tailer = chronicle.createTailer()) {
            while (tailer.nextIndex()) {
                Price price = Price.builder()
                        .currencyPair(tailer.readUTF())
                        .ask(tailer.readDouble())
                        .bid(tailer.readDouble())
                        .spot(tailer.readDouble()).build();
                prices.add(price);
            }
            tailer.finish();
        } catch (IOException e) {
            log.error("Exception occurred while reading Off-Heap objects", e);
        }
        return prices;
    }
}
