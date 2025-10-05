from swiplserver import PrologThread


class CheckMarriageBeforeYear:
    def __init__(self, person: str, year: str):
        self.person = person
        self.year = int(year)

    def run(self, prolog: PrologThread):
        res = prolog.query(f"marriedBeforeYear({self.person}, Spouse, {self.year})")

        if res:
            marriages = []

            for item in res:
                values_dict = item
                spouse_name = values_dict['Spouse']
                marriages.append(spouse_name)

            self.success(marriages)
        else:
            self.failure()

    def success(self, marriages):
        if marriages:
            print(f"Да, {self.person} женился до {self.year} года на:")
            for spouse in marriages:
                print(f" - {spouse}")
        else:
            print(f"Нет, {self.person} не женился до {self.year} года")

    def failure(self):
        print(f"Не удалось проверить браки {self.person}")