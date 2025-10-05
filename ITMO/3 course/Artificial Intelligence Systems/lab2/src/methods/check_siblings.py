from swiplserver import PrologThread


class CheckSiblings:
    def __init__(self, person1: str, person2: str):
        self.person1 = person1
        self.person2 = person2

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isSibling({self.person1}, {self.person2})")

        if res:
            self.success()
        else:
            self.failure()

    def success(self):
        print(f"Да, {self.person1} и {self.person2} - родные братья/сестры")

    def failure(self):
        print(f"Нет, {self.person1} и {self.person2} - не родные братья/сестры")