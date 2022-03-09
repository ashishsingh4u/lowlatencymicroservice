package com.techienotes.lowlatencymicroservice.helper;

import com.techienotes.lowlatencymicroservice.model.PricingData;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class MapHelperService {

    static final long MAXSIZE = 50L;
    static final ChronicleMapBuilder<Long, PricingData> builder =
            ChronicleMapBuilder.of(Long.class, PricingData.class);
    static AtomicInteger waterMark = new AtomicInteger((int) MAXSIZE);
    Random rand = new Random();

    ChronicleMap<Long, PricingData> pricingDataChronicleMap;

    @PostConstruct
    void init() throws IOException {
        Path tempDirectory = Files.createTempDirectory("chronicle-map");
        Path tempFile = Files.createTempFile(tempDirectory, "chronicle-map", ".dat");
        File queueDir = tempFile.toFile();
        log.info("Off-Heap data is written at: {}", queueDir.getAbsolutePath());
        try {
            builder.actualSegments(8 * 1024);
            builder.entries(MAXSIZE);
            builder.averageValue(new PricingData(1, new StringBuilder("data")));
            pricingDataChronicleMap = builder.createPersistedTo(queueDir);
        } catch (IOException e) {
            log.error("Error while creating Chronicle Map", e);
        }
    }

    public long saveObject() {
        long current = rand.nextInt(waterMark.get());
        PricingData data = new PricingData(current, new StringBuilder("Pricing data"));
        pricingDataChronicleMap.put(current, data);
        return current;
    }

    public PricingData getObject(long id) {
        return pricingDataChronicleMap.get(id);
    }
}
