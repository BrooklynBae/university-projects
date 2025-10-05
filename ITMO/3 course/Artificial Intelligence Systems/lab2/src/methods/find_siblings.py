from swiplserver import PrologThread


class FindSiblings:
    def __init__(self, person: str):
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isSibling({self.person}, X)")

        if res:
            siblings = []

            for item in res:
                values_list = list(item.values())
                sibling_name = values_list[0]
                siblings.append(sibling_name)

            self.success(siblings)
        else:
            self.failure()

    def success(self, siblings):
        print(f"Братья и сестры {self.person}:")
        for sibling in siblings:
            print(f" - {sibling}")

    def failure(self):
        print(f"Не найдено братьев и сестер для {self.person}")