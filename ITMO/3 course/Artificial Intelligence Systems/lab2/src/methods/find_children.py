from swiplserver import PrologThread

class FindChildren:
    def __init__(self, person: str):
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"parent({self.person}, Y)")

        if res:
            children = []
            for item in res:
                value_list = list(item.values())
                first_value = value_list[0]
                children.append(first_value)
            self.success(children)
        else:
            self.failure()

    def success(self, children):
        print(f"Дети {self.person}:")
        for child in children:
            print(f" - {child}")

    def failure(self):
        print(f"Не удалось найти детей для {self.person}")