import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

record ServiceRequest(
        String id,
        String location,
        int priority,
        long sequence) {}

public class ServiceRequestSystem {

    private final Map<String, ServiceRequest> requests = new HashMap<>();

    private final PriorityQueue<ServiceRequest> queue =
            new PriorityQueue<>(
                    Comparator.comparingInt(ServiceRequest::priority)
                            .thenComparingLong(ServiceRequest::sequence));

    public boolean submit(ServiceRequest request) {

        if (request == null
                || request.id() == null
                || request.location() == null
                || request.id().trim().isEmpty()
                || request.location().trim().isEmpty()) {
            return false;
        }

        String id = request.id().trim();

        if (requests.containsKey(id)) {
            return false;
        }

        ServiceRequest normalized =
                new ServiceRequest(
                        id,
                        request.location().trim(),
                        request.priority(),
                        request.sequence());

        requests.put(id, normalized);
        queue.offer(normalized);

        return true;
    }

    public boolean cancel(String id) {

        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        String key = id.trim();

        ServiceRequest request = requests.remove(key);

        if (request == null) {
            return false;
        }

        queue.remove(request);

        return true;
    }

    public ServiceRequest nextRequest() {

        while (!queue.isEmpty()) {

            ServiceRequest request = queue.poll();

            if (requests.containsKey(request.id())) {
                requests.remove(request.id());
                return request;
            }
        }

        return null;
    }

    public ServiceRequest find(String id) {

        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        return requests.get(id.trim());
    }

    public int pendingCount() {
        return requests.size();
    }

    public List<String> pendingIds() {

        List<String> result = new ArrayList<>();

        for (ServiceRequest request : requests.values()) {
            result.add(request.id());
        }

        result.sort(String::compareTo);

        return result;
    }
}
