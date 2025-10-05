from swiplserver import PrologThread


class CompareAge:
    def __init__(self, person1: str, person2: str):
        self.person1 = person1
        self.person2 = person2

    def run(self, prolog: PrologThread):
        res1 = prolog.query(f"born({self.person1}, Year1)")
        res2 = prolog.query(f"born({self.person2}, Year2)")

        if res1 and res2:
            year1 = list(res1[0].values())[0]
            year2 = list(res2[0].values())[0]

            if year1 < year2:
                self.success(self.person1, self.person2, "старше")
            elif year1 > year2:
                self.success(self.person2, self.person1, "старше")
            else:
                self.same_age()
        else:
            self.failure()

    def success(self, older_person, younger_person, relation):
        print(f"{older_person} старше {younger_person}")

    def same_age(self):
        print(f"{self.person1} и {self.person2} ровесники")

    def failure(self):
        print(f"Не удалось сравнить возраст {self.person1} и {self.person2}")