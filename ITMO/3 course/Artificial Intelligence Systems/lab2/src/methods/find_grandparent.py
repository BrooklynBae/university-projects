from swiplserver import PrologThread


class FindGrandparents:
    def __init__(self, person: str):
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isGrandparent(X, {self.person})")

        if res:
            grandparents = []

            for item in res:
                values_list = list(item.values())
                grandparent_name = values_list[0]
                grandparents.append(grandparent_name)

            self.success(grandparents)
        else:
            self.failure()

    def success(self, grandparents):
        print(f"Бабушки и дедушки {self.person}:")
        for grandparent in grandparents:
            print(f" - {grandparent}")

    def failure(self):
        print(f"Не найдено бабушек и дедушек для {self.person}")