from swiplserver import PrologThread

class FindParents:
    def __init__(self, person: str):
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"parent(X, {self.person})")

        if res:
            parents = []
            for item in res:
                values_list = list(item.values())
                first_value = values_list[0]
                parents.append(first_value)
            self.success(parents)
        else:
            self.failure()

    def success(self, parents):
        print(f"Родители {self.person}:")
        for parent in parents:
            print(f" - {parent}")

    def failure(self):
        print(f"Не найдено родителей для {self.person}")