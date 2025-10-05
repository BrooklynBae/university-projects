from swiplserver import PrologThread


class CheckGrandparent:
    def __init__(self, potential_grandparent: str, person: str):
        self.potential_grandparent = potential_grandparent
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isGrandparent({self.potential_grandparent}, {self.person})")

        if res:
            self.success()
        else:
            self.failure()

    def success(self):
        print(f"Да, {self.potential_grandparent} является бабушкой/дедушкой {self.person}")

    def failure(self):
        print(f"Нет, {self.potential_grandparent} не является бабушкой/дедушкой {self.person}")