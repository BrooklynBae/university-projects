from swiplserver import PrologThread


class ListPeopleWithoutSiblingsYoungerThan:
    def __init__(self, year: str):
        self.year = int(year)

    def run(self, prolog: PrologThread):
        res = prolog.query(f"born(Person, Year), Year > {self.year}, \\+ hasSibling(Person)")

        if res:
            people = []

            for item in res:
                person_name = item['Person']
                birth_year = item['Year']
                people.append(f"{person_name} (родился в {birth_year} году)")

            self.success(people)
        else:
            self.failure()

    def success(self, people):
        print(f"Люди без братьев/сестер, родившиеся после {self.year} года:")
        for person in people:
            print(f" - {person}")

    def failure(self):
        print(f"Не найдено людей без братьев/сестер, родившихся после {self.year} года")