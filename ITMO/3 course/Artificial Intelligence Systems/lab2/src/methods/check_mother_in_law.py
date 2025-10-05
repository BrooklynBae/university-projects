from swiplserver import PrologThread


class CheckMotherInLaw:
    def __init__(self, potential_mother_in_law: str, person: str):
        self.potential_mother_in_law = potential_mother_in_law
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isMotherInLaw({self.potential_mother_in_law}, {self.person})")

        if res:
            self.success()
        else:
            self.failure()

    def success(self):
        print(f"Да, {self.potential_mother_in_law} является свекровью {self.person}")

    def failure(self):
        print(f"Нет, {self.potential_mother_in_law} не является свекровью {self.person}")