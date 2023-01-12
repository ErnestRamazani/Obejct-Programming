class SavingAccount extends CheckingAccount {

        public double balance;
        public double interest;

        public void interest(int month) {
                balance = balance + balance * (1 + interest * month / 12) - balance;
        }

        public void deposit(double money) {
                super.deposit(money);
                this.balance+=money;
        }

        public void withdraw(double money){
                super.withdraw(money);
                 if (balance <= 0) {
                        System.out.println("Balance is low");
                } else {
                        this.balance -= money;
                        System.out.println("Withdraw:" + money);
                }
}
}
