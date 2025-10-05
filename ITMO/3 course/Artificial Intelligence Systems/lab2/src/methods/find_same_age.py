from swiplserver import PrologThread


class FindSameAge:
    def __init__(self, person: str):
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isTheSameAge({self.person}, X)")

        if res:
            same_age_people = []

            for item in res:
                values_list = list(item.values())
                person_name = values_list[0]
                if person_name != self.person:
                    same_age_people.append(person_name)

            self.success(same_age_people)
        else:
            self.failure()

    def success(self, same_age_people):
        if same_age_people:
            print(f"Ровесники {self.person}:")
            for person in same_age_people:
                print(f" - {person}")
        else:
            print(f"У {self.person} нет ровесников")

    def failure(self):
        print(f"Не удалось найти ровесников для {self.person}")