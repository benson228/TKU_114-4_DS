import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    static class Browser {
        private final Deque<String> history = new ArrayDeque<>();

        public void visit(String page) {
            if (page == null || page.isBlank()) {
                return;
            }

            history.push(page);
            System.out.println("Visit: " + page);
        }

        public String back() {
            if (history.isEmpty()) {
                return null;
            }

            history.pop();

            if (history.isEmpty()) {
                return null;
            }

            return history.peek();
        }

        public String current() {
            if (history.isEmpty()) {
                return null;
            }

            return history.peek();
        }
    }

    public static void main(String[] args) {
        Browser browser = new Browser();

        browser.visit("Google");
        System.out.println("Current: " + browser.current());

        browser.visit("YouTube");
        System.out.println("Current: " + browser.current());

        browser.visit("GitHub");
        System.out.println("Current: " + browser.current());

        System.out.println("Back: " + browser.back());
        System.out.println("Current: " + browser.current());

        System.out.println("Back: " + browser.back());
        System.out.println("Current: " + browser.current());

        System.out.println("Back: " + browser.back());
        System.out.println("Current: " + browser.current());

        System.out.println("Back on empty: " + browser.back());
    }
}