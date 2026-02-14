class TicketBooking {
    int tickets = 10;

    public void bookTicket(String user) {
        if (tickets > 0) {
            System.out.println(user + " is booking ticket...");
            try {
                Thread.sleep(100);   // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tickets--;
            System.out.println(user + " booked ticket. Remaining: " + tickets);
        } else {
            System.out.println("No tickets available for " + user);
        }
    }
}

class User extends Thread {
    TicketBooking booking;

    User(TicketBooking booking, String name) {
        super(name);
        this.booking = booking;
    }

    public void run() {
        booking.bookTicket(getName());
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBooking booking = new TicketBooking();

        for (int i = 1; i <= 5; i++) {
            new User(booking, "User-" + i).start();
        }
    }
}



