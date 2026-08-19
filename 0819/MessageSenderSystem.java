public class MessageSenderSystem {

    interface MessageSender {
        void send(String receiver, String message);
    }

    static class EmailSender implements MessageSender {
        @Override
        public void send(String receiver, String message) {
            if (receiver == null || receiver.isBlank()
                    || message == null || message.isBlank()) {
                System.out.println("Email 發送失敗：收件者或訊息為空");
                return;
            }

            System.out.println("Email → " + receiver + "：" + message);
        }
    }

    static class SmsSender implements MessageSender {
        @Override
        public void send(String receiver, String message) {
            if (receiver == null || receiver.isBlank()
                    || message == null || message.isBlank()) {
                System.out.println("SMS 發送失敗：收件者或訊息為空");
                return;
            }

            System.out.println("SMS → " + receiver + "：" + message);
        }
    }

    static class ConsoleSender implements MessageSender {
        @Override
        public void send(String receiver, String message) {
            if (receiver == null || receiver.isBlank()
                    || message == null || message.isBlank()) {
                System.out.println("Console 發送失敗：收件者或訊息為空");
                return;
            }

            System.out.println("Console → " + receiver + "：" + message);
        }
    }

    static void notify(MessageSender sender, String receiver, String message) {
        if (sender == null) {
            System.out.println("發送失敗：sender 不得為 null");
            return;
        }

        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "amy@example.com", "課程開始提醒");
        notify(sms, "0912345678", "作業即將截止");
        notify(console, "Amy", "測試訊息");

        notify(email, "", "空白收件者測試");
        notify(sms, "0912345678", "");
    }
}