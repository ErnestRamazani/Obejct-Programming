class CheckingAccount {

        public double balance;

        public void deposit(double money) {
                this.balance += money;
        }

        public void withdraw(double money) {
                if (balance <= 0) {
                        System.out.println("Balance is low");
                } else {
                        this.balance -= money;
                        System.out.println("Withdraw:" + money);
                }
        }
}
