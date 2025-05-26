package pl.zenit.infobazademo;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.zenit.infobazademo.data.cache.DataCache;
import pl.zenit.infobazademo.data.external.DataFetcher;
import pl.zenit.infobazademo.data.cache.DataCacheMemImpl;

import java.util.concurrent.atomic.AtomicInteger;

@Profile("!test")
@Service
@DependsOn("DataCacheMemImpl")
public class DataGatherer {

    private final DataFetcher df;

    private final DataCache cache;

    private final AtomicInteger fetchTrials = new AtomicInteger(0);

    public DataGatherer(DataFetcher df, DataCacheMemImpl cache) {
        this.df = df;
        this.cache = cache;
    }

    @Scheduled(fixedDelayString = "${jsonplaceholder.api.fetch-interval}")
    public void refresh() {
        int cnt = fetchTrials.get();
        int totalDelayMs = cnt * cnt * 1000;
        fetchTrials.incrementAndGet();
        try {
            Thread.sleep(totalDelayMs);
            String result = df.fetchData();
            fetchTrials.set(0);
            cache.update(result);
        }
        catch (Exception reported) {
            reported.printStackTrace();
        }
    }

}
