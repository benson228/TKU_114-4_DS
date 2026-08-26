import java.util.ArrayList;
import java.util.List;

public class Q04_NotificationRouter {

    public interface Channel {
        String name();

        boolean supports(String destination);

        String send(String destination, String message);
    }

    public static class EmailChannel implements Channel {

        @Override
        public String name() {
            return "EMAIL";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }

            int atIndex = destination.indexOf('@');

            return atIndex > 0
                    && atIndex < destination.length() - 1;
        }

        @Override
        public String send(
                String destination,
                String message) {
            return name()
                    + "|" + destination
                    + "|" + message;
        }
    }

    public static class SmsChannel implements Channel {

        @Override
        public String name() {
            return "SMS";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }

            String digits = destination.replace("-", "");

            if (digits.length() != 10) {
                return false;
            }

            for (int i = 0; i < digits.length(); i++) {
                if (!Character.isDigit(digits.charAt(i))) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public String send(
                String destination,
                String message) {
            return name()
                    + "|" + destination
                    + "|" + message;
        }
    }

    public static List<String> route(
            List<Channel> channels,
            String destination,
            String message) {

        if (channels == null
                || destination == null
                || message == null) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        for (Channel channel : channels) {
            if (channel == null) {
                continue;
            }

            if (channel.supports(destination)) {
                result.add(
                        channel.send(
                                destination,
                                message));
            }
        }

        return result;
    }
}
