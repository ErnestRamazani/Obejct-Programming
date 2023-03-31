
#include "mbox.h"
#include <iostream>
#include <map>
#include <queue>
#include <mutex>
#include <condition_variable>

class MailBox {
public:
    int mbox_send(int id, const char* msg, int len);
    int mbox_recv(int id, char* buf, int maxlen);
    void mbox_init();
    void mbox_destroy();

private:
    std::map<int, std::queue<std::string>> mailboxes;
    std::map<int, std::mutex> mailbox_mutexes;
    std::map<int, std::condition_variable> mailbox_conds;
};

// Singleton pattern to access the MailBox instance.
MailBox& getMailBoxInstance() {
    static MailBox mailBox;
    return mailBox;
}

// Implement the 4 API's declared in the header file.

void MailBox::mbox_init() {
    // No need to initialize anything as the default constructors take care of that.
}

void MailBox::mbox_destroy() {
    mailboxes.clear();
    mailbox_mutexes.clear();
    mailbox_conds.clear();
}

int MailBox::mbox_send(int id, const char* msg, int len) {
    std::unique_lock<std::mutex> lock(mailbox_mutexes[id]);
    mailboxes[id].push(std::string(msg, len));
    mailbox_conds[id].notify_one();
    return len;
}

int MailBox::mbox_recv(int id, char* buf, int maxlen) {
    std::unique_lock<std::mutex> lock(mailbox_mutexes[id]);
    mailbox_conds[id].wait(lock, [&]() { return !mailboxes[id].empty(); });

    std::string msg = mailboxes[id].front();
    mailboxes[id].pop();
    int bytes_received = std::min(maxlen, static_cast<int>(msg.size()));
    std::copy(msg.begin(), msg.begin() + bytes_received, buf);
    return bytes_received;
}
